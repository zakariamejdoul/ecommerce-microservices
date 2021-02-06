package com.ecommerce.panier.moduls;

public class ProduitDem {
    private long id;
    private int quantite_panier;


    public ProduitDem(long id, int quantite_panier) {
        this.id = id;
        this.quantite_panier = quantite_panier;

    }

    public ProduitDem() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantite_panier() {
        return quantite_panier;
    }

    public void setQuantite_panier(int quantite_panier) {
        this.quantite_panier = quantite_panier;
    }
}
