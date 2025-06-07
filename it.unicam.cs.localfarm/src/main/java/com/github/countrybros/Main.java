package com.github.countrybros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        /**
         * Starts the Spring Boot application by creating the application context and initializing all beans.
         */
        SpringApplication.run(Main.class, args);

    }
}