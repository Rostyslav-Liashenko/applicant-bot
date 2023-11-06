package com.liashenko.applicant.command;

import com.liashenko.applicant.bot.ApplicantBot;

public class CommandGreeting implements Command {
    private final ApplicantBot applicantBot;
    private final Long chatId;

    public CommandGreeting(Long chatId, ApplicantBot applicantBot) {
        this.chatId = chatId;
        this.applicantBot = applicantBot;
    }

    @Override
    public void execute() {
        String author = "Цей бот був створенний студентом Ляшенко Р.І. групи КНС-2201.\n";
        String goal = "Бот призначений для використанням абітурієнтом ЧДТУ.";

        String message = author + goal;
        this.applicantBot.sendMessage(chatId, message);
    }
}
