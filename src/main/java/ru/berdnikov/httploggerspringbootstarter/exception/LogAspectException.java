package ru.berdnikov.httploggerspringbootstarter.exception;

/**
 * @author danilaberdnikov on LogAspectException.
 * @project http-logger-spring-boot-starter
 */
public class LogAspectException extends RuntimeException {
    public LogAspectException() {
    }

    public LogAspectException(String message) {
        super(message);
    }

    public LogAspectException(Throwable cause) {
        super(cause);
    }
}
