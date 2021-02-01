package com.ecommerce.microserviceproduit.controller;


import com.ecommerce.microserviceproduit.doa.ProduitDAO;
import com.ecommerce.microserviceproduit.model.Produit;
import com.ecommerce.microserviceproduit.model.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produit")
public class ProduitController {
    @Autowired
    ProduitDAO pd;

    @GetMapping("/AllProducts")
    public Iterable<Produit> lesproduits(){

       List<Produit> produit = pd.findAll();
        return produit;
    }

    @GetMapping("/chercherProduit/{idProduit}")
    public Optional<Produit> produitbyID(@PathVariable("idProduit") long id){
        return pd.findById(id);
    }

    @PostMapping("/saveProduct")
    public ResponseEntity<Produit> ajouterAvis(@RequestBody Produit produit){
        Produit newproduit = pd.save(produit);
        if(newproduit != null){
            return new ResponseEntity<>(newproduit, HttpStatus.CREATED);

        }else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/ModifierProduit/{idProduit}")
    public void modifierAvis(@PathVariable long idProduit, @RequestBody Produit newproduit) throws ResourceNotFoundException {
        Produit produit  = pd.findById(idProduit).orElseThrow(() -> new ResourceNotFoundException());
        produit.setCategorie(newproduit.getCategorie());
        produit.setDate(newproduit.getDate());
        produit.setDescription(newproduit.getDescription());
        produit.setPrix(newproduit.getPrix());
        produit.setVille(newproduit.getVille());
        produit.setTitre(newproduit.getTitre());
        pd.save(produit);

    }

    @DeleteMapping("/supprimerProduit/{idProduit}")
    public void deleteProduct(@PathVariable("idProduit") long idProduit){
        pd.deleteById(idProduit);
    }
}
