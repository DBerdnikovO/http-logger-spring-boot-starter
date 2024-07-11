package ru.berdnikov.httploggerspringbootstarter.exception;

/**
 * @author danilaberdnikov on LogStartupException.
 * @project http-logger-spring-boot-starter
 */
public class LogStartupException extends RuntimeException {
    public LogStartupException() {
    }

    public LogStartupException(String message) {
        super(message);
    }
}

