package com.ecommerce.microserviceevalution;

import com.ecommerce.microserviceevalution.dao.AvisDao;
import com.ecommerce.microserviceevalution.model.Avis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MicroserviceEvalutionApplication {

    @Bean
    public RestTemplate rs(){
        return new RestTemplate();
    }

    public static void main(String[] args) {SpringApplication.run(MicroserviceEvalutionApplication.class, args);}


}
