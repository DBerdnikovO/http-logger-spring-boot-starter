package ru.berdnikov.httploggerspringbootstarter.config;

import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.berdnikov.httploggerspringbootstarter.interceptor.HttpHandlerInterceptor;

/**
 * @author danilaberdnikov on AppWebHandlerConfiguration.
 * @project http-logger-spring-boot-starter
 */
@RequiredArgsConstructor
public class AppWebHandlerConfiguration implements WebMvcConfigurer {
    private final HttpHandlerInterceptor httpHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(httpHandlerInterceptor);
    }
}
