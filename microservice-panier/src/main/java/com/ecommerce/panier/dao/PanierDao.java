package com.ecommerce.panier.dao;

import com.ecommerce.panier.moduls.produit;

import java.util.ArrayList;

public interface PanierDao {
    public ArrayList<produit> afficherPanier();
    public ArrayList<produit> ajouterProduit(produit p);
    public ArrayList<produit> supprimerProduit(long id);
    public ArrayList<produit> viderPanier();

}
