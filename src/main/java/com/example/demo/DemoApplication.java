package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//scan for other packages,new packages needed to add here
@ComponentScan("com/example/controller")
@ComponentScan("com/example/service")
@EntityScan({"com.example.entity"})
@ComponentScan("com/example/repository")
@ComponentScan("com/example/logging")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
