//package ru.berdnikov.httploggerspringbootstarter.config;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StopWatch;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.util.Enumeration;
//
//
///**
// * @author danilaberdnikov on RequestInterceptor.
// * @project http-logger-spring-boot-starter
// */
//@Slf4j
//@Component
//public class RequestInterceptor implements HandlerInterceptor {
////
////    private static final String START_TIME_ATTRIBUTE = "startTime";
////
////    @Override
////    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
////        long startTime = System.currentTimeMillis();
////        request.setAttribute(START_TIME_ATTRIBUTE, startTime);
////
////        logRequestDetails(request);
////
////        if (handler instanceof HandlerMethod) {
////            Class<?> controllerClass = ((HandlerMethod) handler).getBeanType();
////            String methodName = ((HandlerMethod) handler).getMethod().getName();
////            log.info("Controller name: {}", controllerClass.getName());
////            log.info("Method name: {}", methodName);
////        }
////        return true;
////    }
////
////    @Override
////    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
////        long startTime = (Long) request.getAttribute(START_TIME_ATTRIBUTE);
////        long endTime = System.currentTimeMillis();
////        long executeTime = endTime - startTime;
////        log.info("Request URL: {} ,Before view render time taken: {} ms", request.getRequestURL().toString(), executeTime);
////    }
////
////    @Override
////    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
////        long startTime = (Long) request.getAttribute(START_TIME_ATTRIBUTE);
////        long endTime = System.currentTimeMillis();
////        long executeTime = endTime - startTime;
////
////        log.info("Request URL: {} ,Full time taken: {} ms", request.getRequestURL().toString(), executeTime);
////        logResponseDetails(response);
////
////        if (ex != null) {
////            log.error("An error occurred: ", ex);
////        }
////        log.info("------HTTP Request logging stop------");
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
////    private void logResponseDetails(HttpServletResponse response) {
////        log.info("Response Status: {}", response.getStatus());
////
////        response.getHeaderNames().forEach(headerName -> {
////            String headerValue = response.getHeader(headerName);
////            log.info("Response Header: {} = {}", headerName, headerValue);
////        });
////    }
//}
