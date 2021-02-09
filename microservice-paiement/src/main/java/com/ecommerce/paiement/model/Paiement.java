package com.ecommerce.paiement.model;

import javax.persistence.*;

@Entity
@Table(name = "paiement")
public class Paiement {


    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true)
    private Integer idCommande;


    private Integer montant;
    private Long numeroCarte;
    private Boolean commandePayee;

    public Paiement() {
    }

    public Paiement(int id, Integer idCommande, Integer montant, Long numeroCarte) {
        this.id = id;
        this.idCommande = idCommande;
        this.montant = montant;
        this.numeroCarte = numeroCarte;
    }

    public Paiement(Integer idCommande, Integer montant, Long numeroCarte) {
        this.idCommande = idCommande;
        this.montant = montant;
        this.numeroCarte = numeroCarte;
    }

    public Paiement(Integer idCommande, Integer montant, Long numeroCarte, Boolean commandePayee) {
        this.idCommande = idCommande;
        this.montant = montant;
        this.numeroCarte = numeroCarte;
        this.commandePayee = commandePayee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(Integer idCommande) {
        this.idCommande = idCommande;
    }

    public Integer getMontant() {
        return montant;
    }

    public void setMontant(Integer montant) {
        this.montant = montant;
    }

    public Long getNumeroCarte() {
        return numeroCarte;
    }

    public void setNumeroCarte(Long numeroCarte) {
        this.numeroCarte = numeroCarte;
    }

    public Boolean getCommandePayee() {return commandePayee;}

    public void setCommandePayee(Boolean commandePayee) { this.commandePayee = commandePayee; }

    @Override
    public String toString() {
        return "Paiement{" +
                "id=" + id +
                ", idCommande=" + idCommande +
                ", montant=" + montant +
                ", numeroCarte=" + numeroCarte +
                '}';
    }
}
