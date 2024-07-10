package ru.berdnikov.httploggerspringbootstarter;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import ru.berdnikov.httploggerspringbootstarter.utils.LoggerPropertiesUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * @author danilaberdnikov on LoggerPropertiesUtilsTest.
 * @project http-logger-spring-boot-starter
 */
public class LoggerPropertiesUtilsTest {
    @Mock
    private ConditionContext mockContext;

    @Mock
    private Environment mockEnvironment;

    @Test
    void testIsLoggerEnabled_WhenPropertyExists() {
        MockitoAnnotations.openMocks(this);
        when(mockContext.getEnvironment()).thenReturn(mockEnvironment);
        when(mockEnvironment.getProperty("logger.enabled")).thenReturn("true");

        boolean result = LoggerPropertiesUtils.isLoggerEnabled(mockContext);

        assertThat(result).isTrue();
    }

    @Test
    void testIsLoggerEnabled_WhenPropertyDoesNotExist() {
        MockitoAnnotations.openMocks(this);
        when(mockContext.getEnvironment()).thenReturn(mockEnvironment);
        when(mockEnvironment.getProperty("logger.enabled")).thenReturn(null);

        boolean result = LoggerPropertiesUtils.isLoggerEnabled(mockContext);

        assertThat(result).isFalse();
    }

    @Test
    void testIsAspectLoggingEnabled_WhenTypeIsAspect() {
        MockitoAnnotations.openMocks(this);
        when(mockContext.getEnvironment()).thenReturn(mockEnvironment);
        when(mockEnvironment.getProperty("logger.type")).thenReturn("aspect");

        boolean result = LoggerPropertiesUtils.isAspectLoggingEnabled(mockContext);

        assertThat(result).isTrue();
    }

    @Test
    void testIsAspectLoggingEnabled_WhenTypeIsNotAspect() {
        MockitoAnnotations.openMocks(this);
        when(mockContext.getEnvironment()).thenReturn(mockEnvironment);
        when(mockEnvironment.getProperty("logger.type")).thenReturn("filter");

        boolean result = LoggerPropertiesUtils.isAspectLoggingEnabled(mockContext);

        assertThat(result).isFalse();
    }

    @Test
    void testIsFilterLoggingEnabled_WhenTypeIsFilter() {
        MockitoAnnotations.openMocks(this);
        when(mockContext.getEnvironment()).thenReturn(mockEnvironment);
        when(mockEnvironment.getProperty("logger.type")).thenReturn("filter");

        boolean result = LoggerPropertiesUtils.isFilterLoggingEnabled(mockContext);

        assertThat(result).isTrue();
    }

    @Test
    void testIsFilterLoggingEnabled_WhenTypeIsNotFilter() {
        MockitoAnnotations.openMocks(this);
        when(mockContext.getEnvironment()).thenReturn(mockEnvironment);
        when(mockEnvironment.getProperty("logger.type")).thenReturn("aspect");

        boolean result = LoggerPropertiesUtils.isFilterLoggingEnabled(mockContext);

        assertThat(result).isFalse();
    }

    @Test
    void testIsInterceptorLoggingEnabled_WhenTypeIsInterceptor() {
        MockitoAnnotations.openMocks(this);
        when(mockContext.getEnvironment()).thenReturn(mockEnvironment);
        when(mockEnvironment.getProperty("logger.type")).thenReturn("interceptor");

        boolean result = LoggerPropertiesUtils.isInterceptorLoggingEnabled(mockContext);

        assertThat(result).isTrue();
    }

    @Test
    void testIsInterceptorLoggingEnabled_WhenTypeIsNotInterceptor() {
        MockitoAnnotations.openMocks(this);
        when(mockContext.getEnvironment()).thenReturn(mockEnvironment);
        when(mockEnvironment.getProperty("logger.type")).thenReturn("aspect");

        boolean result = LoggerPropertiesUtils.isInterceptorLoggingEnabled(mockContext);

        assertThat(result).isFalse();
    }
}
