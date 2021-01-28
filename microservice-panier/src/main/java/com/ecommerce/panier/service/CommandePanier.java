package com.ecommerce.panier.service;

import com.ecommerce.panier.moduls.commande;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CommandePanier {

    private ArrayList<commande> commandes = new ArrayList<>();


    public void ajouter(commande commande) {
        this.commandes.add(commande);
    }

    public ArrayList<commande> getCommandes() {
        return commandes;
    }

    public void supprimer(String id) {
        for (commande c:commandes) {
            if (c.getId().equals(id)){
                commandes.remove(c);
                break;
            }
        }
    }



}
