package com.ecommerce.microserviceevalution.controller;

import com.ecommerce.microserviceevalution.model.*;
import com.ecommerce.microserviceevalution.dao.AvisDao;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.ws.rs.HeaderParam;
import java.util.Arrays;
import java.util.Objects;
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


    @GetMapping("/client")
    public Iterable<Avis> avisSelonClient(@RequestHeader("Authorization") String auth) {
        return av.findAvisByUsername(clientProxy.getUsername(auth));
    }


    @PostMapping("/saveAvis")
    public ResponseEntity<Avis> ajouterAvis(@RequestBody Avis avis,@RequestHeader("Authorization") String auth) {
        boolean r = clientProxy.userIsAuth(auth);
        if (r != true) {
            return new ResponseEntity<>(HttpStatus.LOCKED);
        } else {
            Produit p = produitProxy.getProduit(avis.getIdProduit());

            avis.setUsername(clientProxy.getUsername(auth));
            avis.setDesignation(p.getTitre());

            Avis newavis = av.save(avis);
            if (newavis != null) {
                return new ResponseEntity<>(newavis, HttpStatus.CREATED);

            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }





    @PutMapping("/ModifierAvis/{idAvis}")
    public void modifierAvis(@PathVariable long idAvis, @RequestBody Avis newavis) throws ResourceNotFoundException {
        Avis avis = av.findById(idAvis).orElseThrow(() -> new ResourceNotFoundException());
        avis.setAvis(newavis.getAvis());
        av.save(avis);
    }


    @DeleteMapping("/SupprierAvis/{idAvis}")
    public void supprimerAvis(@PathVariable long idAvis) {
        av.deleteById(idAvis);
    }



}
