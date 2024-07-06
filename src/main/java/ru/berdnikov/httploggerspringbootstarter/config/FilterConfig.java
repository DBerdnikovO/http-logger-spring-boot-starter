//package ru.berdnikov.httploggerspringbootstarter.config;
//
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author danilaberdnikov on FilterConfig.
// * @project http-logger-spring-boot-starter
// */
//@Configuration
//public class FilterConfig {
//    @Bean
//    public FilterRegistrationBean<LogFilter> loggingFilter(){
//        FilterRegistrationBean<LogFilter> registrationBean = new FilterRegistrationBean<>();
//
//        registrationBean.setFilter(new LogFilter());
//        registrationBean.addUrlPatterns("/*"); // Укажите URL-паттерны для фильтрации
//
//        return registrationBean;
//    }
//}
