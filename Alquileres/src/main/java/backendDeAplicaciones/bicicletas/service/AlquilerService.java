package backendDeAplicaciones.bicicletas.service;

import backendDeAplicaciones.bicicletas.controller.response.FinalizacionAlquilerResponse;
import backendDeAplicaciones.bicicletas.entity.Alquiler;

import java.util.List;

public interface AlquilerService /*extends  Service<Alquiler, Long>*/{

    Alquiler crearNuevoAlquiler(String idCliente, Long estacionRetiro);
    FinalizacionAlquilerResponse finalizarAlquiler(Long idAlquiler, Long estacionDevolucion, String moneda);
    double calcularCostoAlquiler(Alquiler alquiler);
    List<Alquiler> obtenerAlquileresPorCliente(String idCliente);
}
