package com.ecommerce.panier.moduls;

public class ProduitDem {
    private String id;
    private int quantite_panier;


    public ProduitDem(String id, int quantite_panier) {
        this.id = id;
        this.quantite_panier = quantite_panier;

    }

    public ProduitDem() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQuantite_panier() {
        return quantite_panier;
    }

    public void setQuantite_panier(int quantite_panier) {
        this.quantite_panier = quantite_panier;
    }
}
