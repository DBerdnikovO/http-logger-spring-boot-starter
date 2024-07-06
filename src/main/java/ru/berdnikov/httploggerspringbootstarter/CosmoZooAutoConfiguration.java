package ru.berdnikov.httploggerspringbootstarter;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import ru.berdnikov.httploggerspringbootstarter.aspect.LoggingHttpTrackTimeAspect;

@AutoConfiguration
public class CosmoZooAutoConfiguration {

    @Bean
    public ComsoZoo comsoZoo() {
        System.out.println("Creating ComsoZoo bean...");
        return new ComsoZoo();
    }

    @Bean
    @ConditionalOnMissingBean
    public LoggingHttpTrackTimeAspect LoggingTrackTimeAspect() {
        return new LoggingHttpTrackTimeAspect();
    }
}