package com.liashenko.applicant.bot;

import com.liashenko.applicant.command.CommandMatcher;
import com.liashenko.applicant.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ApplicantBot extends TelegramLongPollingBot {
    private final CommandMatcher commandMatcher;
    private final FileService fileService;

    @Value("${bot.userName}")
    private String userName;

    @Autowired
    public ApplicantBot(@Value("${bot.token}") String token, CommandMatcher commandMatcher, FileService fileService) {
        super(token);
        this.commandMatcher = commandMatcher;
        this.fileService = fileService;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Long chatId = update.getMessage().getChatId();
            String messageText = update.getMessage().getText();

            this.commandMatcher.match(update, messageText);
        } else if (update.hasCallbackQuery()) {
            String callbackData = update.getCallbackQuery().getData();
            Long chatId = update.getCallbackQuery().getMessage().getChatId();

            this.commandMatcher.matchCallback(chatId, callbackData);
        }
    }

    @Override
    public String getBotUsername() {
        return this.userName;
    }

    public void sendMessage(Long chatId, String text) {
        SendMessage sendMessage = this.createSendMessage(chatId, text);

        try {
            execute(sendMessage);
        } catch (TelegramApiException telegramApiException) {
            System.out.println("Error: " + telegramApiException.getMessage());
        }
    }

    public void sendDocument(Long chatId, String path, String messageCaption) {
        SendDocument sendDocument = new SendDocument();
        sendDocument.setChatId(chatId);
        sendDocument.setCaption(messageCaption);

        try {
            InputFile file = this.fileService.getFileByPath(path);
            sendDocument.setDocument(file);
        } catch (IOException exception) {
            System.out.println("problem with file by path:" + path);
            return;
        }

        try {
            execute(sendDocument);
        } catch (TelegramApiException telegramApiException) {
            System.out.println("Error: " + telegramApiException.getMessage());
        }
    }

    public void Greeting(Long chatId) {
        String text = "Привіт, я бот абітурієнт чдту";
        SendMessage sendMessage = this.createSendMessage(chatId, text);
        InlineKeyboardMarkup inlineKeyboardMarkup = this.getGreetingReplyMarkup();
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);

        try {
            execute(sendMessage);
        } catch (TelegramApiException telegramApiException) {
            System.out.println("Error: " + telegramApiException.getMessage());
        }
    }

    private InlineKeyboardMarkup getGreetingReplyMarkup() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> buttons = new ArrayList<>();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        InlineKeyboardButton getSpecialityButton = new InlineKeyboardButton("Дізнатися про всі спецільності");
        getSpecialityButton.setCallbackData("/getSpeciality");

        buttons.add(getSpecialityButton);
        keyboard.add(buttons);
        inlineKeyboardMarkup.setKeyboard(keyboard);

        return inlineKeyboardMarkup;
    }

    private SendMessage createSendMessage(Long chatId, String text) {
        String chatIdStr = chatId.toString();

        return new SendMessage(chatIdStr, text);
    }
}
