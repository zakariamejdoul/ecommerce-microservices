package com.ecommerce.commande.model;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class CommandeProxy {
    @Autowired
    private RestTemplate restTemplate;

    private Integer prixLivraison=10;

    @HystrixCommand(fallbackMethod = "getFallBackCalculPrix", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")
    })
    public Integer calculPrix(Commande commande){
        //liste des produits demandes (productid & qnt) de la commande depuis microservices panier
        /*ResponseEntity<ArrayList<ProduitDem>> Response =
                restTemplate.exchange("microservice-panier/panier/commanderPanier",
                        HttpMethod.GET, null, new ParameterizedTypeReference<ArrayList<ProduitDem>>() {
                        });
        ArrayList<ProduitDem> panier = Response.getBody();*/

        ProduitDem p1 = new ProduitDem("1",5);
        ProduitDem p2 = new ProduitDem("2",4);
        ArrayList<ProduitDem> panier =new ArrayList<>();
        panier.add(p1);
        panier.add(p2);

        // la promotion d un produit depuis microservices promotion et combinaison des deux
        /*for(int i = 0; i < panier.size(); i++)
            panier.get(i).setPrice_promoted(restTemplate.getForObject("http://microservice-promotion/add/" + panier.get(i).getId(), Integer.class));*/

        for (ProduitDem produitDem : panier) produitDem.setPrice_promoted(5);

        //combinaison des deux
        ArrayList<Integer> products = new ArrayList<>();
        for (ProduitDem produitDem : panier)
            products.add((produitDem.getQuantite_panier() * produitDem.getPrice_promoted()));

        //la somme des prix
        Integer sum = products.stream()
                .reduce(0, Integer::sum);


        if(commande.getLivrable()) {
            return sum + prixLivraison;
        }else{
            return sum;
        }
    }

    public Integer getFallBackCalculPrix(Commande commande){
        return 0;
    }
}
