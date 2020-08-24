package com.pyruz.samotech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = {"com.pyruz.samotech.shared", "com.pyruz.samotech.uaa"})
public class UaaApplication {
    public static void main(String[] args) {
        SpringApplication.run(UaaApplication.class, args);
    }

}

