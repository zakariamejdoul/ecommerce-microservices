package com.ecommerce.stock.web.controller;

import com.ecommerce.stock.model.Produit;
import com.ecommerce.stock.model.StockProxy;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

public class StockController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public StockProxy stockProxy;

    @GetMapping(value = "/stock/{productId}")
    public String notify(@PathVariable("productId") String productId) {
        if(stockProxy.getProduct(productId).estEpuise()){
            return "Ok";
        }else{
            return "Stock epuisé";
        }
    }

    @GetMapping(value = "/id/{productId}/quantite/{q}")
    public Boolean estSuffisant(@PathVariable("productId") String productId,@PathVariable("q") Integer q){
        return (stockProxy.getProduct(productId).getQuantite() - q) >= 0;
    }

}
