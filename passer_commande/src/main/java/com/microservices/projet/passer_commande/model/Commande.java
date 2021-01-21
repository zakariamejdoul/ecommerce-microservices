package com.microservices.projet.passer_commande.model;

import java.util.Date;

public class Commande {

    private int id;

    private Integer productId;

    private String dateCommande;

    private Integer quantite;

    private Boolean commandePayee;

    public Commande() {
    }

    public Commande(int id, Integer productId, String dateCommande, Integer quantite, Boolean commandePayee) {
        this.id = id;
        this.productId = productId;
        this.dateCommande = dateCommande;
        this.quantite = quantite;
        this.commandePayee = commandePayee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(String dateCommande) {
        this.dateCommande = dateCommande;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Boolean getCommandePayee() {
        return commandePayee;
    }

    public void setCommandePayee(Boolean commandePayee) {
        this.commandePayee = commandePayee;
    }

    @Override
    public String toString() {
        return "commande{" +
                "id=" + id +
                ", productId=" + productId +
                ", dateCommande=" + dateCommande +
                ", quantite=" + quantite +
                ", commandePayee=" + commandePayee +
                '}';
    }

}
