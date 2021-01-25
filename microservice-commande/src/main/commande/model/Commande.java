package com.ecommerce.commande.model;

public class Commande {
    private int id;
    private String dateCommande;
    private Boolean commandePayee;
    public Commande() {
    }

    public Commande(int id, String dateCommande, Boolean commandePayee) {
        this.id = id;
        this.dateCommande = dateCommande;
        this.commandePayee = commandePayee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(String dateCommande) {
        this.dateCommande = dateCommande;
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
                ", dateCommande=" + dateCommande +
                ", commandePayee=" + commandePayee +
                '}';
    }

}
