package ru.berdnikov.httploggerspringbootstarter.logger;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Enumeration;

/**
 * @author danilaberdnikov on HttpRequestAndResponseLogDetails.
 * @project http-logger-spring-boot-starter
 */
@Slf4j
public class HttpRequestAndResponseLogDetails implements HttpLogDetails {
    @Override
    public void logRequestAndResponseDetails(ServletRequest request, ServletResponse response) {
        logRequestDetails(request);
        logResponseDetails(response);
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
