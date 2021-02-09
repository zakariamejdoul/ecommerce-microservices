package com.ecommerce.paiement.web.controller;

import com.ecommerce.paiement.dao.PaiementDao;
import com.ecommerce.paiement.model.Commande;
import com.ecommerce.paiement.model.CommandeProxy;
import com.ecommerce.paiement.model.Paiement;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(path = "/paiement")
public class PaiementControll {

    @Autowired
    private PaiementDao paiementDao;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CommandeProxy commandeProxy;

    @PostMapping(path = "/{idCommande}")
    public ResponseEntity<Paiement> payerUneCommande(@RequestBody Paiement paiement, @PathVariable("idCommande") int id){

        Integer prix = commandeProxy.getPrix(id);
        Commande cmd = commandeProxy.getCommande(id);

        if(prix !=0){
            cmd.setCommandePayee(Boolean.TRUE);
        }

        Paiement paiementExistant = paiementDao.findByIdCommande(id);

        if(paiementExistant != null) throw new PaiementExistantException("cette commande est déja payé");

        paiement.setCommandePayee(cmd.getCommandePayee());
        paiement.setIdCommande(id);
        paiement.setMontant(prix);
        Paiement nouveauPaiement=paiementDao.save(paiement);

        return new ResponseEntity<Paiement>(nouveauPaiement, HttpStatus.CREATED);
    }
}
