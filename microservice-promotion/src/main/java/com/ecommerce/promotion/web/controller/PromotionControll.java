package com.ecommerce.promotion.web.controller;


import com.ecommerce.promotion.dao.PromotionDao;
import com.ecommerce.promotion.model.Promotion;
//import jdk.jfr.internal.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/promotion")
public class PromotionControll {

    @Autowired
    private PromotionDao promotionDao;

    @PostMapping(path="/add")
    public ResponseEntity<Promotion> addNewPromotion(@RequestBody Promotion promotion){
        try {
            Promotion _promotion = promotionDao.save(new Promotion(promotion.getProduct_id(),promotion.getStart_date(), promotion.getEnd_date(),promotion.getTaxe() ));
            return new ResponseEntity<>(_promotion, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
