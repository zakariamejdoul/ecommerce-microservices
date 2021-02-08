package com.ecommerce.microserviceevalution.controller;

import com.ecommerce.microserviceevalution.model.*;
import com.ecommerce.microserviceevalution.dao.AvisDao;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

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
    public Iterable<Avis> avisSelonClient(@RequestParam("idClient") String username) {
        return av.findAvisByUsername(username);
    }

    @HystrixCommand
    @PostMapping("/saveAvis")
    public ResponseEntity<Avis> ajouterAvis(@RequestBody Avis avis) {

        Produit p = produitProxy.getProduit(avis.getIdProduit());

        avis.setDesignation(p.getTitre());

        Avis newavis = av.save(avis);
        if (newavis != null) {
            return new ResponseEntity<>(newavis, HttpStatus.CREATED);

        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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

    @GetMapping("/userIsAuth")
    public ResponseEntity<?> userIsAuth(@RequestParam("Authorization") String authorization) {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", authorization.trim());
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                "http://microservice-auth/api/test/user",
                HttpMethod.GET,
                entity,
                String.class);

        return ResponseEntity.ok(Objects.requireNonNull(response.getBody()));


    }


}
