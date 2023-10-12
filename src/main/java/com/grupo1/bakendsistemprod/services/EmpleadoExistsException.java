package com.grupo1.bakendsistemprod.services;

public class EmpleadoExistsException extends RuntimeException {
    public EmpleadoExistsException(String message) {
        super(message);
    }
}
