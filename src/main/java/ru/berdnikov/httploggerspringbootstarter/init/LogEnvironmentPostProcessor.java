package ru.berdnikov.httploggerspringbootstarter.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.stereotype.Component;
import ru.berdnikov.httploggerspringbootstarter.exception.LogStartupException;
import ru.berdnikov.httploggerspringbootstarter.logger.LogLevel;
import ru.berdnikov.httploggerspringbootstarter.utils.LogApplicationVariables;
import ru.berdnikov.httploggerspringbootstarter.utils.LoggingParameters;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author danilaberdnikov on LogEnvironmentPostProcessor.
 * @project http-logger-spring-boot-starter
 */
//+
@Component
public class LogEnvironmentPostProcessor implements EnvironmentPostProcessor {
    private final static String CUSTOM_PROPERTY_NAME = "customLogProperties";

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        var enabledProperty = Optional.ofNullable(environment.getProperty(LogApplicationVariables.ENABLED));
        var levelProperty = Optional.ofNullable(environment.getProperty(LogApplicationVariables.LEVEL));
        var levelSrcProperty = Optional.ofNullable(environment.getProperty(LogApplicationVariables.LEVEL_SRC));
        var formatProperty = Optional.ofNullable(environment.getProperty(LogApplicationVariables.FORMAT));

        boolean isEnabled = enabledProperty.isPresent();

        if (!isEnabled) {
            throw new LogStartupException("Error property environment " + LogApplicationVariables.ENABLED);
        }

        Map<String, Object> logProperties = new HashMap<>();

        if (levelPropertyExistLogType(levelProperty)) {
            logProperties.put(levelSrcProperty.orElse(LoggingParameters.LOGGING_LEVEL_ROOT), levelProperty.get());
        }

        formatProperty.ifPresent(format -> logProperties.put(LoggingParameters.LOGGING_PATTERN, formatProperty.get()));

        MapPropertySource propertySource = new MapPropertySource(CUSTOM_PROPERTY_NAME, logProperties);
        environment.getPropertySources().addLast(propertySource);
    }

    private boolean levelPropertyExistLogType(Optional<String> level) {
        if (level.isPresent()) {
            for (LogLevel logLevel : LogLevel.values()) {
                if (logLevel.name().equalsIgnoreCase(level.get())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }
}
