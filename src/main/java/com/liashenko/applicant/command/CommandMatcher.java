package com.liashenko.applicant.command;

import com.liashenko.applicant.bot.ApplicantBot;
import com.liashenko.applicant.dtos.response.EducationProgramResponseDto;
import com.liashenko.applicant.service.EducationProgramService;
import com.liashenko.applicant.service.TextFormatService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class CommandMatcher {
    private final EducationProgramService educationProgramService;
    private final ApplicantBot applicantBot;
    private final TextFormatService textFormatService;

    @Autowired
    public CommandMatcher(TextFormatService textFormatService, EducationProgramService educationProgramService, @Lazy ApplicantBot applicantBot) {
        this.applicantBot = applicantBot;
        this.educationProgramService = educationProgramService;
        this.textFormatService = textFormatService;
    }

    public void match(Long chatId, String commandKey) {
        if (commandKey.equals("/getEducationPrograms")) {
            this.handleEducationPrograms(chatId);
        }
    }

    public void handleEducationPrograms(Long chatId) {
        List<EducationProgramResponseDto> educationProgramResponseDtos = this.educationProgramService.getAll();
        String messageText = this.textFormatService.EducationProgramDtosToText(educationProgramResponseDtos);
        this.applicantBot.sendMessage(chatId, messageText);
    }
}
