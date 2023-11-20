package com.liashenko.applicant.aspect;

import com.liashenko.applicant.dtos.response.UserResponseDto;
import com.liashenko.applicant.service.UserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class NotificationLoggingAspect {
    private final Logger logger = LoggerFactory.getLogger(NotificationLoggingAspect.class);
    private final UserService userService;

    @Autowired
    public NotificationLoggingAspect(UserService userService) {
        this.userService = userService;
    }

    @Pointcut("execution(* com.liashenko.applicant.service.NotificationService.sendNotification(..))")
    public void afterSuccessSendNotification() {}

    @AfterReturning(pointcut = "afterSuccessSendNotification()", returning = "result")
    public void logNotification() {
        List<UserResponseDto> users = this.userService.getUserWithEnableNotification();

        logger.info("Sending notification to users:");
        for (UserResponseDto userResponseDto : users) {
            String chatId = userResponseDto.getChatId();
            logger.info("User with chatId {}: {}", chatId, userResponseDto);
        }
    }
}
