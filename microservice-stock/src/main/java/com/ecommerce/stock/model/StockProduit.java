package com.ecommerce.stock.model;

public class StockProduit {
    private int id;
    private int quantite;

    public StockProduit(){

    }

    public StockProduit(int id, int quantite){
        this.id=id;
        this.quantite=quantite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Boolean estEpuise(int id) {
        if (this.id == id && this.quantite <= 0) return true;
        return false;
    }
}
