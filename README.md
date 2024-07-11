# http-logger-spring-boot-starter

Автоматическая настройка логирования http ответов и запросов для Spring Boot.

| http-logger-spring-boot-starter  | Spring-Boot |
| ------------- | ------------- |

## Быстрая настройка

* Добавьте зависимость:

    ```xml
        <dependency>
            <groupId>ru.berdnikov</groupId>
            <artifactId>http-logger-spring-boot-starter</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>
     ```

* Добавьте свойство в файл:
    ```properties
    logger.enabled = true
    ```

* Запустите приложение и увидите логирование всех http запросов и ответов.

## Документация

* Настраиваемые свойства 
    ```properties
    logger.type =
    logger.level-src =
    logger.level =
    logger.format =
    ```
* logger.type Предоставляет возможность настроить способ логирования http запросов. Всего 3 способа:
  
  * ASPECT - логирование с помощью аспекта, он натсроен на ослеживание запрсов в классах, помеченных аннотацией Controller 
    или RestController, на методах, отмеченных одной из следующих анноатций RequestMapping, GetMapping, PostMapping, 
    PutMapping, DeleteMapping. Используется по умолчанию.

  * FILTER - логирование с помощью фильтра, настроенного на перехват всех запросов и ответов с помощью добавления класса FilterRegistrationBean.

  * INTERCEPTOR - логирование с помощью интерцептора, настроенного на перехват всех запросов и ответов с помощью интерфейса WebMvcConfigurer.

* logger.level-src Предоставляет возможность указать, какой путь логировать, по умолчанию: logging.level.root 

* logger.level Предоставляет возможность указать тип уровня для логировнаия: FATAL, ERROR, WARN, INFO, DEBUG, TRACE. По умолчанию: INFO.

* logger.format Предоставляет возможность написать формат вывода логов, по умолчанию не задан.
