package com.liashenko.applicant.command;

import com.liashenko.applicant.bot.ApplicantBot;
import com.liashenko.applicant.dtos.request.UserRequestDto;
import com.liashenko.applicant.dtos.response.*;
import com.liashenko.applicant.service.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.Optional;

@Data
@Component
public class CommandMatcher {
    private final EducationProgramService educationProgramService;
    private final DocumentAdmissionService documentAdmissionService;
    private final SpecialityService specialityService;
    private final CostEducationService costEducationService;
    private final AdmissionRuleService admissionRuleService;
    private final OpenDayService openDayService;
    private final UserService userService;
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
            AdmissionRuleService admissionRuleService,
            UserService userService,
            OpenDayService openDayService,
            InfoService infoService,
            @Lazy ApplicantBot applicantBot
    ) {
        this.applicantBot = applicantBot;
        this.educationProgramService = educationProgramService;
        this.documentAdmissionService = documentAdmissionService;
        this.costEducationService = costEducationService;
        this.specialityService = specialityService;
        this.openDayService = openDayService;
        this.userService = userService;
        this.textFormatService = textFormatService;
        this.admissionRuleService = admissionRuleService;
        this.infoService = infoService;
    }

    public void match(Update update, String commandKey) {
        Message message = update.getMessage();
        Long chatId = message.getChatId();

        switch (commandKey) {
            case "/get_education_programs" -> this.handleEducationPrograms(chatId);
            case "/get_document_admission" -> this.handleAdmissionDocument(chatId);
            case "/get_speciality" -> this.handleSpeciality(chatId);
            case "/get_cost_education" -> this.handleCostEducation(chatId);
            case "/get_consultation_center_schedule" -> this.handleConsultationCenterSchedule(chatId);
            case "/get_admission_rules" -> this.handleAdmissionRules(chatId);
            case "/get_open_days" -> this.handleOpenDays(chatId);
            case "/enable_notifications" -> this.handleEnableNotification(update);
            case "/disabled_notifications" -> this.handleDisabledNotification(chatId);
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
            this.handleDocument(chatId, costEducationResponseDto);
        }
    }

    public void handleGreeting(Long chatId) {
        this.applicantBot.Greeting(chatId);
    }

    public void handleConsultationCenterSchedule(Long chatId) {
        String messageAboutConsultationCenterSchedule = this.infoService.getConsultationCenterSchedule();
        this.applicantBot.sendMessage(chatId, messageAboutConsultationCenterSchedule);
    }

    public void handleAdmissionRules(Long chatId) {
        List<AdmissionRuleResponseDto> admissionRules = this.admissionRuleService.getAll();

        for (AdmissionRuleResponseDto admissionRuleResponseDto: admissionRules) {
            this.handleDocument(chatId, admissionRuleResponseDto);
        }
    }

    public void handleOpenDays(Long chatId) {
        List<OpenDayResponseDto> openDayResponseDtos = this.openDayService.getAll();
        String messageText = this.textFormatService.OpenDayDtosToText(openDayResponseDtos);
        this.applicantBot.sendMessage(chatId, messageText);
    }

    public void handleEnableNotification(Update update) {
        Long chatId = update.getMessage().getChatId();

        Optional<UserResponseDto> optionalUserResponseDto = this.userService.findByChatId(chatId);

        if (optionalUserResponseDto.isPresent()) {
            UserResponseDto userResponseDto = optionalUserResponseDto.get();
            String userChatId = userResponseDto.getChatId();

            this.userService.updateIsShowNotification(userChatId, true);
        } else {
            UserRequestDto userRequestDto = new UserRequestDto();

            userRequestDto.setChatId(chatId.toString());
            userRequestDto.setFirstName(update.getMessage().getFrom().getFirstName());
            userRequestDto.setLastName(update.getMessage().getFrom().getLastName());
            userRequestDto.setShowNotification(true);

            this.userService.create(userRequestDto);
        }

        String text = "Ви успішно ввімкнули Увідомлення. Ми повідомимо вам про \"День відкритих дверей \" завчасно.";
        this.applicantBot.sendMessage(chatId, text);
    }

    public void handleDisabledNotification(Long chatId) {
        this.userService.updateIsShowNotification(chatId.toString(), false);
        String text = "Ви вимкнули Увідомлення. Ми не будeмо вам більше повідомляти про \"День відкритих дверей \"";
        this.applicantBot.sendMessage(chatId, text);
    }

    private void handleDocument(Long chatId, DocumentResponseDto documentResponseDto) {
        String path = documentResponseDto.getPathToFile();
        String caption = documentResponseDto.getDescription();
        this.applicantBot.sendDocument(chatId, path, caption);
    }
}
