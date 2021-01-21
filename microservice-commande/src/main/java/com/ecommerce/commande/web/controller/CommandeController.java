package com.ecommerce.commande.web.controller;

import com.ecommerce.commande.dao.CommandeDao;
import com.ecommerce.commande.model.Commande;
import com.ecommerce.commande.web.exceptions.ImpossibleAjouterCommandeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommandeController {

    @Autowired
    CommandeDao commandesDao;

    @PostMapping(value = "/commandes")
    public ResponseEntity<Commande> ajouterCommande(@RequestBody Commande commande){

        Commande nouvelleCommande = commandesDao.save(commande);

        if(nouvelleCommande == null) throw new ImpossibleAjouterCommandeException("Impossible d'ajouter cette commande");

        return new ResponseEntity<Commande>(commande, HttpStatus.CREATED);
    }

    @GetMapping(value = "/commandes/{id}")
    public Commande recupererUneCommande(@PathVariable int id){
        //Optional<Commande> commande = Optional.ofNullable(commandesDao.findById(id));
        //if(!commande.isPresent()) throw new CommandeNotFoundException("Cette commande n'existe pas");
        //return commande;
        return new Commande(id, 4, "12/04/2020", 5, Boolean.TRUE);
    }
}
