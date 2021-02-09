package com.ecommerce.recherche.resource;


import com.ecommerce.recherche.moduls.ListeProduits;
import com.ecommerce.recherche.moduls.produit;
import com.ecommerce.recherche.services.AllProduit;
import com.ecommerce.recherche.services.ChercheByID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@RestController
@RequestMapping("/recherche")
public class Recherche {


    @Autowired
    private ListeProduits listeProduits;

    @Autowired
    private AllProduit allProduit;

    @Autowired
    private ChercheByID chercheByID;

    @RequestMapping("/byname/{name}")
    public ArrayList<produit> getProduitByNama(@PathVariable("name") String name) {

        allProduit.getAllProduits();

        ArrayList<produit> rechercheListe = new ArrayList<>();
        ArrayList<produit> produits = listeProduits.getProduits();

        for (produit p : produits){
            if (p.getTitre().toLowerCase().contains(name.toLowerCase())){
                rechercheListe.add(p);
            }
        }
        return rechercheListe;

    }


    @RequestMapping("/byfilter/ville/{ville}/categorie/{categorie}")
    public ArrayList<produit> getProduitByfiltrage(@PathVariable("ville") String ville, @PathVariable("categorie") String categorie) {

        allProduit.getAllProduits();

        ArrayList<produit> filterListe = new ArrayList<>();
        ArrayList<produit> produits = listeProduits.getProduits();
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

    @RequestMapping("/byid/{id}")
    public produit getProduitById(@PathVariable("id") long id) {

        return chercheByID.rechercheByID(id);

    }


}