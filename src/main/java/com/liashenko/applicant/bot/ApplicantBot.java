package com.liashenko.applicant.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class ApplicantBot extends TelegramLongPollingBot {
    @Value("${bot.userName}")
    private String userName;

    public ApplicantBot(@Value("${bot.token}") String token) {
        super(token);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage()) return;

        String messageText = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();
    }

    @Override
    public String getBotUsername() {
        return this.userName;
    }

    private void sendMessage(Long chatId, String text) {
        String chatIdStr = chatId.toString();
        SendMessage sendMessage = new SendMessage(chatIdStr, text);

        try {
            execute(sendMessage);
        } catch (TelegramApiException telegramApiException) {
            System.out.println("Error: " + telegramApiException.getMessage());
        }
    }
}
