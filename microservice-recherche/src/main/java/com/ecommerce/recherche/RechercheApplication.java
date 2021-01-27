package com.ecommerce.recherche;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RechercheApplication {

    public static void main(String[] args) {
        SpringApplication.run(RechercheApplication.class, args);
    }
    @Bean
    public RestTemplate getRestTemlate(){
        return new RestTemplate();
    }
}
