package com.ecommerce.recherche.services;

import com.ecommerce.recherche.dao.ProduitDaoCls;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AllProduit {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "FallBackProduit", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")
    })
    public ProduitDaoCls getAllProduits() {
        return restTemplate.getForObject("http://Produit/AllProduit/", ProduitDaoCls.class);
    }

    public ProduitDaoCls FallBackProduit() {

        return new ProduitDaoCls();

    }
}
