package com.projetMS.recherche.panierproduit.dao;

import com.projetMS.recherche.panierproduit.moduls.produit;

import java.util.ArrayList;

public interface PanierDao {
    public ArrayList<produit> afficherPanier();
    public ArrayList<produit> ajouterProduit(produit p);
    public ArrayList<produit> supprimerProduit(String id);
    public ArrayList<produit> viderPanier();

}
