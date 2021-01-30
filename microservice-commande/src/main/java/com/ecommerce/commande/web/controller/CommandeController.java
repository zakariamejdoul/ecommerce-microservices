package com.ecommerce.commande.web.controller;

import com.ecommerce.commande.model.Commande;
import com.ecommerce.commande.model.CommandeProxy;
import com.ecommerce.commande.model.ProduitDem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/commande")
public class CommandeController {

    @Autowired
    public CommandeProxy commandeProxy;

    @RequestMapping("/{id}")
    public Commande getCommande(@PathVariable Integer id){
        return new Commande(id, "12/04/2020", Boolean.FALSE,Boolean.TRUE);
    }

    @RequestMapping("/{id}/prix")
    public Integer prixCommande(@PathVariable Integer id){
        Commande commande = getCommande(id);
        return commandeProxy.calculPrix(commande);
    }
}
