package com.grupo1.bakendsistemprod.services;

import com.grupo1.bakendsistemprod.entities.Dimension;
import com.grupo1.bakendsistemprod.repositories.DimensionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service // Anotación que marca esta clase como un servicio de Spring.

public class DimensionService {

    private final DimensionRepository dimensionRepository; // Inyección de dependencia del repositorio.

    public DimensionService(DimensionRepository dimensionRepository) {
        this.dimensionRepository = dimensionRepository;
    }

    public List<Dimension> getAllDimensions() {
        return dimensionRepository.findAll();
    }

    public Dimension getDimensionById(Short id) {
        return dimensionRepository.findById(id).orElse(null);
    }

    public ResponseEntity<Dimension> getDimensionByDescription(String dimensionDescription) {
        Dimension dimensionEncontrada = dimensionRepository.findByDimension(dimensionDescription);

        if (dimensionEncontrada != null) {
            return new ResponseEntity<>(dimensionEncontrada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public void deleteDimension(Short id) {
        dimensionRepository.deleteById(id);
    }

    // Método para crear una nueva dimensión
    public Dimension createDimension(Dimension dimension) {
        // Verifica si ya existe una dimensión con la misma descripción
        Dimension existingDimension = dimensionRepository.findByDimension(dimension.getDimension());
    
        if (existingDimension == null) {
            // No existe una dimensión con esta descripción, por lo que se crea una nueva
            return dimensionRepository.save(dimension);
        } else {
            // Ya existe una dimensión con esta descripción, lanza una excepción
            throw new DimensionExistenteException("La dimensión ya existe en la base de datos.");
        }
    }
    
    // Método para actualizar una dimensión existente
    public String updateDimension(Short id, Dimension dimensionActualizada) {
        // Verificar si la nueva dimensión ya existe en la base de datos
        Dimension existingDimension = dimensionRepository.findByDimension(dimensionActualizada.getDimension());
    
        if (existingDimension == null || existingDimension.getId_dimension().equals(id)) {
            // No se encontró la nueva dimensión en la base de datos o se está actualizando la misma dimensión
            // Buscar la dimensión existente por su ID
            Optional<Dimension> optionalDimension = dimensionRepository.findById(id);
    
            if (optionalDimension.isPresent()) {
                Dimension dimensionExistente = optionalDimension.get();
                // Actualizar la descripción de la dimensión existente
                dimensionExistente.setDimension(dimensionActualizada.getDimension());
                dimensionRepository.save(dimensionExistente);
                return "La dimensión se ha actualizado con éxito.";
            } else {
                // No se encontró una dimensión existente para actualizar
                return "No se encontró una dimensión existente para actualizar.";
            }
        } else {
            // La nueva dimensión ya existe en la base de datos
            return "No se puede realizar la actualización, el nombre de la dimensión ya está en uso.";
        }
    }
}

