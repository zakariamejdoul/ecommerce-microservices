package com.ecommerce.commande.web.controller;

import com.ecommerce.commande.model.CommandeProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommandeController {

    @Autowired
    public CommandeProxy commandeProxy;


    @PostMapping(value = "/commande/{id}/prix")
    public Integer prixCommande(@PathVariable Integer id){
            return commandeProxy.calculPrix();
    }

}
