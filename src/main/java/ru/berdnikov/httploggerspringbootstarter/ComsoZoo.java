package ru.berdnikov.httploggerspringbootstarter;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class ComsoZoo {

    @PostConstruct
    private void greeting() {

        System.out.println("Добро пожаловать в CosmoZoo!");
    }
}
