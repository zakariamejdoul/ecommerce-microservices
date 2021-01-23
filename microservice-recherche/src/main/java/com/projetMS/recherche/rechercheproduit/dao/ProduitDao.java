package com.projetMS.recherche.rechercheproduit.dao;

import com.projetMS.recherche.rechercheproduit.moduls.produit;

import java.util.ArrayList;

public interface ProduitDao {

    public ArrayList<produit> rechercheByName(String name);

    public ArrayList<produit> rechercheByFilter(String ville,String categorie);

    public produit rechercheByID(String id);

}
