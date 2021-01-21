package com.ecommerce.microserviceevalution.dao;


import com.ecommerce.microserviceevalution.model.Avis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface AvisDao extends JpaRepository<Avis, Long> {
    Iterable<Avis> findAvisByIdProduit(int idProduit);
    Iterable<Avis> findAvisByIdClient(int idClient);



}
