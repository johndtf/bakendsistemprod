package com.grupo1.bakendsistemprod.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grupo1.bakendsistemprod.entities.Empleado;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    List<Empleado> findByNombre(String nombre);
    Optional<Empleado> findByCedula(Integer cedula);
    Optional<Empleado> findByUsuario(String usuario);
    Optional<Empleado> findByEmail(String email);
}


