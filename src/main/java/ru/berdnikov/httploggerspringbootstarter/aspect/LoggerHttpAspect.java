package ru.berdnikov.httploggerspringbootstarter.aspect;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ru.berdnikov.httploggerspringbootstarter.exception.LoggerAspectException;
import ru.berdnikov.httploggerspringbootstarter.logger.HttpLogger;

import java.util.Arrays;

/**
 * @author danilaberdnikov on LoggerHttpAspect.
 * @project http-logger-spring-boot-starter
 */
@Aspect
@Slf4j
@Component
@RequiredArgsConstructor
public class LoggerHttpAspect {
    private final HttpLogger httpLogger;

    @Pointcut("within(@org.springframework.stereotype.Controller *) || " +
            "within(@org.springframework.web.bind.annotation.RestController *)")
    public void controllerClasses() {}

    @Pointcut("execution(@(org.springframework.web.bind.annotation.RequestMapping || " +
            "org.springframework.web.bind.annotation.GetMapping || " +
            "org.springframework.web.bind.annotation.PostMapping || " +
            "org.springframework.web.bind.annotation.PutMapping || " +
            "org.springframework.web.bind.annotation.DeleteMapping) * *(..))")
    public void requestMappingMethods() {}

    @Around("controllerClasses() && requestMappingMethods()")
    public Object logAround(ProceedingJoinPoint joinPoint) {
        log.info("------Aspect logging enabled------");

        logRequestAndResponse();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            throw new LoggerAspectException(e);
        } finally {
            stopWatch.stop();
            httpLogger.measureExecutionTime(stopWatch.getTotalTimeMillis());
        }
    }

    private void logRequestAndResponse() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes servletRequestAttributes) {
            HttpServletRequest request = servletRequestAttributes.getRequest();
            HttpServletResponse response = servletRequestAttributes.getResponse();
            httpLogger.logRequestDetails(request);
            httpLogger.logResponseDetails(response);
        }
    }
}
