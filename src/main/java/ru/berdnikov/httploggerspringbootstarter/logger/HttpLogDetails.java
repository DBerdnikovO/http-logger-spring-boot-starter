package ru.berdnikov.httploggerspringbootstarter.logger;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

/**
 * @author danilaberdnikov on HttpLogDetails.
 * @project http-logger-spring-boot-starter
 */
public interface HttpLogDetails {
    void logRequestAndResponseDetails(ServletRequest request, ServletResponse response);

    void logRequestDetails(ServletRequest servletRequest);

    void logResponseDetails(ServletResponse servletResponse);
}
