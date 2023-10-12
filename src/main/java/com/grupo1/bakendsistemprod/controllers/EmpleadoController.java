package com.grupo1.bakendsistemprod.controllers;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.grupo1.bakendsistemprod.entities.Empleado;
import com.grupo1.bakendsistemprod.services.EmpleadoService;
import com.grupo1.bakendsistemprod.services.EmpleadoExistsException;
import com.grupo1.bakendsistemprod.services.EmpleadoNotFoundException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

   // @Autowired
    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @PostMapping("/")
public ResponseEntity<?> crearEmpleado(@RequestBody Empleado empleado) {
    try {
        Empleado nuevoEmpleado = empleadoService.crearEmpleado(empleado);
        return new ResponseEntity<>(nuevoEmpleado, HttpStatus.CREATED);
    } catch (EmpleadoExistsException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}


    @GetMapping("/")
    public ResponseEntity<List<Empleado>> obtenerTodosLosEmpleados() {
        List<Empleado> empleados = empleadoService.obtenerTodosLosEmpleados();
        return new ResponseEntity<>(empleados, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> obtenerEmpleadoPorId(@PathVariable Long id) {
        Optional<Empleado> empleado = empleadoService.obtenerEmpleadoPorId(id);
        return empleado.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Empleado>> buscarEmpleadosPorNombre(@RequestParam String nombre) {
        List<Empleado> empleados = empleadoService.buscarEmpleadosPorNombre(nombre);
        return new ResponseEntity<>(empleados, HttpStatus.OK);
    }

    @GetMapping("/buscar/{cedula}")
    public ResponseEntity<Empleado> buscarEmpleadoPorCedula(@PathVariable Integer cedula) {
        Optional<Empleado> empleado = empleadoService.buscarEmpleadoPorCedula(cedula);

        return empleado.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(
            @PathVariable Long id,
            @RequestBody Empleado empleadoActualizado) {
        try {
            Empleado empleadoActualizadoEnBD = empleadoService.actualizarEmpleado(id, empleadoActualizado);
            return ResponseEntity.ok(empleadoActualizadoEnBD);
        } catch (EmpleadoNotFoundException e) {
            // Manejo de la excepción y respuesta adecuada
            return ResponseEntity.notFound().build();
        }
    }
   @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarEmpleado(@PathVariable Long id) {
    try {
        empleadoService.eliminarEmpleado(id);
        return new ResponseEntity<>("Registro eliminado con éxito", HttpStatus.OK);
    } catch (EmpleadoNotFoundException e) {
        return new ResponseEntity<>("Empleado no encontrado con ID: " + id, HttpStatus.NOT_FOUND);
    } catch (Exception e) {
        return new ResponseEntity<>("No se pudo eliminar el registro", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    } 
}

