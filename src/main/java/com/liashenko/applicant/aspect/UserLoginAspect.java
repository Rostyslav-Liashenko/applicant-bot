package com.liashenko.applicant.aspect;


import com.liashenko.applicant.dtos.response.UserResponseDto;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserLoginAspect {
    private final Logger logger = LoggerFactory.getLogger(UserLoginAspect.class);

    @Pointcut("execution(* com.liashenko.applicant.service.UserService.create(..))")
    public void createUser() {}

    @AfterReturning(pointcut = "createUser()", returning = "userResponseDto")
    public void logUserCreation(JoinPoint joinPoint, UserResponseDto userResponseDto) {
        logger.info("User creation method called: {}", joinPoint.getSignature().toShortString());

        Object[] methodArgs = joinPoint.getArgs();

        if (methodArgs.length > 0) {
            logger.info("UserRequestDto: {}", methodArgs[0]);
        }

        if (userResponseDto != null) {
            logger.info("User created: {}", userResponseDto);
        } else {
            logger.warn("User creation failed.");
        }
    }
}
