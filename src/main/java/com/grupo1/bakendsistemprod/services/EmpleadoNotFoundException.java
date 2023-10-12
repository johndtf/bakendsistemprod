package com.grupo1.bakendsistemprod.services;

public class EmpleadoNotFoundException extends RuntimeException {
    public EmpleadoNotFoundException(String message) {
        super(message);
    }
}
