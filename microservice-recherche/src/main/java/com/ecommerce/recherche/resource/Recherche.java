package com.ecommerce.recherche.resource;

import com.ecommerce.recherche.dao.ProduitDao;
import com.ecommerce.recherche.moduls.produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/recherche")
public class Recherche {



    @Autowired
    private ProduitDao produitDao;

    @RequestMapping("/byname/{name}")
    public ArrayList<produit> getProduitByNama(@PathVariable("name") String name) {

        return produitDao.rechercheByName(name);

    }

    @RequestMapping("/byfilter/ville/{ville}/categorie/{categorie}")
    public ArrayList<produit> getProduitByfiltrage(@PathVariable("ville") String ville, @PathVariable("categorie") String categorie) {

        return produitDao.rechercheByFilter(ville,categorie);

    }

    @RequestMapping("/byid/{id}")
    public produit getProduitById(@PathVariable("id") String id) {

        return produitDao.rechercheByID(id);

    }


}