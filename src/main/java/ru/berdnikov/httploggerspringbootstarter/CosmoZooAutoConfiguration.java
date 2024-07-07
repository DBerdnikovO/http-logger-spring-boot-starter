package ru.berdnikov.httploggerspringbootstarter;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import ru.berdnikov.httploggerspringbootstarter.aspect.MetricsAspect;
import ru.berdnikov.httploggerspringbootstarter.logger.HttpLogger;
//import ru.berdnikov.httploggerspringbootstarter.aspect.MetricsAspect;

@AutoConfiguration
@RequiredArgsConstructor
public class CosmoZooAutoConfiguration {
    @Bean
    public HttpLogger httpLogger() {
        return new HttpLogger();
    }

    @Bean
    public ComsoZoo comsoZoo() {
        System.out.println("Creating ComsoZoo bean...");
        return new ComsoZoo();
    }

    @Bean
    @ConditionalOnMissingBean
    public MetricsAspect LoggingTrackTimeAspect(HttpLogger httpLogger) {
        return new MetricsAspect(httpLogger);
    }
}