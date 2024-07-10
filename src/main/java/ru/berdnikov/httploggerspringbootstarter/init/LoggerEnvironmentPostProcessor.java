package ru.berdnikov.httploggerspringbootstarter.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.stereotype.Component;
import ru.berdnikov.httploggerspringbootstarter.utils.EnvironmentValues;
import ru.berdnikov.httploggerspringbootstarter.utils.LoggerProperties;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author danilaberdnikov on LoggerEnvironmentPostProcessor.
 * @project http-logger-spring-boot-starter
 */
@Component
public class LoggerEnvironmentPostProcessor implements EnvironmentPostProcessor {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        var enabledProperty = Optional.ofNullable(environment.getProperty(LoggerProperties.ENABLED));
        var levelProperty = Optional.ofNullable(environment.getProperty(LoggerProperties.LEVEL));
        var levelSrcProperty = Optional.ofNullable(environment.getProperty(LoggerProperties.LEVEL_SRC));
        var formatProperty = Optional.ofNullable(environment.getProperty(LoggerProperties.FORMAT));

        boolean isEnabled = enabledProperty.isPresent();

        if (isEnabled) {
            Map<String, Object> logProperties = new HashMap<>();
            levelSrcProperty.ifPresent(s -> logProperties.put(s, levelProperty.isPresent() ? levelProperty : EnvironmentValues.LEVEL_INFO));
            formatProperty.ifPresent(p -> logProperties.put(EnvironmentValues.LOGGING_PATTERN, formatProperty));

            MapPropertySource propertySource = new MapPropertySource(EnvironmentValues.CUSTOM_PROPERTY_NAME, logProperties);
            environment.getPropertySources().addLast(propertySource);
        }
    }
}
