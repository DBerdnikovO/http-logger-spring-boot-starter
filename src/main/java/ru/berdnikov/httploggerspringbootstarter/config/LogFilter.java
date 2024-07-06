//package ru.berdnikov.httploggerspringbootstarter.config;
//
//import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.util.Enumeration;
//
///**
// * @author danilaberdnikov on LogFilter.
// * @project http-logger-spring-boot-starter
// */
//@Slf4j
//@Component
//public class LogFilter implements Filter {
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//
//    }
//
////    @Override
////    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
////            throws IOException, ServletException {
////        HttpServletRequest httpRequest = (HttpServletRequest) request;
////        HttpServletResponse httpResponse = (HttpServletResponse) response;
////
////        long startTime = System.currentTimeMillis();
////
////        logRequestDetails(httpRequest);
////
////        chain.doFilter(request, response);
////
////        long endTime = System.currentTimeMillis();
////        long duration = endTime - startTime;
////
////        logResponseDetails(httpResponse, duration);
////    }
////
////    private void logRequestDetails(HttpServletRequest request) {
////        log.info("------HTTP Request logging start------");
////        log.info("METHOD type: {}", request.getMethod());
////        log.info("Request URI: {}", request.getRequestURI());
////        log.info("Servlet PATH: {}", request.getServletPath());
////
////        Enumeration<String> headerNames = request.getHeaderNames();
////        while (headerNames.hasMoreElements()) {
////            String headerName = headerNames.nextElement();
////            String headerValue = request.getHeader(headerName);
////            log.info("Request Header: {} = {}", headerName, headerValue);
////        }
////    }
////
////    private void logResponseDetails(HttpServletResponse response, long duration) {
////        log.info("Response Status: {}", response.getStatus());
////
////        response.getHeaderNames().forEach(headerName -> {
////            String headerValue = response.getHeader(headerName);
////            log.info("Response Header: {} = {}", headerName, headerValue);
////        });
////
////        log.info("Request processing time: {} ms", duration);
////        log.info("------HTTP Request logging stop------");
////    }
//}
