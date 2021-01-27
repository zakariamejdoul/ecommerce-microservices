package com.ecommerce.recherche.dao;



import com.ecommerce.recherche.moduls.produit;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class ProduitDaoCls implements ProduitDao{

    private static ArrayList<produit> produits = new ArrayList<>() ;
    static {
        produits.add(new produit("p1","motor 150","hello","120","oujda","moto","12/1/2021"));
        produits.add(new produit("p2","Voiture BMW","model 2014","20000","berken","voiture","1/1/2020"));
        produits.add(new produit("p3","Tmax","530","1200000","rabat","moto","20/2/2020"));
        produits.add(new produit("p4","maison 100m","hello","120","oujda","maison","23/5/2019"));
        produits.add(new produit("p5","tele","veuf","3000","casa","tele","6/11/2020"));

    }

    @Override
    public void setProduits(ArrayList<produit> produits) {
        ProduitDaoCls.produits = produits;
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

