package com.grupo1.bakendsistemprod.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.grupo1.bakendsistemprod.services.DimensionService;
import com.grupo1.bakendsistemprod.entities.Dimension;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.grupo1.bakendsistemprod.services.DimensionExistenteException;



@RestController
@RequestMapping("/dimensiones") // Define la ruta base para todas las solicitudes relacionadas con dimensiones
public class DimensionController {

    // Inyecta el servicio de Dimension
    @Autowired
    private DimensionService dimensionService;

    // Define un punto final para crear una nueva dimensión
    @PostMapping("/")
public ResponseEntity<?> createDimension(@RequestBody Dimension dimension) {
    try {
        Dimension nuevaDimension = dimensionService.createDimension(dimension);
        return new ResponseEntity<>(nuevaDimension, HttpStatus.CREATED);
    } catch (DimensionExistenteException ex) {
        // Manejar la excepción cuando la dimensión ya existe       
        return new ResponseEntity<>("La dimensión ya existe en la base de datos.", HttpStatus.CONFLICT);

    }
}


    // Define un punto final para obtener todas las dimensiones
    @GetMapping("/")
    public ResponseEntity<List<Dimension>> getAllDimensions() {
        List<Dimension> dimensiones = dimensionService.getAllDimensions();
        return new ResponseEntity<>(dimensiones, HttpStatus.OK);
        
    }

    // Define un punto final para obtener una dimensión por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Dimension> getDimensionById(@PathVariable Short id) {
        Dimension dimension = dimensionService.getDimensionById(id);
        return new ResponseEntity<>(dimension, HttpStatus.OK);
    }

    // Define un punto final para obtener una dimensión por nombre
    @GetMapping("/buscar")
    public ResponseEntity<Dimension> buscarPorDimension(@RequestParam String dimension) {
        return dimensionService.getDimensionByDescription(dimension);
    }


    

    // Define un punto final para actualizar una dimensión por su ID
@PutMapping("/{id}")
public ResponseEntity<String> updateDimension(@PathVariable Short id, @RequestBody Dimension dimension) {
    String mensaje = dimensionService.updateDimension(id, dimension);
    if (mensaje != null) {
        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    } else {
        return new ResponseEntity<>("No se pudo actualizar la dimensión", HttpStatus.BAD_REQUEST);
    }
}


    // Define un punto final para eliminar una dimensión por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDimension(@PathVariable Short id) {
        dimensionService.deleteDimension(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
