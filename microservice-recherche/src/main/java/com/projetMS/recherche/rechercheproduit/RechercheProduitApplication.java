package com.projetMS.recherche.rechercheproduit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RechercheProduitApplication {


	@Bean
	public RestTemplate getRestTemlate(){
		return new RestTemplate();
	}


	public static void main(String[] args) {
		SpringApplication.run(RechercheProduitApplication.class, args);
	}


}
