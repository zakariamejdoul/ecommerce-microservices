package com.ecommerce.promotion.model;

import com.ecommerce.promotion.dao.PromotionDao;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProduitProxy {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private PromotionDao promotionDao;

    @HystrixCommand(fallbackMethod = "getProduitFallBack",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")
    })

    public Produit getProduit(long id){
        return restTemplate.getForObject("http://microservice-produit/produit/chercherProduit/"+id, Produit.class);
    }

    public Produit getProduitFallBack(long id){
        return new Produit(id, "no title, Server is down", "", 0, "", "","", 0);
    }

    /*@HystrixCommand(fallbackMethod = "PrixPrmotedByIDFallBack",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")
    })

    @GetMapping(path="/prixprmoted/{idProduit}")
    public double PrixPrmotedByID(@PathVariable("idProduit") long id){
        double prixpromoted=0;
        List<Promotion> promotions = new ArrayList<Promotion>();
        promotions.addAll(promotionDao.findAll());
        Produit pd = restTemplate.getForObject("http://microservice-produit/produit/chercherProduit/"+id, Produit.class);
        for (Promotion p : promotions ){
            if (pd.getId()==p.getProduct_id()){
                prixpromoted= p.getPrixPromoted();
            }
        }
        return prixpromoted;
    }

    public double PrixPrmotedByIDFallBack(@PathVariable("idProduit") long id){
        return 0;
    }*/

}