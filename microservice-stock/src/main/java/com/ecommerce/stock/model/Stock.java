package com.ecommerce.stock.model;

public class Stock {
    private String id;
    private Integer productId;
    private Integer productQ;

    Stock(){

    }

    Stock(String id, Integer productId, Integer productQ){
        this.id=id;
        this.productId=productId;
        this.productQ=productQ;
    }

    public String getId() {
        return id;
    }

    public Integer getProductId() {
        return productId;
    }

    public Integer getProductQ() {
        return productQ;
    }

    public void setProductQ(Integer productQ) {
        this.productQ = productQ;
    }
}

