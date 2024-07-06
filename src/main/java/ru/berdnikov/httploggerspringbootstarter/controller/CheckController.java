package ru.berdnikov.httploggerspringbootstarter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author danilaberdnikov on CheckController.
 * @project http-logger-spring-boot-starter
 */
@RequestMapping("/api")
@RestController
public class CheckController {
    @GetMapping()
    public String get() {
        return "HELLO";
    }
}
