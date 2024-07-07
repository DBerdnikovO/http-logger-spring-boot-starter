package ru.berdnikov.httploggerspringbootstarter.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author danilaberdnikov on FilterConfig.
 * @project http-logger-spring-boot-starter
 */
@Configuration
@RequiredArgsConstructor
public class FilterConfig {
    private final LogFilter logFilter;

    @Bean
    public FilterRegistrationBean<LogFilter> loggingFilter() {
        FilterRegistrationBean<LogFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(logFilter);
        registrationBean.addUrlPatterns("/*");

        return registrationBean;
    }
}
