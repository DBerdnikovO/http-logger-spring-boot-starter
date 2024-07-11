package ru.berdnikov.httploggerspringbootstarter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author danilaberdnikov on LogApplicationVariables.
 * @project http-logger-spring-boot-starter
 */
@Data
@ConfigurationProperties(prefix = "logger")
public class LogProperties {
    private Boolean enabled;
    private String type;
    private String level;
    private String levelSrc;
    private String format;
}
