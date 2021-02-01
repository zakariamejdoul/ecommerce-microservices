package com.ecommerce.microserviceevalution.model;

public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException() {

    }
    public ResourceNotFoundException(String s) {
        super(s);
    }
}
