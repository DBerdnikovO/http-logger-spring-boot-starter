package ru.berdnikov.httploggerspringbootstarter.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import ru.berdnikov.httploggerspringbootstarter.filter.HttpLogFilter;

/**
 * @author danilaberdnikov on AppFilterConfiguration.
 * @project http-logger-spring-boot-starter
 */
@RequiredArgsConstructor
public class AppFilterConfiguration {
    private final HttpLogFilter httpLogFilter;

    @Bean
    public FilterRegistrationBean<HttpLogFilter> loggingFilter() {
        FilterRegistrationBean<HttpLogFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(httpLogFilter);
        registrationBean.addUrlPatterns("/*");

        return registrationBean;
    }
}
