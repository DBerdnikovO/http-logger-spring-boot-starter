package ru.berdnikov.httploggerspringbootstarter.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author danilaberdnikov on TrackHttpTime.
 * @project http-logger-spring-boot-starter
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TrackHttpTime {
}
