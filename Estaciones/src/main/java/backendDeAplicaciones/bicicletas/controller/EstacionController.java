package backendDeAplicaciones.bicicletas.controller;

import backendDeAplicaciones.bicicletas.entity.Estacion;
import backendDeAplicaciones.bicicletas.service.EstacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estaciones")
public class EstacionController {

    private final EstacionService estacionService;

    @Autowired
    public EstacionController(EstacionService estacionService) {
        this.estacionService = estacionService;
    }

    // 1. Consultar el listado de todas las estaciones disponibles en la ciudad
    @GetMapping("/all")
    public List<Estacion> obtenerTodasLasEstaciones() {
        return estacionService.getAll();
    }


    // 5. Agregar una nueva estación al sistema
    @PostMapping
    public void agregarEstacion(@RequestBody Estacion estacion) {
        estacionService.add(estacion);
    }

    @PutMapping("/{id}")
    public void actualizarEstacion(@PathVariable Long id, @RequestBody Estacion estacion) {
        // Verificar si la estación con el ID especificado existe antes de actualizar
        Estacion existingEstacion = estacionService.getById(id);
        if (existingEstacion != null) {
            estacion.setId(id); // Asegurar que el ID coincida con el de la solicitud
            estacionService.update(estacion);
        }
    }

    @DeleteMapping("/{id}")
    public void eliminarEstacion(@PathVariable Long id) {
        estacionService.delete(id);
    }

    @GetMapping("/{id}")
    public Estacion obtenerEstacionPorId(@PathVariable Long id) {
        return estacionService.getById(id);
    }

    @GetMapping("/estacion-mas-cercana")
    public ResponseEntity<Estacion> encontrarEstacionMasCercana(
            @RequestParam("latitud") double latitud,
            @RequestParam("longitud") double longitud) {
        Estacion estacionMasCercana = estacionService.encontrarEstacionMasCercana(latitud, longitud);

        if (estacionMasCercana != null) {
            return ResponseEntity.ok(estacionMasCercana);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}