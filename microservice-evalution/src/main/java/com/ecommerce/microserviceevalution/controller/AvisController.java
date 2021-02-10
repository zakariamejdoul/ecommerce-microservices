package com.ecommerce.microserviceevalution.controller;

import com.ecommerce.microserviceevalution.model.*;
import com.ecommerce.microserviceevalution.dao.AvisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;


@RestController
@RequestMapping("/Avis")
public class AvisController {

    @Autowired
    private AvisDao av;

    @Autowired
    private ProduitProxy produitProxy;

    @Autowired
    private ClientProxy clientProxy;

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/AllAvis")
    public Iterable<Avis> allOpinions() {
        Iterable<Avis> l = av.findAll();
        return l;
    }


    @GetMapping("/chercherAvis/{idAvis}")
    public Optional<Avis> avisSelonID(@PathVariable long idAvis) {
        Optional<Avis> avis = av.findById(idAvis);
        return avis;
    }


    @GetMapping("/produit")
    public Iterable<Avis> avisSelonIDProduit(@RequestParam("idProduit") int idProduit) {
        Iterable<Avis> avis = av.findAvisByIdProduit(idProduit);

        return avis;
    }


    @GetMapping("/MesAvis")
    public Iterable<Avis> avisSelonClient(@RequestHeader("Authorization") String auth) {
        if(clientProxy.userIsAuth(auth).getBody().equals("User Content.")){
        return av.findAvisByUsername(clientProxy.getUsername(auth));}
        else{
            return null;
        }
    }


    @PostMapping("/saveAvis")
    public ResponseEntity<Avis> ajouterAvis(@RequestBody Avis avis,@RequestHeader("Authorization") String auth) {

        if (clientProxy.userIsAuth(auth).getBody().equals("User Content.")) {
            Produit p = produitProxy.getProduit(avis.getIdProduit());

            avis.setUsername(clientProxy.getUsername(auth));
            avis.setDesignation(p.getTitre());

            Avis newavis = av.save(avis);
            if (newavis != null) {
                return new ResponseEntity<>(newavis, HttpStatus.CREATED);

            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
                return new ResponseEntity<>(HttpStatus.LOCKED);
            }
    }






    @PutMapping("/ModifierAvis/{idAvis}")
    public ResponseEntity<?> modifierAvis(@PathVariable("idAvis") long idAvis, @RequestBody Avis newavis, @RequestHeader("Authorization")  String auth){
        Avis avis = av.findByIdAvis(idAvis);
        if(avis.getUsername().equals(clientProxy.getUsername(auth))){
            avis.setAvis(newavis.getAvis());
            av.save(avis);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }


    @DeleteMapping("/SupprimerAvis/{idAvis}")
    public ResponseEntity<?> supprimerAvis(@PathVariable("idAvis") long idAvis, @RequestHeader("Authorization") String auth) {
        Avis avis = av.findByIdAvis(idAvis);
        if(avis.getUsername().equals(clientProxy.getUsername(auth))){
            av.deleteById(idAvis);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }



}
