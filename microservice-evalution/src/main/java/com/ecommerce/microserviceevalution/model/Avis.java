package com.ecommerce.microserviceevalution.model;


import javax.persistence.*;

@Entity
public class Avis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idAvis;

    private String avis;
    private int idClient;
    private int idProduit;

    public Avis(){

    }
    public Avis(String avis, int idClient, int idProduit) {
        this.avis = avis;
        this.idClient = idClient;
        this.idProduit = idProduit;
    }

    public String getAvis() {
        return avis;
    }

    public void setAvis(String avis) {
        this.avis = avis;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public long getIdAvis() {
        return idAvis;
    }

    public void setIdAvis(long idAvis) {
        this.idAvis = idAvis;
    }

    @Override
    public String toString() {
        return "Avis {" +
                "id=" + idAvis +
                ", Avis='" + avis + '\'' +
                ", idProduit='" + idProduit + '\'' +
                ", idClient='" + idClient + '\'' +
                '}';
    }
}
