package ru.berdnikov.httploggerspringbootstarter.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import ru.berdnikov.httploggerspringbootstarter.aspect.LoggerHttpAspect;
import ru.berdnikov.httploggerspringbootstarter.condition.ConditionalOnLogger;
import ru.berdnikov.httploggerspringbootstarter.filter.*;
import ru.berdnikov.httploggerspringbootstarter.interceptor.HttpHandlerInterceptor;
import ru.berdnikov.httploggerspringbootstarter.logger.HttpLogger;
import ru.berdnikov.httploggerspringbootstarter.logger.LoggerType;

@AutoConfiguration
@EnableConfigurationProperties(LoggerProperties.class)
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "logger", value = "enabled", havingValue = "true")
public class LoggerAutoConfiguration {
    @Bean
    public HttpLogger httpLogger() {
        return new HttpLogger();
    }

    @Bean
    @ConditionalOnLogger(LoggerType.FILTER)
    public HttpFilter logFilter(HttpLogger httpLogger) {
        return new HttpFilter(httpLogger);
    }

    @Bean
    @ConditionalOnLogger(LoggerType.INTERCEPTOR)
    public HttpHandlerInterceptor requestInterceptor(HttpLogger httpLogger) {
        return new HttpHandlerInterceptor(httpLogger);
    }

    @Bean
    @ConditionalOnLogger(LoggerType.ASPECT)
    public LoggerHttpAspect metricsAspect(HttpLogger httpLogger) {
        return new LoggerHttpAspect(httpLogger);
    }

    @Bean
    @ConditionalOnLogger(LoggerType.FILTER)
    public AppFilterConfiguration filterConfig(HttpFilter httpFilter) {
        return new AppFilterConfiguration(httpFilter);
    }

    @Bean
    @ConditionalOnLogger(LoggerType.INTERCEPTOR)
    public AppWebConfiguration webConfig(HttpHandlerInterceptor httpHandlerInterceptor) {
        return new AppWebConfiguration(httpHandlerInterceptor);
    }
}