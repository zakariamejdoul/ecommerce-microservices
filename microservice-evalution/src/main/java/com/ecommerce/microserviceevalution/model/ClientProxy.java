package com.ecommerce.microserviceevalution.model;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class ClientProxy {
    @Autowired
    private RestTemplate restTemplate;


    @HystrixCommand(fallbackMethod = "getuserIsAuthFallBack",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")
            })

    public ResponseEntity<?> userIsAuth(@RequestParam("Authorization") String authorization) {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", authorization.trim());
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                "http://microservice-auth/api/test/user",
                HttpMethod.GET,
                entity,
                String.class);

        return ResponseEntity.ok(Objects.requireNonNull(response.getBody()));
    }


    public ResponseEntity<?> getuserIsAuthFallBack(String authorization){
        return new ResponseEntity<>(HttpStatus.LOCKED);
    }


    @HystrixCommand(fallbackMethod = "getuserNameFallBack",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")
            })
    public String getUsername(String auth){
        return restTemplate.getForObject("http://microservice-auth/api/auth/getusername/"+auth, String.class);
    }

    public String getuserNameFallBack(String auth){
        return "";
    }
}
