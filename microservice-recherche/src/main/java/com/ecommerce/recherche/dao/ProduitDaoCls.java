package com.ecommerce.recherche.dao;



import com.ecommerce.recherche.moduls.produit;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class ProduitDaoCls implements ProduitDao{

    private static ArrayList<produit> produits = new ArrayList<>() ;


    @Override
    public void setProduits(ArrayList<produit> produits) {
        this.produits = produits;
    }



    @Override
    public ArrayList<produit> rechercheByName(String name) {

        ArrayList<produit> rechercheListe = new ArrayList<>();
        for (produit p : produits){
            if (p.getTitre().toLowerCase().contains(name.toLowerCase())){
                rechercheListe.add(p);
            }
        }
        return rechercheListe;
    }

    @Override
    public ArrayList<produit> rechercheByFilter(String ville, String categorie) {

        ArrayList<produit> filterListe = new ArrayList<>();
        if (ville.isEmpty() && categorie.equals("Toutes")) {
            filterListe = produits;
        } else {
            if (!ville.isEmpty() && !categorie.equals("Toutes")) {
                for (produit p : produits) {
                    if (p.getCategorie().equals(categorie) && p.getVille().toLowerCase().equals(ville.toLowerCase())) {
                        filterListe.add(p);
                    }
                }

            } else if (!ville.isEmpty() && categorie.equals("Toutes")) {
                for (produit p : produits) {
                    if (p.getVille().toLowerCase().equals(ville.toLowerCase())) {
                        filterListe.add(p);
                    }
                }

            } else {
                for (produit p : produits) {
                    if (p.getCategorie().equals(categorie)) {
                        filterListe.add(p);
                    }
                }
            }


        }
        return filterListe;
    }

    @Override
    public produit rechercheByID(String id) {
        for (produit p : produits) {

            if (id.equals(p.getId())){
                return p;
            }

        }
        return null;
    }

}

