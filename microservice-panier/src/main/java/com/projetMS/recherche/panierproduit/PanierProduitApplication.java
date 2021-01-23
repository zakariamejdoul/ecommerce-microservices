package com.projetMS.recherche.panierproduit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class PanierProduitApplication {

	@Bean
	public RestTemplate getRestTemlate(){
		return new RestTemplate();
	}


	public static void main(String[] args) {
		SpringApplication.run(PanierProduitApplication.class, args);
	}

}
