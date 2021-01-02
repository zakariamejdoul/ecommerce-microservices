package com.ecommerce.auth.dao;

import com.ecommerce.auth.model.ERole;
import com.ecommerce.auth.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleDao extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
