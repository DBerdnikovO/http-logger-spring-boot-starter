package ru.berdnikov.httploggerspringbootstarter.condition;

import org.springframework.context.annotation.Conditional;
import ru.berdnikov.httploggerspringbootstarter.logger.LogType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author danilaberdnikov on ConditionalOnLog.
 * @project http-logger-spring-boot-starter
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Conditional(ConditionalOnLogCondition.class)
public @interface ConditionalOnLog {
    LogType value();
}

