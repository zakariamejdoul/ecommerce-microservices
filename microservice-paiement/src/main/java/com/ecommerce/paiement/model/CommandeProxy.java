package com.ecommerce.paiement.model;

import com.ecommerce.paiement.dao.PaiementDao;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CommandeProxy {

    @Autowired
    private RestTemplate restTemplate;


    @HystrixCommand(fallbackMethod = "getPrixFallBack",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")
    })
    public Integer getPrix(int id){
        return restTemplate.getForObject("http://microservice-commande/commande/"+id+"/prix",Integer.class);
    }

    public Integer getPrixFallBack(int id){
        return 0;
    }

    @HystrixCommand(fallbackMethod = "getCommandeFallBack")
    public Commande getCommande(int id){
        return restTemplate.getForObject("http://microservice-commande/commande/"+id,Commande.class);
    }

    public Commande getCommandeFallBack(int id){
        return new Commande(id,"",Boolean.FALSE,Boolean.FALSE);
    }

}
