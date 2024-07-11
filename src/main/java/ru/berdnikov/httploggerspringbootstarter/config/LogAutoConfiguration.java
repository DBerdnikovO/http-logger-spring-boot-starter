package ru.berdnikov.httploggerspringbootstarter.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import ru.berdnikov.httploggerspringbootstarter.aspect.LogHttpAspect;
import ru.berdnikov.httploggerspringbootstarter.condition.ConditionalOnLog;
import ru.berdnikov.httploggerspringbootstarter.filter.HttpLogFilterImpl;
import ru.berdnikov.httploggerspringbootstarter.interceptor.HttpHandlerInterceptor;
import ru.berdnikov.httploggerspringbootstarter.logger.HttpExecutionTimingLogging;
import ru.berdnikov.httploggerspringbootstarter.logger.HttpRequestAndResponseLogDetails;
import ru.berdnikov.httploggerspringbootstarter.logger.LogType;

@AutoConfiguration
@EnableConfigurationProperties(LogProperties.class)
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "logger", value = "enabled", havingValue = "true")
public class LogAutoConfiguration {
    @Bean
    public HttpRequestAndResponseLogDetails httpRequestAndResponseLogging() {
        return new HttpRequestAndResponseLogDetails();
    }

    @Bean
    public HttpExecutionTimingLogging httpTimingLogging() {
        return new HttpExecutionTimingLogging();
    }

    @Bean
    @ConditionalOnLog(LogType.FILTER)
    public HttpLogFilterImpl logFilter(HttpRequestAndResponseLogDetails httpRequestAndResponseLogDetails,
                                       HttpExecutionTimingLogging httpExecutionTimingLogging) {
        return new HttpLogFilterImpl(httpRequestAndResponseLogDetails, httpExecutionTimingLogging);
    }

    @Bean
    @ConditionalOnLog(LogType.INTERCEPTOR)
    public HttpHandlerInterceptor requestInterceptor(HttpRequestAndResponseLogDetails httpRequestAndResponseLogDetails,
                                                     HttpExecutionTimingLogging httpExecutionTimingLogging) {
        return new HttpHandlerInterceptor(httpRequestAndResponseLogDetails, httpExecutionTimingLogging);
    }

    @Bean
    @ConditionalOnLog(LogType.ASPECT)
    public LogHttpAspect metricsAspect(HttpRequestAndResponseLogDetails httpRequestAndResponseLogDetails,
                                       HttpExecutionTimingLogging httpExecutionTimingLogging) {
        return new LogHttpAspect(httpRequestAndResponseLogDetails, httpExecutionTimingLogging);
    }

    @Bean
    @ConditionalOnLog(LogType.FILTER)
    public AppFilterConfiguration filterConfig(HttpLogFilterImpl httpLogFilterImpl) {
        return new AppFilterConfiguration(httpLogFilterImpl);
    }

    @Bean
    @ConditionalOnLog(LogType.INTERCEPTOR)
    public AppWebHandlerConfiguration webConfig(HttpHandlerInterceptor httpHandlerInterceptor) {
        return new AppWebHandlerConfiguration(httpHandlerInterceptor);
    }
}