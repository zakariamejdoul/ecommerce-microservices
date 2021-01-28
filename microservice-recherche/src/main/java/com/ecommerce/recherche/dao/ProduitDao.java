package com.ecommerce.recherche.dao;


import com.ecommerce.recherche.moduls.produit;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

public interface ProduitDao {

    public void setProduits(ArrayList<produit> produits);

    public ArrayList<produit> rechercheByName(String name);

    public ArrayList<produit> rechercheByFilter(String ville,String categorie);

    public produit rechercheByID(String id);

}
