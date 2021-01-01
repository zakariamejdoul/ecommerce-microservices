package com.ecommerce.client.dao;

import java.util.List;
import com.ecommerce.client.model.Client;
import com.ecommerce.client.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientDao extends MongoRepository<Client, String> {
    List<Client> findByRole(Role role);
}
