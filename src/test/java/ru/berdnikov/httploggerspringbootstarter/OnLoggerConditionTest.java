package ru.berdnikov.httploggerspringbootstarter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import ru.berdnikov.httploggerspringbootstarter.condition.ConditionalOnLogger;
import ru.berdnikov.httploggerspringbootstarter.condition.OnLoggerCondition;
import ru.berdnikov.httploggerspringbootstarter.logger.LoggerType;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

/**
 * @author danilaberdnikov on OnLoggerConditionTest.
 * @project http-logger-spring-boot-starter
 */
public class OnLoggerConditionTest {

    @Mock
    private ConditionContext mockContext;

    @Mock
    private AnnotatedTypeMetadata mockMetadata;

    @Mock
    private Environment mockEnvironment;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testMatches_LoggerDisabled() {
        when(mockContext.getEnvironment()).thenReturn(mockEnvironment);
        when(mockEnvironment.getProperty("logger.enabled")).thenReturn(null);

        OnLoggerCondition condition = new OnLoggerCondition();

        boolean result = condition.matches(mockContext, mockMetadata);

        assertFalse(result);
    }

    @Test
    public void testMatches_NoAnnotationAttributes() {
        when(mockContext.getEnvironment()).thenReturn(mockEnvironment);
        when(mockEnvironment.getProperty("logger.enabled")).thenReturn("true");
        when(mockMetadata.getAnnotationAttributes(ConditionalOnLogger.class.getName())).thenReturn(null);

        OnLoggerCondition condition = new OnLoggerCondition();

        boolean result = condition.matches(mockContext, mockMetadata);

        assertFalse(result);
    }

    @Test
    public void testMatches_AspectLoggingEnabled() {
        when(mockContext.getEnvironment()).thenReturn(mockEnvironment);
        when(mockEnvironment.getProperty("logger.enabled")).thenReturn("true");
        when(mockMetadata.getAnnotationAttributes(ConditionalOnLogger.class.getName()))
                .thenReturn(Collections.singletonMap("value", LoggerType.ASPECT));

        when(mockContext.getEnvironment().getProperty("logger.type")).thenReturn("aspect");

        OnLoggerCondition condition = new OnLoggerCondition();

        boolean result = condition.matches(mockContext, mockMetadata);

        assertTrue(result);
    }

    @Test
    public void testMatches_FilterLoggingEnabled() {
        when(mockContext.getEnvironment()).thenReturn(mockEnvironment);
        when(mockEnvironment.getProperty("logger.enabled")).thenReturn("true");
        when(mockMetadata.getAnnotationAttributes(ConditionalOnLogger.class.getName()))
                .thenReturn(Collections.singletonMap("value", LoggerType.FILTER));

        when(mockContext.getEnvironment().getProperty("logger.type")).thenReturn("filter");

        OnLoggerCondition condition = new OnLoggerCondition();

        boolean result = condition.matches(mockContext, mockMetadata);

        assertTrue(result);
    }

    @Test
    public void testMatches_InterceptorLoggingEnabled() {
        when(mockContext.getEnvironment()).thenReturn(mockEnvironment);
        when(mockEnvironment.getProperty("logger.enabled")).thenReturn("true");
        when(mockMetadata.getAnnotationAttributes(ConditionalOnLogger.class.getName()))
                .thenReturn(Collections.singletonMap("value", LoggerType.INTERCEPTOR));

        when(mockContext.getEnvironment().getProperty("logger.type")).thenReturn("interceptor");

        OnLoggerCondition condition = new OnLoggerCondition();

        boolean result = condition.matches(mockContext, mockMetadata);

        assertTrue(result);
    }
}
