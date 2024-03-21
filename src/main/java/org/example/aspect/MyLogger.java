package org.example.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.example.entity.LoginLogger;
import org.example.service.LoginLoggerService;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;

@Aspect
@Slf4j
@Component
@RequiredArgsConstructor
public class MyLogger {

    private final LoginLoggerService loginLoggerService;

    @Around(value = "@annotation(org.example.aspect.ToLogOurApp)")
    public Object myLoggerMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Aspect is working");

        String answer = (String) joinPoint.proceed();
        String request = "Login -> " + joinPoint.getArgs()[0].toString().split(", ")[5];

        log.info("\nArgs -> {}\nAnswer - {}\nTime - {}\n", request, answer, LocalDate.now());

        LoginLogger loginLogger = new LoginLogger();
        loginLogger.setArgs(request);
        loginLogger.setResponse(answer);
        loginLogger.setCreatedAt(LocalDateTime.now().toString());

        loginLoggerService.saveLog(loginLogger);

        return answer;
    }

}
