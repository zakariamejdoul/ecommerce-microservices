package com.ecommerce.commande.web.controller;

import com.ecommerce.commande.model.Commande;
import com.ecommerce.commande.model.CommandeProxy;
import com.ecommerce.commande.model.Promotion;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import freemarker.core.ReturnInstruction;
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

    @Autowired
    public CommandeProxy commandeProxy;


    @PostMapping(value = "/commande/{id}/prix")
    public Integer prixCommande(@PathVariable Integer id){
            return commandeProxy.calculPrix();
    }

}
