package com.grupo1.bakendsistemprod.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity // Anotación que indica que esta clase es una entidad JPA.
@Table(name = "dimensiones") // Especifica el nombre de la tabla en la base de datos.

public class Dimension {

    @Id // Marca el campo como la clave primaria de la entidad.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera valores de clave primaria de manera automática.
    private Short id_dimension; // Campo de la clave primaria.

    private String dimension; // Otro campo de la entidad.

    // Métodos getters y setters para acceder y modificar los campos de la entidad.

    public Short getId_dimension() {
        return id_dimension;
    }

    public void setId_dimension(Short id_dimension) {
        this.id_dimension = id_dimension;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }
}
