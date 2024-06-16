package com.demo.project02.rserver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class RserverApp {

    public static void main(String[] args) {
        SpringApplication.run(RserverApp.class, args);
    }

    @Bean
    public CommandLineRunner onStart() {
        return (args) -> {
            log.info("On Start!");
        };
    }
}
