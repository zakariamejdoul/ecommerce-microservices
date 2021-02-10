package com.ecommerce.microserviceproduit.doa;

import com.ecommerce.microserviceproduit.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduitDAO extends JpaRepository<Produit, Long> {
    Iterable<Produit> findAllByProprietaire(String proprietaire);


}
