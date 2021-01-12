package com.ecommerce.paiement.dao;

import com.ecommerce.paiement.model.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaiementDao  extends JpaRepository<Paiement, Integer> {

    Paiement findByIdCommande(int idCommande);
}
