package ru.berdnikov.httploggerspringbootstarter.exception;

/**
 * @author danilaberdnikov on LoggerAspectException.
 * @project http-logger-spring-boot-starter
 */
public class LoggerAspectException extends RuntimeException {
    public LoggerAspectException() {
    }

    public LoggerAspectException(String message) {
        super(message);
    }

    public LoggerAspectException(Throwable cause) {
        super(cause);
    }
}
