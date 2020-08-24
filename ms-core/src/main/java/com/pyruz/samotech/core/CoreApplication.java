package com.pyruz.samotech.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EntityScan("com/pyruz.samotech/shared/model/entity")
@ComponentScan(basePackages = {"com.pyruz.samotech.shared", "com.pyruz.samotech.core"})
public class CoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(CoreApplication.class, args);
    }
}
