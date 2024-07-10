package ru.berdnikov.httploggerspringbootstarter.exception;

/**
 * @author danilaberdnikov on LoggerStartupException.
 * @project http-logger-spring-boot-starter
 */
public class LoggerStartupException extends RuntimeException {
    public LoggerStartupException() {
    }

    public LoggerStartupException(String message) {
        super(message);
    }
}

