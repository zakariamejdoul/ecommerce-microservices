package com.ecommerce.microserviceproduit.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String titre;
    private String description;
    private double prix;
    private String ville;
    private String categorie;
    private String date;
    private int quantite;
    private String proprietaire;

    public Produit() {
    }


    public Produit(String titre, String description, double prix, String ville, String categorie, String date, int quantite, String proprietaire)
    {
        this.proprietaire = proprietaire;
        this.titre = titre;
        this.description = description;
        this.prix = prix;
        this.ville = ville;
        this.categorie = categorie;
        this.date = date;
        this.quantite = quantite;
    }


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public double getPrix() {
        return prix;
    }
    public void setPrix(double prix) {
        this.prix = prix;
    }
    public String getVille() {
        return ville;
    }
    public void setVille(String ville) {
        this.ville = ville;
    }
    public String getCategorie() {
        return categorie;
    }
    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getProprietaire() {return proprietaire;}

    public void setProprietaire(String proprietaire) {
        this.proprietaire = proprietaire;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id='" + id + '\'' +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", prix='" + prix + '\'' +
                ", ville='" + ville + '\'' +
                ", categorie='" + categorie + '\'' +
                ", date='" + date + '\'' +
                ", proprietaire='" + proprietaire + '\'' +
                '}';
    }
}
