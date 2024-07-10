package ru.berdnikov.httploggerspringbootstarter.logger;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import ru.berdnikov.httploggerspringbootstarter.utils.EnvironmentValues;

import java.util.Enumeration;

/**
 * @author danilaberdnikov on HttpLogger.
 * @project http-logger-spring-boot-starter
 */
@Slf4j
@Component
public class HttpLogger implements HttpLogging, HttpTiming {
    @Override
    public void measureExecutionTime(StopWatch stopWatch) {
        stopWatch.stop();
        long executionTime = stopWatch.getTotalTimeMillis();
        log.info("Execute with time: {} ", executionTime);
    }

    @Override
    public void measureExecutionTime(ServletRequest request) {
        long startTime = (Long) request.getAttribute(EnvironmentValues.START_TIME_ATTRIBUTE);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        log.info("Execute with time: {} ms", executionTime);
    }

    @Override
    public void measureExecutionTime(long timeMils) {
        log.info("Execute with time: {} ms", timeMils);
    }

    @Override
    public void logRequestDetails(ServletRequest servletRequest) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

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
        log.info("------HTTP Request logging stop------");
    }

    @Override
    public void logResponseDetails(ServletResponse servletResponse) {
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        log.info("------HTTP Response logging start------");
        log.info("Response Status: {}", response.getStatus());

        response.getHeaderNames().forEach(headerName -> {
            String headerValue = response.getHeader(headerName);
            log.info("Response Header: {} = {}", headerName, headerValue);
        });

        log.info("------HTTP Response logging stop------");
    }
}
