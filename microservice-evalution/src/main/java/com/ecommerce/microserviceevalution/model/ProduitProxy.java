package com.ecommerce.microserviceevalution.model;

import com.ecommerce.microserviceevalution.dao.AvisDao;
import com.netflix.discovery.converters.Auto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

@Service
public class ProduitProxy {
    @Autowired
    private RestTemplate restTemplate;


    @HystrixCommand(fallbackMethod = "getProduitfallBack",
        commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")
        })
    public Produit getProduit(int idProduit) {
        return restTemplate.getForObject("http://microservice-produit/produit/chercherProduit/" + idProduit, Produit.class);
    }

    public Produit getProduitfallBack(int idProduit){
        return new Produit(idProduit,"No Product found, server is down","No Product found, the server is down", 0, "No Product found, server is down", "No Product found", "null");
    }
}