package com.carlmem.pastebin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class HashServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HashServiceApplication.class, args);
    }
}