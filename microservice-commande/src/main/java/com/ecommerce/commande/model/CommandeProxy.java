package com.ecommerce.commande.model;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CommandeProxy {
    @Autowired
    private RestTemplate restTemplate;


    @GetMapping(value = "/commande/{id}")
    public Commande getCommande(@PathVariable int id){
        return new Commande(id, "12/04/2020", Boolean.FALSE,Boolean.TRUE);
    }

    private Integer prixLivraison=10;

    @HystrixCommand(fallbackMethod = "getFallBackQuantite", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")
    })
    public Integer calculPrix(){
        Commande commande = getCommande(5);
        //liste des prix (product*qnt) de la commande num id depuis microservices panier
        ProduitDem produitDem = restTemplate.getForObject("microservice-panier/panier/commanderPanier",ProduitDem.class);

        // la promotion d un produit depuis microservices promotion
        Integer pricePromoted = restTemplate.getForObject("microservice-promotion/promotion/"+produitDem.getId(),Integer.class);

        //combinaison des deux
        List<Integer> products = Arrays.asList(); // (prix ou prixpromted) *quantite
        products.add((produitDem.getQuantite_panier()*pricePromoted));

        //la somme des prix
        Integer sum = products.stream()
                .reduce(0, Integer::sum);
        if(commande.getLivrable()) {
            return sum + prixLivraison;
        }else{
            return sum;
        }
    }

    public Integer getFallBackCalculPrix(){
        return 0;
    }
}
