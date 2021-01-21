package com.ecommerce.commande.dao;

import com.ecommerce.commande.model.Commande;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandeDao extends MongoRepository<Commande, Integer> {
    Commande findById(int idCommande);
    Commande save(Commande commande);
}