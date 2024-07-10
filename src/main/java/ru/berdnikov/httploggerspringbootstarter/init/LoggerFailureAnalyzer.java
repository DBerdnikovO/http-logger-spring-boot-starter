package ru.berdnikov.httploggerspringbootstarter.init;

import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;
import org.springframework.stereotype.Component;
import ru.berdnikov.httploggerspringbootstarter.exception.LoggerStartupException;

/**
 * @author danilaberdnikov on LoggerFailureAnalyzer.
 * @project http-logger-spring-boot-starter
 */
@Component
public class LoggerFailureAnalyzer extends AbstractFailureAnalyzer<LoggerStartupException> {
    @Override
    protected FailureAnalysis analyze(Throwable rootFailure, LoggerStartupException cause) {
        return new FailureAnalysis(cause.getMessage(), "Укажите валидные значения для свойства", cause);
    }
}
