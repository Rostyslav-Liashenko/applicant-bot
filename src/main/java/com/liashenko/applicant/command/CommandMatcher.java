package com.liashenko.applicant.command;

import com.liashenko.applicant.bot.ApplicantBot;
import com.liashenko.applicant.service.FacultyService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Data
@Component
public class CommandMatcher {
    private final FacultyService facultyService;
    private final ApplicantBot applicantBot;

    @Autowired
    public CommandMatcher(FacultyService facultyService, @Lazy ApplicantBot applicantBot) {
        this.facultyService = facultyService;
        this.applicantBot = applicantBot;
    }

    public void match(Long chatId, String commandKey) {
        if (commandKey.equals("/getInfoAboutSpeciality")) {
            CommandGetSpeciality commandGetSpeciality = new CommandGetSpeciality(chatId, facultyService, applicantBot);
            commandGetSpeciality.execute();
        } else if (commandKey.equals("/start")) {
            CommandGreeting commandGreeting = new CommandGreeting(chatId, applicantBot);
            commandGreeting.execute();
        }
    }
}
