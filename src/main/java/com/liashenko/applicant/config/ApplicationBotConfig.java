package com.liashenko.applicant.config;

import com.liashenko.applicant.bot.ApplicantBot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class ApplicationBotConfig {
    @Bean
    public TelegramBotsApi telegramBotsApi(ApplicantBot applicantBot) throws TelegramApiException {
        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(applicantBot);

        return api;
    }
}
