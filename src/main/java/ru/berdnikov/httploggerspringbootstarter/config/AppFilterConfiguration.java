package ru.berdnikov.httploggerspringbootstarter.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import ru.berdnikov.httploggerspringbootstarter.filter.HttpFilter;

/**
 * @author danilaberdnikov on AppFilterConfiguration.
 * @project http-logger-spring-boot-starter
 */
@RequiredArgsConstructor
public class AppFilterConfiguration {
    private final HttpFilter httpFilter;

    @Bean
    public FilterRegistrationBean<HttpFilter> loggingFilter() {
        FilterRegistrationBean<HttpFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(httpFilter);
        registrationBean.addUrlPatterns("/*");

        return registrationBean;
    }
}
