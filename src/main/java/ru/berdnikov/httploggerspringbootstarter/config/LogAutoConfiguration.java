package ru.berdnikov.httploggerspringbootstarter.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import ru.berdnikov.httploggerspringbootstarter.aspect.LogHttpAspect;
import ru.berdnikov.httploggerspringbootstarter.condition.ConditionalOnLog;
import ru.berdnikov.httploggerspringbootstarter.filter.HttpLogFilter;
import ru.berdnikov.httploggerspringbootstarter.interceptor.HttpHandlerInterceptor;
import ru.berdnikov.httploggerspringbootstarter.logger.HttpExecutionTimeLogging;
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
    public HttpExecutionTimeLogging httpTimingLogging() {
        return new HttpExecutionTimeLogging();
    }

    @Bean
    @ConditionalOnLog(LogType.FILTER)
    public HttpLogFilter logFilter(HttpRequestAndResponseLogDetails httpRequestAndResponseLogDetails,
                                   HttpExecutionTimeLogging httpExecutionTimeLogging) {
        return new HttpLogFilter(httpRequestAndResponseLogDetails, httpExecutionTimeLogging);
    }

    @Bean
    @ConditionalOnLog(LogType.INTERCEPTOR)
    public HttpHandlerInterceptor requestInterceptor(HttpRequestAndResponseLogDetails httpRequestAndResponseLogDetails,
                                                     HttpExecutionTimeLogging httpExecutionTimeLogging) {
        return new HttpHandlerInterceptor(httpRequestAndResponseLogDetails, httpExecutionTimeLogging);
    }

    @Bean
    @ConditionalOnLog(LogType.ASPECT)
    public LogHttpAspect metricsAspect(HttpRequestAndResponseLogDetails httpRequestAndResponseLogDetails,
                                       HttpExecutionTimeLogging httpExecutionTimeLogging) {
        return new LogHttpAspect(httpRequestAndResponseLogDetails, httpExecutionTimeLogging);
    }

    @Bean
    @ConditionalOnLog(LogType.FILTER)
    public AppFilterConfiguration filterConfig(HttpLogFilter httpLogFilter) {
        return new AppFilterConfiguration(httpLogFilter);
    }

    @Bean
    @ConditionalOnLog(LogType.INTERCEPTOR)
    public AppWebHandlerConfiguration webConfig(HttpHandlerInterceptor httpHandlerInterceptor) {
        return new AppWebHandlerConfiguration(httpHandlerInterceptor);
    }
}