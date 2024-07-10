package ru.berdnikov.httploggerspringbootstarter.logger;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

/**
 * @author danilaberdnikov on HttpLogging.
 * @project http-logger-spring-boot-starter
 */
public interface HttpLogging {
    void logRequestDetails(ServletRequest servletRequest);

    void logResponseDetails(ServletResponse servletResponse);
}
