package com.ecommerce.panier.service;

import com.ecommerce.panier.moduls.CommandePanier;
import com.ecommerce.panier.moduls.ProduitDem;
import com.ecommerce.panier.moduls.produit;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProduitData {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CommandePanier commandePanier;


    @HystrixCommand(fallbackMethod = "getFallBackGetProduit", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")
    })
    public produit getForProduit(long id, int quantite) {
        produit p = restTemplate.getForObject("http://microservice-recherche/recherche/byid/" + id, produit.class);
        p.setQuantite_panier(quantite);
        commandePanier.ajouter(new ProduitDem(id,quantite));
        return p;
    }

    public produit getFallBackGetProduit(long id, int quantite) {
        produit p = new produit();
        p.setId(id);
        p.setTitre("Produit n'est pas dispo");
        p.setQuantite_panier(0);
        return p;

    }

}
