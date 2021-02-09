package com.ecommerce.recherche.moduls;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class ListeProduits {
    private static ArrayList<produit> produits = new ArrayList<>() ;

    public ListeProduits() {
    }

    public static ArrayList<produit> getProduits() {
        return produits;
    }

    public static void setProduits(ArrayList<produit> produits) {
        ListeProduits.produits = produits;
    }
}
