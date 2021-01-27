package com.ecommerce.recherche.dao;

import com.ecommerce.recherche.moduls.produit;

import java.util.ArrayList;

public interface ProduitDao {

    public ArrayList<produit> rechercheByName(String name);

    public ArrayList<produit> rechercheByFilter(String ville,String categorie);

    public produit rechercheByID(String id);

}
