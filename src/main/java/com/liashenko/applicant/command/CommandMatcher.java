package com.liashenko.applicant.command;

import com.liashenko.applicant.bot.ApplicantBot;
import com.liashenko.applicant.dtos.response.DocumentAdmissionResponseDto;
import com.liashenko.applicant.dtos.response.EducationProgramResponseDto;
import com.liashenko.applicant.dtos.response.SpecialityResponseDto;
import com.liashenko.applicant.service.DocumentAdmissionService;
import com.liashenko.applicant.service.EducationProgramService;
import com.liashenko.applicant.service.SpecialityService;
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
    private final DocumentAdmissionService documentAdmissionService;
    private final SpecialityService specialityService;
    private final ApplicantBot applicantBot;
    private final TextFormatService textFormatService;

    @Autowired
    public CommandMatcher(
            TextFormatService textFormatService,
            EducationProgramService educationProgramService,
            DocumentAdmissionService documentAdmissionService,
            SpecialityService specialityService,
            @Lazy ApplicantBot applicantBot
    ) {
        this.applicantBot = applicantBot;
        this.educationProgramService = educationProgramService;
        this.documentAdmissionService = documentAdmissionService;
        this.specialityService = specialityService;
        this.textFormatService = textFormatService;
    }

    public void match(Long chatId, String commandKey) {
        if (commandKey.equals("/getEducationPrograms")) {
            this.handleEducationPrograms(chatId);
        } else if (commandKey.equals("/getDocumentAdmission")) {
            this.handleAdmissionDocument(chatId);
        } else if (commandKey.equals("/getSpeciality")) {
            this.handleSpeciality(chatId);
        }
    }

    public void handleEducationPrograms(Long chatId) {
        List<EducationProgramResponseDto> educationProgramResponseDtos = this.educationProgramService.getAll();
        String messageText = this.textFormatService.educationProgramDtosToText(educationProgramResponseDtos);
        this.applicantBot.sendMessage(chatId, messageText);
    }

    public void handleAdmissionDocument(Long chatId) {
        List<DocumentAdmissionResponseDto> documentAdmissionResponseDtos = this.documentAdmissionService.getAll();
        String messageText = this.textFormatService.documentAdmissionDtosToText(documentAdmissionResponseDtos);
        this.applicantBot.sendMessage(chatId, messageText);
    }

    public void handleSpeciality(Long chatId) {
        List<SpecialityResponseDto> specialityResponseDtos = this.specialityService.getAll();
        String messageText = this.textFormatService.specialityDtosToText(specialityResponseDtos);
        this.applicantBot.sendMessage(chatId, messageText);
    }
}
