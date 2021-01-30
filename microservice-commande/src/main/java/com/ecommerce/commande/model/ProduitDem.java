package com.ecommerce.commande.model;

public class ProduitDem {
    private String id;
    private Integer quantite_panier;
    private Integer price_promoted;

    public ProduitDem(String id, Integer quantite_panier) {
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

    public Integer getQuantite_panier() {
        return quantite_panier;
    }

    public void setQuantite_panier(Integer quantite_panier) {
        this.quantite_panier = quantite_panier;
    }

    public Integer getPrice_promoted() {
        return price_promoted;
    }

    public void setPrice_promoted(Integer price_promoted) {
        this.price_promoted = price_promoted;
    }
}
