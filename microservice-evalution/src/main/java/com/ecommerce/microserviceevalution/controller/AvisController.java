package com.ecommerce.microserviceevalution.controller;

import com.ecommerce.microserviceevalution.model.Avis;
import com.ecommerce.microserviceevalution.dao.AvisDao;
import com.ecommerce.microserviceevalution.model.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;


@RestController
@RequestMapping("/Avis")
public class AvisController {

    @Autowired
    private AvisDao av;


    @GetMapping("/AllAvis")
        public Iterable<Avis> allOpinions(){
            Iterable<Avis> l = av.findAll();
            return l;
    }

    @GetMapping("/{idAvis}")
    public Optional<Avis> avisSelonID(@PathVariable long idAvis){
        return av.findById(idAvis);
    }


    @GetMapping("/produit")
    public Iterable<Avis> avisSelonIDProduit(@RequestParam("idProduit") int idProduit){
        return av.findAvisByIdProduit(idProduit);
    }

    @GetMapping("/client")
    public Iterable<Avis> avisSelonClient(@RequestParam("idClient") int idClient ){
        return av.findAvisByIdClient(idClient);
    }


    @PostMapping("/saveAvis")
    public ResponseEntity<Avis> ajouterAvis(@RequestBody Avis avis){
        Avis newavis = av.save(avis);
        if(newavis != null){
            return new ResponseEntity<>(newavis, HttpStatus.CREATED);

        }else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/ModifierAvis/{idAvis}")
    public void modifierAvis(@PathVariable long idAvis, @RequestBody Avis newavis) throws ResourceNotFoundException {
        Avis avis = av.findById(idAvis).orElseThrow(() -> new ResourceNotFoundException());
        avis.setAvis(newavis.getAvis());

        av.save(avis);

    }

    @DeleteMapping ("/SupprimerAvis/{idAvis}")
    public void supprimerProduit(@PathVariable long idAvis) {
        av.deleteById(idAvis);
    }





}
