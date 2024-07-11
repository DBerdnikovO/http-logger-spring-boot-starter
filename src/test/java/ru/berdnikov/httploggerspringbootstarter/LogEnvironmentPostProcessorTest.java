package ru.berdnikov.httploggerspringbootstarter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.mock.env.MockEnvironment;
import ru.berdnikov.httploggerspringbootstarter.init.LogEnvironmentPostProcessor;
import ru.berdnikov.httploggerspringbootstarter.utils.LogApplicationVariables;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * @author danilaberdnikov on LogEnvironmentPostProcessorTest.
 * @project http-logger-spring-boot-starter
 */

public class LogEnvironmentPostProcessorTest {
    private LogEnvironmentPostProcessor postProcessor;
    private ConfigurableEnvironment environment;

    @BeforeEach
    public void setUp() {
        postProcessor = new LogEnvironmentPostProcessor();
        environment = new MockEnvironment();
    }

    @Test
    public void testPostProcessEnvironment() {
        ((MockEnvironment) environment).setProperty(LogApplicationVariables.ENABLED, "true");
        ((MockEnvironment) environment).setProperty(LogApplicationVariables.LEVEL, "DEBUG");
        ((MockEnvironment) environment).setProperty(LogApplicationVariables.LEVEL_SRC, "logging.level.application");
        ((MockEnvironment) environment).setProperty(LogApplicationVariables.FORMAT, "%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n");

        SpringApplication application = mock(SpringApplication.class);

        postProcessor.postProcessEnvironment(environment, application);

        MapPropertySource customPropertySource = (MapPropertySource) environment.getPropertySources().get(LogEnvironmentPostProcessor.CUSTOM_PROPERTY_NAME);
        assertNotNull(customPropertySource);

        Map<String, Object> properties = customPropertySource.getSource();
        assertEquals("DEBUG", properties.get("logging.level.application"));
        assertEquals("%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n", properties.get("logging.pattern.console"));
    }

    @Test
    public void testLevelPropertyExistLogType() {
        assertTrue(postProcessor.levelPropertyExistLogType(Optional.of("INFO")));
        assertFalse(postProcessor.levelPropertyExistLogType(Optional.of("unknown")));
        assertFalse(postProcessor.levelPropertyExistLogType(Optional.empty()));
    }
}
