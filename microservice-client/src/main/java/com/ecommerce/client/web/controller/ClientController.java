package com.ecommerce.client.web.controller;


import com.ecommerce.client.dao.ClientDao;
import com.ecommerce.client.model.Client;
import com.ecommerce.client.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/client")
public class ClientController {

    @Autowired
    private ClientDao clientDao;

    @PostMapping(path = "/add")
    public ResponseEntity<Client> addNewUser(@RequestBody Client client) {
        try {
            Client _client = clientDao.save(new Client(client.getLogin(), client.getPassword(), Role.user));
            return new ResponseEntity<>(_client, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Client>> getAllClients(@RequestParam(required = false) Role role) {
        try {
            List<Client> clients = new ArrayList<Client>();

            if (role == null)
                clientDao.findAll().forEach(clients::add);
            else
                clientDao.findByRole(role).forEach(clients::add);

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
    public ResponseEntity<Client> updateClient(@PathVariable("id") String id, @RequestBody Client client ) {
        Optional<Client> clientData = clientDao.findById(id);

        if(clientData.isPresent()){
            Client _client = clientData.get();
            _client.setLogin(client.getLogin());
            _client.setPassword(client.getPassword());
            return new ResponseEntity<>(clientDao.save(_client), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
