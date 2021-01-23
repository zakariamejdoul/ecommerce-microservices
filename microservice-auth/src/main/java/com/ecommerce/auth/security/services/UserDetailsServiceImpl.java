package com.ecommerce.auth.security.services;

import com.ecommerce.auth.model.Client;
import com.ecommerce.auth.payload.request.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    RestTemplate restTemplate;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://microservice-client/api/client/username")
                .queryParam("username", username);
        HttpEntity<SignUpRequest> entity = new HttpEntity<SignUpRequest>(headers);
        Client user = restTemplate.getForObject(builder.toUriString(),Client.class, entity);
        return UserDetailsImpl.build(user);
    }

}