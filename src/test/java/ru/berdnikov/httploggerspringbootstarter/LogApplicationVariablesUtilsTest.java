package ru.berdnikov.httploggerspringbootstarter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import ru.berdnikov.httploggerspringbootstarter.utils.LogPropertiesUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

/**
 * @author danilaberdnikov on LogApplicationVariablesUtilsTest.
 * @project http-logger-spring-boot-starter
 */
public class LogApplicationVariablesUtilsTest {
    @Mock
    private ConditionContext mockContext;

    @Mock
    private Environment mockEnvironment;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testIsLoggerEnabled_WhenPropertyExists() {
        when(mockContext.getEnvironment()).thenReturn(mockEnvironment);
        when(mockEnvironment.getProperty("logger.enabled")).thenReturn("true");

        boolean result = LogPropertiesUtils.isLoggerEnabled(mockContext);

        assertTrue(result);
    }

    @Test
    void testIsLoggerEnabled_WhenPropertyDoesNotExist() {
        when(mockContext.getEnvironment()).thenReturn(mockEnvironment);
        when(mockEnvironment.getProperty("logger.enabled")).thenReturn(null);

        boolean result = LogPropertiesUtils.isLoggerEnabled(mockContext);

        assertFalse(result);
    }

    @Test
    void testIsAspectLoggingEnabled_WhenTypeIsAspect() {
        when(mockContext.getEnvironment()).thenReturn(mockEnvironment);
        when(mockEnvironment.getProperty("logger.type")).thenReturn("aspect");

        boolean result = LogPropertiesUtils.isAspectLoggingEnabled(mockContext);

        assertTrue(result);
    }

    @Test
    void testIsAspectLoggingEnabled_WhenTypeIsNotAspect() {
        when(mockContext.getEnvironment()).thenReturn(mockEnvironment);
        when(mockEnvironment.getProperty("logger.type")).thenReturn("filter");

        boolean result = LogPropertiesUtils.isAspectLoggingEnabled(mockContext);

        assertFalse(result);
    }

    @Test
    void testIsFilterLoggingEnabled_WhenTypeIsFilter() {
        when(mockContext.getEnvironment()).thenReturn(mockEnvironment);
        when(mockEnvironment.getProperty("logger.type")).thenReturn("filter");

        boolean result = LogPropertiesUtils.isFilterLoggingEnabled(mockContext);

        assertTrue(result);
    }

    @Test
    void testIsFilterLoggingEnabled_WhenTypeIsNotFilter() {
        when(mockContext.getEnvironment()).thenReturn(mockEnvironment);
        when(mockEnvironment.getProperty("logger.type")).thenReturn("aspect");

        boolean result = LogPropertiesUtils.isFilterLoggingEnabled(mockContext);

        assertFalse(result);
    }

    @Test
    void testIsInterceptorLoggingEnabled_WhenTypeIsInterceptor() {
        when(mockContext.getEnvironment()).thenReturn(mockEnvironment);
        when(mockEnvironment.getProperty("logger.type")).thenReturn("interceptor");

        boolean result = LogPropertiesUtils.isInterceptorLoggingEnabled(mockContext);

        assertTrue(result);
    }

    @Test
    void testIsInterceptorLoggingEnabled_WhenTypeIsNotInterceptor() {
        when(mockContext.getEnvironment()).thenReturn(mockEnvironment);
        when(mockEnvironment.getProperty("logger.type")).thenReturn("aspect");

        boolean result = LogPropertiesUtils.isInterceptorLoggingEnabled(mockContext);

        assertFalse(result);
    }
}
