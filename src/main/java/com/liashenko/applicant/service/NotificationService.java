package com.liashenko.applicant.service;

import com.liashenko.applicant.bot.ApplicantBot;
import com.liashenko.applicant.dtos.response.OpenDayResponseDto;
import com.liashenko.applicant.dtos.response.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    private final UserService userService;
    private final OpenDayService openDayService;
    private final ApplicantBot applicantBot;
    private final TextFormatService textFormatService;

    @Autowired
    public NotificationService(
            UserService userService,
            OpenDayService openDayService,
            ApplicantBot applicantBot,
            TextFormatService textFormatService
    ) {
        this.userService = userService;
        this.openDayService = openDayService;
        this.applicantBot = applicantBot;
        this.textFormatService = textFormatService;
    }

    @Scheduled(cron = "0 8,20 * * *")
    public void sendNotification() {
        int differenceDay = 3;
        List<OpenDayResponseDto> openDayResponseDtos = this.openDayService.getByDateDifferenceFromCurrentDate(differenceDay);

        if (openDayResponseDtos.isEmpty()) return;

        OpenDayResponseDto nearDay = openDayResponseDtos.get(0);
        List<UserResponseDto> users = this.userService.getUserWithEnableNotification();

        for (UserResponseDto userResponseDto: users) {
            String chatId = userResponseDto.getChatId();
            String text = this.textFormatService.OpenDayResponseDtoToText(nearDay);
            this.applicantBot.sendMessage(Long.valueOf(chatId), text);
        }
    }
}
