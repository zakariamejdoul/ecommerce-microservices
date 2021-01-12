package com.ecommerce.auth.dao;

import java.util.Optional;

import com.ecommerce.auth.model.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientDao extends MongoRepository<Client, String> {
    Boolean existsByUsername(String username);
    Optional<Client> findByUsername(String username);
    Boolean existsByEmail(String email);
}
