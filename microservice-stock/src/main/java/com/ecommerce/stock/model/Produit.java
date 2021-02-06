package com.ecommerce.stock.model;

public class Produit {

    private long id;
    private String titre;
    private String description;
    private double prix;
    private String ville;
    private String categorie;
    private String date;
    private int quantite;

    // Constructor
    public Produit( long id,String titre, String description, double prix, String ville, String categorie, String date, int quantite) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.prix = prix;
        this.ville = ville;
        this.categorie = categorie;
        this.date = date;
        this.quantite=quantite;
    }

    public Produit() {
    }

    // Getters & setters
    public long getId() {
        return id;
    }

    public void setId(long id) { this.id = id; }

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

    public double getPrix() { return prix; }

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

    public Boolean estEpuise(){
        return this.quantite == 0;
    }
}