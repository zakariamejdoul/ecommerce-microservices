package com.ecommerce.panier.service;

import com.ecommerce.panier.moduls.ProduitDem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CommandePanier {

    private ArrayList<ProduitDem> ProduitDems = new ArrayList<>();


    public void ajouter(ProduitDem ProduitDem) {
        this.ProduitDems.add(ProduitDem);
    }

    public ArrayList<ProduitDem> getCommandes() {
        return ProduitDems;
    }

    public void supprimer(String id) {
        for (ProduitDem c: ProduitDems) {
            if (c.getId().equals(id)){
                ProduitDems.remove(c);
                break;
            }
        }
    }



}
