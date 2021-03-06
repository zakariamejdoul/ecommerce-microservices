package com.ecommerce.promotion.web.controller;


import com.ecommerce.promotion.dao.PromotionDao;
import com.ecommerce.promotion.model.Produit;
import com.ecommerce.promotion.model.ProduitProxy;
import com.ecommerce.promotion.model.Promotion;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/promotion")
public class PromotionControll {

    @Autowired
    private PromotionDao promotionDao;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public ProduitProxy produitProxy;

    private DiscoveryClient discoveryClient;

    @HystrixCommand
    @PostMapping(path="/add/{idProduit}")
    public ResponseEntity<Promotion> addNewPromotion(@RequestBody Promotion promotion,@PathVariable("idProduit") long id) {
        Produit p = produitProxy.getProduit(id);
        try {
            double  prixinit= p.getPrix();
            Promotion _promotion = promotionDao.save(new Promotion(id,promotion.getStart_date(), promotion.getEnd_date(),promotion.getTaxe(),prixinit-(prixinit* promotion.getTaxe())));
            return new ResponseEntity<>(_promotion, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @HystrixCommand
    @GetMapping(path="/prixprmoted/{idProduit}")
    public double PrixPrmotedByID(@PathVariable("idProduit") long id){
        double prixpromoted=0;
        List<Promotion> promotions = new ArrayList<Promotion>();
        promotions.addAll(promotionDao.findAll());
        //Produit pd = produitProxy.getProduit(id);
        //Produit pd = restTemplate.getForObject("http://microservice-produit/produit/chercherProduit/"+id, Produit.class);
        for (Promotion p : promotions ){
            //pd.getId()
            if (id==p.getProduct_id()){
                prixpromoted= p.getPrixPromoted();
            }
        }
        return prixpromoted;
    }


    @GetMapping(path = "/all")
    public ResponseEntity<List<Promotion>>  getAllPromotions(){
        try {
            List<Promotion> promotions = new ArrayList<Promotion>();


            promotions.addAll(promotionDao.findAll());


            if (promotions.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(promotions, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Promotion> getPromotionById(@PathVariable("id") Long id){
        Optional<Promotion> promotion = promotionDao.findById(id);

        return promotion.map(promotion1 -> new ResponseEntity<>(promotion1, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

}
