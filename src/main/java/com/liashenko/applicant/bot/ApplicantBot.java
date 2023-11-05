package com.liashenko.applicant.bot;

import com.liashenko.applicant.command.CommandMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class ApplicantBot extends TelegramLongPollingBot {
    private final CommandMatcher commandMatcher;

    @Value("${bot.userName}")
    private String userName;

    @Autowired
    public ApplicantBot(@Value("${bot.token}") String token, CommandMatcher commandMatcher) {
        super(token);
        this.commandMatcher = commandMatcher;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage()) return;

        Long chatId = update.getMessage().getChatId();
        String messageText = update.getMessage().getText();

        this.commandMatcher.match(chatId, messageText);
    }

    @Override
    public String getBotUsername() {
        return this.userName;
    }

    public void sendMessage(Long chatId, String text) {
        String chatIdStr = chatId.toString();
        SendMessage sendMessage = new SendMessage(chatIdStr, text);

        try {
            execute(sendMessage);
        } catch (TelegramApiException telegramApiException) {
            System.out.println("Error: " + telegramApiException.getMessage());
        }
    }
}
