package ru.berdnikov.httploggerspringbootstarter.aspect;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ru.berdnikov.httploggerspringbootstarter.logger.HttpLogger;

/**
 * @author danilaberdnikov on MetricsAspect.
 * @project http-logger-spring-boot-starter
 */
@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class MetricsAspect {
    private final HttpLogger httpLogger;

    @Pointcut("execution(* jakarta.servlet.http.HttpServlet.*(..)) *)")
    public void servletService() {
    }

    @Around("servletService()")
    public Object logAround(ProceedingJoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();

        log.info("------Aspect logging enable------");
        httpLogger.logRequestDetails(request);
        httpLogger.logResponseDetails(response);

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            httpLogger.measureExecutionTime(stopWatch);
        }
    }
}
