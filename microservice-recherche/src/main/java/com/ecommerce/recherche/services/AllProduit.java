package com.ecommerce.recherche.services;

import com.ecommerce.recherche.moduls.ListeProduits;
import com.ecommerce.recherche.moduls.produit;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;

@Service
public class AllProduit {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ListeProduits listeProduits;

    @HystrixCommand(fallbackMethod = "FallBackProduit")
    public void getAllProduits() {

        ResponseEntity<ArrayList<produit>> Response =
                restTemplate.exchange("http://microservice-produit/produit/AllProducts",
                        HttpMethod.GET, null, new ParameterizedTypeReference<ArrayList<produit>>() {
                        });
        ArrayList<produit> produits = Response.getBody();

        listeProduits.setProduits(produits);

    }

    public void FallBackProduit() {

        ArrayList<produit> produits = new ArrayList<>();

        produit p = new produit();
        p.setTitre("Pas Des Produits !");
        p.setId(0);
        produits.add(p);

        listeProduits.setProduits(produits);

    }
}
