package ru.berdnikov.httploggerspringbootstarter.aspect;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ru.berdnikov.httploggerspringbootstarter.exception.LogAspectException;
import ru.berdnikov.httploggerspringbootstarter.logger.HttpExecutionTiming;
import ru.berdnikov.httploggerspringbootstarter.logger.HttpLogDetails;

/**
 * @author danilaberdnikov on LogHttpAspect.
 * @project http-logger-spring-boot-starter
 */
//+
@Aspect
@Slf4j
@RequiredArgsConstructor
public class LogHttpAspect {
    private final HttpLogDetails httpRequestAndResponseLogging;
    private final HttpExecutionTiming httpExecutionTimingLogging;

    @Pointcut("within(@org.springframework.stereotype.Controller *) || " +
            "within(@org.springframework.web.bind.annotation.RestController *)")
    public void controllerClasses() {
    }

    @Pointcut("execution(@(org.springframework.web.bind.annotation.RequestMapping || " +
            "org.springframework.web.bind.annotation.GetMapping || " +
            "org.springframework.web.bind.annotation.PostMapping || " +
            "org.springframework.web.bind.annotation.PutMapping || " +
            "org.springframework.web.bind.annotation.DeleteMapping) * *(..))")
    public void requestMappingMethods() {
    }

    @Around("controllerClasses() && requestMappingMethods()")
    public Object logRequestAndResponseAround(ProceedingJoinPoint joinPoint) {
        log.info("------Aspect logging enabled------");
        log.info("Aspect logs before (joinPoint.proceed()) starts");

        logRequestAndResponse();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            throw new LogAspectException(e);
        } finally {
            stopWatch.stop();
            log.info("Aspect logs after (joinPoint.proceed()) starts");
            logRequestAndResponse();
            httpExecutionTimingLogging.measureExecutionTime(stopWatch.getTotalTimeMillis());
        }
    }

    private void logRequestAndResponse() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes servletRequestAttributes) {
            HttpServletRequest request = servletRequestAttributes.getRequest();
            HttpServletResponse response = servletRequestAttributes.getResponse();
            httpRequestAndResponseLogging.logRequestAndResponseDetails(request, response);
        }
    }
}
