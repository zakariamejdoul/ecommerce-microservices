package com.ecommerce.microserviceevalution.dao;


import com.ecommerce.microserviceevalution.model.Avis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;




@Repository
public interface AvisDao extends JpaRepository<Avis, Long> {
    Iterable<Avis> findAvisByIdProduit(int idProduit);
    Iterable<Avis> findAvisByUsername(String idClient);
    Avis findByIdAvis(long id);
    @Query("select idAvis, username,designation, avis from Avis")
    Iterable<Avis> findIdAvisAndUsernameAndDesignationAndAvis();

    Iterable<Avis> findIdAvisAndUsernameAndDesignationAndAvisByIdAvis(long idAvis);

    Iterable<Avis> findIdAvisAndUsernameAndDesignationAndAvisByIdProduit(int idProduit);





}
