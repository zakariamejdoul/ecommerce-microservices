package com.ecommerce.panier.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class QuantiteInStock {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallBackQuantite", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")
    })
    public Boolean getForQuantite(long id, int quantite) {
        return restTemplate.getForObject("http://microservice-stock/stock/id/" + id + "/quantite/" + quantite, Boolean.class);
    }

    public Boolean getFallBackQuantite(long id, int quantite) {
        return false;
    }
}
