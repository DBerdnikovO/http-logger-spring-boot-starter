package ru.berdnikov.httploggerspringbootstarter.controlelr;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author danilaberdnikov on CheckControlelr.
 * @project http-logger-spring-boot-starter
 */
@RestController()
@RequestMapping("/api")
public class CheckControlelr {
    @GetMapping
    public String check() {
        return "HELLO";
    }
}
