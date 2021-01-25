package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class CommandeModifiée {
    @Id
    private int idCommande;
    private int idClient;
    private List<Produit> produits;


    public CommandeModifiée(int idCommande, int idClient, List<Produit> produits) {
        this.idCommande = idCommande;
        this.idClient = idClient;
        this.produits = produits;
    }

    public CommandeModifiée() {

    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }
}
