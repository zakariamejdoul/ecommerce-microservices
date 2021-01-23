package com.ecommerce.client.web.controller;


import com.ecommerce.client.payload.request.MessageResponse;
import com.ecommerce.client.dao.ClientDao;
import com.ecommerce.client.dao.RoleDao;
import com.ecommerce.client.model.Client;
import com.ecommerce.client.model.ERole;
import com.ecommerce.client.model.Role;
import com.ecommerce.client.payload.request.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping(path = "/api/client")
public class ClientController {

    @Autowired
    private ClientDao clientDao;

    @Autowired
    private RoleDao roleDao;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    PasswordEncoder encoder;


    @PostMapping(path = "/add")
    public ResponseEntity<?> addNewUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (clientDao.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (clientDao.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user
        Client user = new Client(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleDao.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin" -> {
                        Role adminRole = roleDao.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                    }
                    case "mod" -> {
                        Role modRole = roleDao.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);
                    }
                    default -> {
                        Role userRole = roleDao.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                    }
                }
            });
        }
        user.setRoles(roles);
        clientDao.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Client>> getAllClients() {
        try {
            List<Client> clients = new ArrayList<Client>();


            clients.addAll(clientDao.findAll());


            if (clients.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(clients, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable("id") String id) {
        Optional<Client> clientData = clientDao.findById(id);

        return clientData.map(client -> new ResponseEntity<>(client, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateClient(@PathVariable("id") String id, @RequestBody Client signUpRequest) {
        Optional<Client> clientData = clientDao.findById(id);

        if (clientData.isPresent()) {
            if (clientDao.existsByUsername(signUpRequest.getUsername())) {
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: Username is already taken!"));
            }

            if (clientDao.existsByEmail(signUpRequest.getEmail())) {
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: Email is already in use!"));
            }

            Client _client = clientData.get();
            _client.setUsername(signUpRequest.getUsername());
            _client.setEmail(signUpRequest.getEmail());
            _client.setPassword(encoder.encode(signUpRequest.getPassword()));
            return new ResponseEntity<>(clientDao.save(_client), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
