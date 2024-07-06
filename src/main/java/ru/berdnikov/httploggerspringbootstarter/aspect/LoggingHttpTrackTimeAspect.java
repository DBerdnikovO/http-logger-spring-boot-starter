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

import java.util.Enumeration;

/**
 * @author danilaberdnikov on LoggingHttpTrackTimeAspect.
 * @project http-logger-spring-boot-starter
 */
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class LoggingHttpTrackTimeAspect {
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *) || within(@org.springframework.stereotype.Controller *)")
    public void controllerMethods() {}

    @Around("controllerMethods()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        for (Object arg : joinPoint.getArgs()) {
            if (arg instanceof HttpServletRequest) {
                request = (HttpServletRequest) arg;
            } else if (arg instanceof HttpServletResponse) {
                response = (HttpServletResponse) arg;
            }
        }

        long startTime = System.currentTimeMillis();
//        if (request != null) {
            logRequestDetails(request);
//        }

        Object result = joinPoint.proceed();

        if (response != null) {
            long endTime = System.currentTimeMillis();
            logResponseDetails(response, endTime - startTime);
        }

        return result;
    }

    private void logRequestDetails(HttpServletRequest request) {
        log.info("------HTTP Request logging start------");
        log.info("METHOD type: {}", request.getMethod());
        log.info("Request URI: {}", request.getRequestURI());
        log.info("Servlet PATH: {}", request.getServletPath());

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            log.info("Request Header: {} = {}", headerName, headerValue);
        }
    }

    private void logResponseDetails(HttpServletResponse response, long duration) {
        log.info("Response Status: {}", response.getStatus());

        response.getHeaderNames().forEach(headerName -> {
            String headerValue = response.getHeader(headerName);
            log.info("Response Header: {} = {}", headerName, headerValue);
        });

        log.info("Request processing time: {} ms", duration);
        log.info("------HTTP Request logging stop------");
    }
}
