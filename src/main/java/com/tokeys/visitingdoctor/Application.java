package com.tokeys.visitingdoctor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@Configuration
@EnableCaching
@ComponentScan(basePackages = {"com.tokeys","com.ibeetl.admin"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run( Application.class, args );
    }

}

