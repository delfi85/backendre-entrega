package backendDeAplicaciones.bicicletas.controller;

import backendDeAplicaciones.bicicletas.controller.response.FinalizacionAlquilerResponse;
import backendDeAplicaciones.bicicletas.service.AlquilerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alquileres")
public class AlquilerController {

    private final AlquilerService alquilerService;

    @Autowired
    public AlquilerController(AlquilerService alquilerService) {
        this.alquilerService = alquilerService;
    }

    @PostMapping("/nuevo")
    public ResponseEntity<Alquiler> crearNuevoAlquiler(
            @RequestParam String idCliente,
            @RequestParam Long estacionRetiroId
    ) {
        Alquiler alquiler = alquilerService.crearNuevoAlquiler(idCliente, estacionRetiroId);

        // Devuelve una respuesta exitosa con el objeto Alquiler creado y el código 201 (CREATED).
        return new ResponseEntity<>(alquiler, HttpStatus.CREATED);
    }
    @PostMapping("/finalizar")
    public ResponseEntity<FinalizacionAlquilerResponse> finalizarAlquiler(
            @RequestParam Long idAlquiler,
            @RequestParam Long estacionDevolucion,
            @RequestParam(defaultValue = "ARS") String moneda
    ) {
        FinalizacionAlquilerResponse response = alquilerService.finalizarAlquiler(idAlquiler, estacionDevolucion, moneda);

        // Devuelve una respuesta con el objeto FinalizacionAlquilerResponse y el código 200 (OK).
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<Alquiler>> obtenerAlquileresPorCliente(@PathVariable String idCliente) {
        List<Alquiler> alquiler = alquilerService.obtenerAlquileresPorCliente(idCliente);

        // Devuelve una respuesta con el objeto Alquiler y el código 200 (OK).
        return new ResponseEntity<>(alquiler, HttpStatus.OK);
    }
}