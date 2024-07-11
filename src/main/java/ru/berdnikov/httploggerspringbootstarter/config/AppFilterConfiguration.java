package ru.berdnikov.httploggerspringbootstarter.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import ru.berdnikov.httploggerspringbootstarter.filter.HttpLogFilterImpl;

/**
 * @author danilaberdnikov on AppFilterConfiguration.
 * @project http-logger-spring-boot-starter
 */
@RequiredArgsConstructor
public class AppFilterConfiguration {
    private final HttpLogFilterImpl httpLogFilterImpl;

    @Bean
    public FilterRegistrationBean<HttpLogFilterImpl> loggingFilter() {
        FilterRegistrationBean<HttpLogFilterImpl> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(httpLogFilterImpl);
        registrationBean.addUrlPatterns("/*");

        return registrationBean;
    }
}
