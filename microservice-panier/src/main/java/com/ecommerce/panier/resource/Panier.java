package com.ecommerce.panier.resource;


import com.ecommerce.panier.dao.PanierDao;
import com.ecommerce.panier.moduls.CommandePanier;
import com.ecommerce.panier.moduls.produit;
import com.ecommerce.panier.service.ProduitData;
import com.ecommerce.panier.service.QuantiteInStock;
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

    @Autowired
    QuantiteInStock quantiteInStock;

    @Autowired
    ProduitData produitData;

    @Autowired
    CommandePanier commandePanier;

    @RequestMapping("/ajouterproduit/{id}/quantite/{quantite}")
    public ArrayList<produit> ajouter_produit(@PathVariable("id") long id, @PathVariable("quantite") int quantite) {

        // Stock
        Boolean quantitedisponible = quantiteInStock.getForQuantite(id, quantite);

        if (quantitedisponible){
            produit p = produitData.getForProduit(id,quantite);


            return panierDao.ajouterProduit(p);
        }else {
            ArrayList<produit> produits = new ArrayList<>();

            produit p = new produit();
            p.setQuantite_panier(-1);
            p.setTitre("Quantite non disponible");
            p.setId(0);
            produits.add(p);
            return produits;
        }


    }




    @RequestMapping("/afficherpanier")
    public ArrayList<produit> afficher_panier() {

        return panierDao.afficherPanier();

    }

    @RequestMapping("/supprimerproduit/{id}")
    public ArrayList<produit> supprimer_produit(@PathVariable("id") long id) {

        commandePanier.supprimer(id);

        return panierDao.supprimerProduit(id);

    }

    @RequestMapping("/viderpanier")
    public ArrayList<produit> vider_panier() {

        return panierDao.viderPanier();

    }

    @RequestMapping("/commanderPanier")
    public CommandePanier CommanderPanier() {

        return commandePanier;

    }

}
