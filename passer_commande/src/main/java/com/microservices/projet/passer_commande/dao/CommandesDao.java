package com.microservices.projet.passer_commande.dao;

import com.microservices.projet.passer_commande.model.Commande;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandesDao extends MongoRepository<Commande, Integer> {
    Commande findById(int idCommande);
    Commande save(Commande commande);
}