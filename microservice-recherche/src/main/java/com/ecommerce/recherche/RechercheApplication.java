package com.ecommerce.recherche;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class RechercheApplication {
    @LoadBalanced

    @Bean
    public RestTemplate getRestTemlate(){
        // Timeout
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(5000);
        return new RestTemplate(clientHttpRequestFactory);
    }


    public static void main(String[] args) {
        SpringApplication.run(RechercheApplication.class, args);
    }


}
