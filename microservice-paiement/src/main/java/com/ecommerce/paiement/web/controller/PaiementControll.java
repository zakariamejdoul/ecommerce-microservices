package com.ecommerce.paiement.web.controller;


import com.ecommerce.paiement.dao.PaiementDao;
import com.ecommerce.paiement.model.Paiement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/paiement")
public class PaiementControll {

    @Autowired
    private PaiementDao paiementDao;

    @PostMapping(path = "/")
    public ResponseEntity<Paiement> payerUneCommande(@RequestBody Paiement paiement){

        Paiement paiementExistant = paiementDao.findByIdCommande(paiement.getIdCommande());
        if(paiementExistant != null) throw new PaiementExistantException("cette commande est déja payé");

        Paiement nouveauPaiement=paiementDao.save(paiement);

        return new ResponseEntity<Paiement>(nouveauPaiement, HttpStatus.CREATED);
    }
}
