package com.example.demo2;

import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Log4j2
@Configuration
@ComponentScan("com.example.demo2.service")
public class RestConfiguration {
    @PostConstruct
    protected void postConstruct(){
        log.error("rest services initialized");
    }
}
