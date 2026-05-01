package com.enamorado.restclienttesting;

import com.enamorado.restclienttesting.services.RestClientsService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestClientTestingApplication implements CommandLineRunner {

    RestClientsService restClientsService;

    public RestClientTestingApplication(RestClientsService restClientsService) {
        this.restClientsService = restClientsService;
    }

    public static void main(String[] args) {
        SpringApplication.run(RestClientTestingApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        restClientsService.callRestTemplate();
    }
}
