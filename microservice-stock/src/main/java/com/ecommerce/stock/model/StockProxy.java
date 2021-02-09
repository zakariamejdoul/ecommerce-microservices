package com.ecommerce.stock.model;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StockProxy {
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallBackProduct", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")
    })
    public Produit getProduct(long productId){
        //return restTemplate.getForObject("http://microservice-produit/produit/chercherProduit/" + productId, Produit.class);
        return new Produit(productId,"PC", "desc", 3000, "Oujda","info","25/01/2020",5);
    }

    public Produit getFallBackProduct(long productId){
        return new Produit(productId,"no value", "no value", 0, "no value","no value","no value",0);
    }


}
