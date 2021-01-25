package com.ecommerce.stock.web.controller;

import com.ecommerce.stock.model.Produit;
import com.ecommerce.stock.model.StockProduit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class StockController {

    @GetMapping(value = "/stock/{productId}")
    public StockProduit getStockProduit(@PathVariable("productId") int productId) {
        return new StockProduit(productId, 5);
    }

    public Produit getProduct(String productId){
        return new Produit(productId,"PC", "desc", 3000, "Oujda","info","25/01/2020");
    }

    @GetMapping(value = "/stock/{productId}")
    public String notify(@PathVariable("productId") String productId) {
        if(getProduct(productId).estEpuise()){
            return "Ok";
        }else{
            return "Stock epuisé";
        }
    }

}
