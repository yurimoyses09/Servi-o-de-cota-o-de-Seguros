package com.acme.insurance;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
@EnableRabbit
public class ApplicationInsurance {

    private static final Logger logger = Logger.getLogger(ApplicationInsurance.class.getName());

    public static void main(String[] args) {
        SpringApplication.run(ApplicationInsurance.class, args);
        logger.info("API IS WORKING");
    }
}
