package com.ecommerce.commande.model;

public class Commande {
    private Integer id;
    private String dateCommande;
    private Boolean commandePayee;
    private Boolean livrable;
    public Commande() {
    }

    public Commande(Integer id, String dateCommande, Boolean commandePayee, Boolean livrable) {
        this.id = id;
        this.dateCommande = dateCommande;
        this.commandePayee = commandePayee;
        this.livrable=livrable;
    }

    public Integer getId() {
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

    public Boolean getLivrable() {
        return livrable;
    }

    public void setLivrable(Boolean livrable) {
        this.livrable = livrable;
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
