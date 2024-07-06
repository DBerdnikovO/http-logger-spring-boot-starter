package ru.berdnikov.httploggerspringbootstarter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.berdnikov.httploggerspringbootstarter.ComsoZoo;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class HttpLoggerSpringBootStarterApplicationTests {

    @Autowired
    private ComsoZoo comsoZoo;

    @Test
    public void testComsoZooBeanExists() {
        assertThat(comsoZoo).isNotNull();
    }
}
