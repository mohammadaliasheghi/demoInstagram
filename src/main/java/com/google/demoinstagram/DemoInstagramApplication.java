package com.google.demoinstagram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class DemoInstagramApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoInstagramApplication.class, args);
    }

}
