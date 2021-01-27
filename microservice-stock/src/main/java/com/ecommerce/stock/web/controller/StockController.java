package com.ecommerce.stock.web.controller;

import com.ecommerce.stock.model.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

public class StockController {

    @Autowired
    private RestTemplate restTemplate;

    public Produit getProduct(String productId){
        return restTemplate.getForObject("http://microservice-produit/id/" + productId, Produit.class);
        //return new Produit(productId,"PC", "desc", 3000, "Oujda","info","25/01/2020");
    }

    @GetMapping(value = "/stock/{productId}")
    public String notify(@PathVariable("productId") String productId) {
        if(getProduct(productId).estEpuise()){
            return "Ok";
        }else{
            return "Stock epuisé";
        }
    }

    @GetMapping(value = "/id/{productId}/quantite/{q}")
    public Boolean estSuffisant(@PathVariable("productId") String productId,@PathVariable("q") Integer q){
        return (getProduct(productId).getQuantite() - q) >= 0;
    }

}
