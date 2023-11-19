package backendDeAplicaciones.bicicletas.controller;

import backendDeAplicaciones.bicicletas.entity.Estacion;
import backendDeAplicaciones.bicicletas.service.UbicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ubicacion")
public class UbicacionController {

    @Autowired
    private UbicacionService ubicacionService;

    //2. Consultar los datos de la estación más cercana a una ubicación provista por el cliente.
    @GetMapping("/estacion-mas-cercana")
    public ResponseEntity<Estacion> encontrarEstacionMasCercana(
            @RequestParam("latitud") double latitud,
            @RequestParam("longitud") double longitud) {
        Estacion estacionMasCercana = ubicacionService.encontrarEstacionMasCercana(latitud, longitud);

        if (estacionMasCercana != null) {
            return ResponseEntity.ok(estacionMasCercana);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}