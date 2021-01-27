package com.ecommerce.commande.web.controller;

import com.ecommerce.commande.model.Commande;
import com.ecommerce.commande.model.Promotion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CommandeController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/commande/{id}")
    public Commande recupererUneCommande(@PathVariable int id){
        return new Commande(id, "12/04/2020", Boolean.FALSE);
    }

    //fonction doit etre dans le model panier ou bien passer dans les parametre 0 1
    public Boolean estLivrable(){
        return true;
    }
    private Integer prixLivraison=10;

    List<List<Integer>> lisPanier2 = new ArrayList<List<Integer>>();

    public Integer calculPrix(){
        //liste des prix (product*qnt) de la commande num id depuis microservices panier

        List<List<Integer>> lisPanier = new ArrayList<List<Integer>>();
        List<Integer> p1 = new ArrayList (5); //produit p1 avec quantite 5
        List<Integer> p2 = new ArrayList (4); //produit p2 avec quantite 4
        lisPanier.set(1, p1); //p1 a le id 1 et la quantite 5
        lisPanier.set(2, p2); //p2 a le id 2 et la quantite 4

        // la promotion d un produit depuis microservices promotion
        lisPanier.stream().map(product ->{
            Promotion promotion = restTemplate.getForObject("microservice-promotion/promotion/"+product.get(0),Promotion.class);
            List<Integer> p = new ArrayList (promotion.getProduct_id());
            p.add(product.get(1)); //1 inidice de la quantite
            lisPanier2.add(p);
            return lisPanier2;
        }).collect(Collectors.toList());

        //combinaison des deux
        List<Integer> products = Arrays.asList(); // (prix ou prixpromted) *quantite

        lisPanier2.stream().map(product ->{
            return products.add((product.get(1)*product.get(2)));
        }).collect(Collectors.toList());

        Integer sum = products.stream()
                .reduce(0, Integer::sum);
        if(estLivrable()) {
            return sum + prixLivraison;
        }else{
            return sum;
        }
    }
    @PostMapping(value = "/commande/{id}/prix")
    public Integer prixCommande(@PathVariable Integer id){
            return calculPrix();
    }

}
