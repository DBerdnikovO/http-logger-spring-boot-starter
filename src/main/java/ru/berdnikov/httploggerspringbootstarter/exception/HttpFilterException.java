package ru.berdnikov.httploggerspringbootstarter.exception;

/**
 * @author danilaberdnikov on HttpFilterException.
 * @project http-logger-spring-boot-starter
 */
public class HttpFilterException extends RuntimeException {
    public HttpFilterException() {
    }

    public HttpFilterException(String message) {
        super(message);
    }

    public HttpFilterException(Throwable cause) {
        super(cause);
    }
}
