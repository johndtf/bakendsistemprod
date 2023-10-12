package com.grupo1.bakendsistemprod.services;

import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.grupo1.bakendsistemprod.entities.Empleado;
import com.grupo1.bakendsistemprod.repositories.EmpleadoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

  
    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    public Empleado crearEmpleado(Empleado empleado) {
        Optional<Empleado> existingEmpleadoByCedula = empleadoRepository.findByCedula(empleado.getCedula());
        Optional<Empleado> existingEmpleadoByUser = empleadoRepository.findByUsuario(empleado.getUsuario());
        Optional<Empleado> existingEmpleadoByEmail = empleadoRepository.findByEmail(empleado.getEmail());
    
        if (existingEmpleadoByCedula.isPresent()) {
            throw new EmpleadoExistsException("Ya existe un empleado con esta misma Cédula");
        } else if (existingEmpleadoByUser.isPresent()) {
            throw new EmpleadoExistsException("Ya existe un empleado con el mismo usuario");
        } else if (existingEmpleadoByEmail.isPresent()) {
            throw new EmpleadoExistsException("Ya existe un empleado con ete mismo Email");
        }
    
        return empleadoRepository.save(empleado);
    }
    
    

    public List<Empleado> obtenerTodosLosEmpleados() {
        return empleadoRepository.findAll();
    }

    public Optional<Empleado> obtenerEmpleadoPorId(Long id) {
        return empleadoRepository.findById(id);
    }

    public Optional<Empleado> buscarEmpleadoPorCedula(Integer cedula) {
        return empleadoRepository.findByCedula(cedula);
    }

    public List<Empleado> buscarEmpleadosPorNombre(String nombre) {
        return empleadoRepository.findByNombre(nombre);
    }

    public Empleado actualizarEmpleado(Long empleadoId, Empleado empleadoActualizado) {
    // Verifica si el empleado existe en la base de datos
    Optional<Empleado> optionalEmpleado = empleadoRepository.findById(empleadoId);

    if (optionalEmpleado.isPresent()) {
        Empleado empleadoExistente = optionalEmpleado.get();

        // Copia todos los campos de empleadoActualizado al empleadoExistente
        // Excepto el ID, ya que no se debe modificar
        BeanUtils.copyProperties(empleadoActualizado, empleadoExistente, "id");

        // Guarda el empleado actualizado en la base de datos
        return empleadoRepository.save(empleadoExistente);
    } else {
        // Maneja el caso en el que el empleado no se encuentra
        throw new EmpleadoNotFoundException("Empleado no encontrado con ID: " + empleadoId);
    }
}


public void eliminarEmpleado(Long id) {
    Optional<Empleado> optionalEmpleado = empleadoRepository.findById(id);
    if (optionalEmpleado.isPresent()) {
        empleadoRepository.deleteById(id);
    } else {
        throw new EmpleadoNotFoundException("Empleado no encontrado con ID: " + id);
    }
}

}
