package com.projetMS.recherche.panierproduit.dao;

import com.projetMS.recherche.panierproduit.moduls.produit;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
@Repository
public class PanierCls implements PanierDao{


    private static ArrayList<produit> ListPannier = new ArrayList<>();


    @Override
    public ArrayList<produit> afficherPanier() {

        return ListPannier;
    }

    @Override
    public ArrayList<produit> ajouterProduit(produit p) {
        ListPannier.add(p);
        return ListPannier;
    }

    @Override
    public ArrayList<produit> supprimerProduit(String id) {

        for (produit p:ListPannier
             ) {
            if (p.getId().equals(id)){
                ListPannier.remove(p);
                break;
            }


        }

        return ListPannier;
    }

    @Override
    public ArrayList<produit> viderPanier() {
        ListPannier.clear();
        return ListPannier;
    }
}
