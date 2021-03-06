package com.ecommerce.client.dao;

import java.util.List;
import java.util.Optional;

import com.ecommerce.client.model.Client;
import com.ecommerce.client.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientDao extends MongoRepository<Client, String> {
    Boolean existsByUsername(String username);
    Optional<Client> findByUsername(String username);
    Boolean existsByEmail(String email);
}
