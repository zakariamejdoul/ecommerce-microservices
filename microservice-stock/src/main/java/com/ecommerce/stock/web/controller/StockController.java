package com.ecommerce.stock.web.controller;

import com.ecommerce.stock.model.StockProduit;
import org.springframework.web.bind.annotation.PathVariable;

public class StockController {

    public StockProduit getStockProduit(@PathVariable("productId") int productId) {
        return new StockProduit(productId, 5);
    }
}
