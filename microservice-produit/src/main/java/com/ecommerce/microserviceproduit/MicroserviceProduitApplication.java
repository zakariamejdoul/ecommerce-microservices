package com.ecommerce.microserviceproduit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;

@SpringBootApplication
public class MicroserviceProduitApplication {

	@LoadBalanced
	public static void main(String[] args) {
		SpringApplication.run(MicroserviceProduitApplication.class, args);
	}

}
