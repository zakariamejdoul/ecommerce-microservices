package com.ecommerce.client.dao;

import com.ecommerce.client.model.ERole;
import com.ecommerce.client.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleDao extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
