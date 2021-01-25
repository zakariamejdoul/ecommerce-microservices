package com.ecommerce.commande.web.controller;

import com.ecommerce.commande.model.Commande;
import com.ecommerce.commande.web.exceptions.ImpossibleAjouterCommandeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class CommandeController {

    @GetMapping(value = "/commande/{id}")
    public Commande recupererUneCommande(@PathVariable int id){
        return new Commande(id, "12/04/2020", Boolean.TRUE);
    }

    //fonction doit etre dans le model panier ou bien passer dans les parametre 0 1
    public Boolean estLivrable(){
        return true;
    };
    private Integer prixLivraison=10;

    @PostMapping(value = "/commande/{id}/prix")
    public Integer prixCommande(@PathVariable int id){
        //liste des prix (product*qnt) de la commande num id
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        Integer sum = integers.stream()
                .reduce(0, Integer::sum);
        if(estLivrable()) {
            return sum + prixLivraison;
        }else{
            return sum;
        }
    }
}
