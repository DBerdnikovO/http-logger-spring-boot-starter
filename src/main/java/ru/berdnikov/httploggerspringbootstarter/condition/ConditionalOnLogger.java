package ru.berdnikov.httploggerspringbootstarter.condition;

import org.springframework.context.annotation.Conditional;
import ru.berdnikov.httploggerspringbootstarter.logger.LoggerType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author danilaberdnikov on ConditionalOnLogger.
 * @project http-logger-spring-boot-starter
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Conditional(OnLoggerCondition.class)
public @interface ConditionalOnLogger {
    LoggerType value();
}

