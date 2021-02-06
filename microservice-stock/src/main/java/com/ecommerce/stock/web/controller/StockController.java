package com.ecommerce.stock.web.controller;

import com.ecommerce.stock.model.Produit;
import com.ecommerce.stock.model.StockProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    public StockProxy stockProxy;

    @RequestMapping("/{productId}")
    public String notify(@PathVariable("productId") long productId) {
        if(stockProxy.getProduct(productId).estEpuise()){
            return "Stock epuisé";
        }else{
            return "Ok, le stock est suffisant";
        }
    }

    @RequestMapping("/id/{productId}/quantite/{q}")
    public Boolean estSuffisant(@PathVariable("productId") long productId,@PathVariable("q") int q){
        System.out.println(stockProxy.getProduct(productId).getQuantite());
        return (stockProxy.getProduct(productId).getQuantite() - q) >= 0;
    }

}
