package com.liashenko.applicant.command;

import com.liashenko.applicant.bot.ApplicantBot;
import com.liashenko.applicant.dtos.response.CostEducationResponseDto;
import com.liashenko.applicant.dtos.response.DocumentAdmissionResponseDto;
import com.liashenko.applicant.dtos.response.EducationProgramResponseDto;
import com.liashenko.applicant.dtos.response.SpecialityResponseDto;
import com.liashenko.applicant.service.*;
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
    private final CostEducationService costEducationService;
    private final InfoService infoService;
    private final ApplicantBot applicantBot;
    private final TextFormatService textFormatService;

    @Autowired
    public CommandMatcher(
            TextFormatService textFormatService,
            EducationProgramService educationProgramService,
            DocumentAdmissionService documentAdmissionService,
            SpecialityService specialityService,
            CostEducationService costEducationService,
            InfoService infoService,
            @Lazy ApplicantBot applicantBot
    ) {
        this.applicantBot = applicantBot;
        this.educationProgramService = educationProgramService;
        this.documentAdmissionService = documentAdmissionService;
        this.costEducationService = costEducationService;
        this.specialityService = specialityService;
        this.textFormatService = textFormatService;
        this.infoService = infoService;
    }

    public void match(Long chatId, String commandKey) {
        switch (commandKey) {
            case "/getEducationPrograms" -> this.handleEducationPrograms(chatId);
            case "/getDocumentAdmission" -> this.handleAdmissionDocument(chatId);
            case "/getSpeciality" -> this.handleSpeciality(chatId);
            case "/getCostEducation" -> this.handleCostEducation(chatId);
            case "/getConsultationCenterSchedule" -> this.handleConsultationCenterSchedule(chatId);
            case "/start" -> this.handleGreeting(chatId);
        }
    }

    public void matchCallback(Long chatId, String callbackName) {
        switch (callbackName) {
            case "/getSpeciality" -> this.handleSpeciality(chatId);
            case "/getDocumentAdmission" -> this.handleAdmissionDocument(chatId);
        }
    }

    public void handleEducationPrograms(Long chatId) {
        List<EducationProgramResponseDto> educationProgramResponseDtos = this.educationProgramService.getAll();
        int countShiftText = 0;
        String messageText = this.textFormatService.educationProgramDtosToText(educationProgramResponseDtos, countShiftText);
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

    public void handleCostEducation(Long chatId) {
        List<CostEducationResponseDto> costEducationResponseDtos = this.costEducationService.getAll();

        for (CostEducationResponseDto costEducationResponseDto: costEducationResponseDtos) {
            String path = costEducationResponseDto.getPathToFile();
            String caption = costEducationResponseDto.getDescription();
            this.applicantBot.sendDocument(chatId, path, caption);
        }
    }

    public void handleGreeting(Long chatId) {
        this.applicantBot.Greeting(chatId);
    }

    public void handleConsultationCenterSchedule(Long chatId) {
        String messageAboutConsultationCenterSchedule = this.infoService.getConsultationCenterSchedule();
        this.applicantBot.sendMessage(chatId, messageAboutConsultationCenterSchedule);
    }
}
