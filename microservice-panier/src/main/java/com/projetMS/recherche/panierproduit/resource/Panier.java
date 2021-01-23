package com.projetMS.recherche.panierproduit.resource;

import com.projetMS.recherche.panierproduit.dao.PanierDao;
import com.projetMS.recherche.panierproduit.moduls.produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@RestController
@RequestMapping("/panier")
public class Panier {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PanierDao panierDao;

    @RequestMapping("/ajouterproduit/{id}")
    public ArrayList<produit> ajouter_produit(@PathVariable("id") String id) {

        produit p = restTemplate.getForObject("http://localhost:8081/recherche/byid/"+id, produit.class);
        return panierDao.ajouterProduit(p);

    }

    @RequestMapping("/afficherpanier")
    public ArrayList<produit> afficher_panier(String id) {

        return panierDao.afficherPanier();

    }

    @RequestMapping("/supprimerproduit/{id}")
    public ArrayList<produit> supprimer_produit(@PathVariable("id") String id) {

        return panierDao.supprimerProduit(id);

    }

    @RequestMapping("/viderpanier")
    public ArrayList<produit> vider_panier() {

        return panierDao.viderPanier();

    }

}
