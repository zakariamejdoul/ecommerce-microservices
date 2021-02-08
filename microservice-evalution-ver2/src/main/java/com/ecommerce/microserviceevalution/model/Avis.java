package com.ecommerce.microserviceevalution.model;


import javax.persistence.*;

@Entity
public class Avis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idAvis;
    private int idProduit;
    private String designation;
    private String avis;
    private String username;

    public Avis(){

    }

    public Avis(String username, String designation, String avis, String idClient, int idProduit) {
        this.username = username;
        this.designation = designation;
        this.avis = avis;
        this.idProduit = idProduit;
    }

    public long getIdAvis() {
        return idAvis;
    }

    public void setIdAvis(long idAvis) {
        this.idAvis = idAvis;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getAvis() {
        return avis;
    }

    public void setAvis(String avis) {
        this.avis = avis;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    @Override
    public String toString() {
        return "Avis{" +
                "idAvis=" + idAvis +
                ", username='" + username + '\'' +
                ", designation='" + designation + '\'' +
                ", avis='" + avis + '\'' +
                ", idProduit=" + idProduit +

                '}';
    }
}
