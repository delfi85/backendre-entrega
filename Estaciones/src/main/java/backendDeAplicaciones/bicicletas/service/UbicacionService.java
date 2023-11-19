package backendDeAplicaciones.bicicletas.service;

import backendDeAplicaciones.bicicletas.entity.Estacion;

public interface UbicacionService {
    Estacion encontrarEstacionMasCercana(double latitudCliente, double longitudCliente);
    double calcularDistancia(double lat1, double lon1, double lat2, double lon2);
}