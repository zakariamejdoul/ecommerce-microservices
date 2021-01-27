package com.ecommerce.panier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class PanierApplication {

    public static void main(String[] args) {
        SpringApplication.run(PanierApplication.class, args);
    }
    @Bean
    public RestTemplate getRestTemlate(){
        return new RestTemplate();
    }
}
