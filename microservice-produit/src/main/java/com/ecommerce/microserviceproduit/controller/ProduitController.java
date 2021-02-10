package com.ecommerce.microserviceproduit.controller;


import ch.qos.logback.core.net.server.Client;
import com.ecommerce.microserviceproduit.doa.ProduitDAO;
import com.ecommerce.microserviceproduit.model.ClientProxy;
import com.ecommerce.microserviceproduit.model.Produit;
import com.ecommerce.microserviceproduit.model.ResourceNotFoundException;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produit")
public class ProduitController {
    @Autowired
    ProduitDAO pd;

    @Autowired
    private ClientProxy clientProxy;

    @GetMapping("/AllProducts")
    public Iterable<Produit> lesproduits(){
        RestTemplate rs = new RestTemplate();

       List<Produit> produit = pd.findAll();
        return produit;
    }

    @GetMapping("/chercherProduit/{idProduit}")
    public Optional<Produit> produitbyID(@PathVariable("idProduit") long id){
        return pd.findById(id);
    }



    @GetMapping("/MesProduits")
    public Iterable<Produit> mesproduits(@RequestHeader("Authorization") String auth){
        if(clientProxy.userIsAuth(auth).getBody().equals("Admin Board.")){
            return pd.findAllByProprietaire(clientProxy.getUsername(auth));
        }else{
            return null;
        }
    }


    @PostMapping("/saveProduct")
    public ResponseEntity<Produit> ajouterAvis(@RequestBody Produit produit, @RequestHeader("Authorization") String auth){
        if(clientProxy.userIsAuth(auth).getBody().equals("Admin Board.")) {
            produit.setProprietaire(clientProxy.getUsername(auth));
            Produit newproduit = pd.save(produit);
            if (newproduit != null) {
                return new ResponseEntity<>(newproduit, HttpStatus.CREATED);

            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else{
                    return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @PutMapping("/ModifierProduit/{idProduit}")
    public ResponseEntity<?> modifierAvis(@PathVariable long idProduit, @RequestBody Produit newproduit, @RequestHeader("Authorization") String auth) throws ResourceNotFoundException {
        Produit produit  = pd.findById(idProduit).orElseThrow(() -> new ResourceNotFoundException());
        if (clientProxy.getUsername(auth).equals(produit.getProprietaire())){
            produit.setCategorie(newproduit.getCategorie());
            produit.setDate(newproduit.getDate());
            produit.setDescription(newproduit.getDescription());
            produit.setPrix(newproduit.getPrix());
            produit.setVille(newproduit.getVille());
            produit.setTitre(newproduit.getTitre());
            produit.setProprietaire(clientProxy.getUsername(auth));
            pd.save(produit);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @DeleteMapping("/supprimerProduit/{idProduit}")
    public ResponseEntity<?> deleteProduct(@PathVariable("idProduit") long idProduit, @RequestHeader("Authorization") String auth) throws ResourceNotFoundException {
        Produit produit  = pd.findById(idProduit).orElseThrow(() -> new ResourceNotFoundException());
        if(clientProxy.getUsername(auth).equals(produit.getProprietaire())){
            pd.deleteById(idProduit);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
