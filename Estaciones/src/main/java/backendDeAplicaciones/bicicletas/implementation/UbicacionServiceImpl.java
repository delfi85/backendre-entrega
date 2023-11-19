package backendDeAplicaciones.bicicletas.implementation;

import backendDeAplicaciones.bicicletas.entity.Estacion;
import backendDeAplicaciones.bicicletas.repositories.EstacionRepository;
import backendDeAplicaciones.bicicletas.service.UbicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UbicacionServiceImpl implements UbicacionService {

    @Autowired
    private EstacionRepository estacionRepository;

    @Override
    public Estacion encontrarEstacionMasCercana(double latitudCliente, double longitudCliente) {
        // Obtiene la lista de todas las estaciones desde el repositorio.
        List<Estacion> estaciones = estacionRepository.findAll();

        // Inicializa la estación más cercana y la distancia mínima con valores iniciales.
        Estacion estacionMasCercana = null;
        double distanciaMinima = Double.MAX_VALUE;

        // Itera a través de todas las estaciones para encontrar la más cercana.
        for (Estacion estacion : estaciones) {
            // Calcula la distancia entre la ubicación del cliente y la estación actual.
            double distancia = calcularDistancia(latitudCliente, longitudCliente, estacion.getLatitud(), estacion.getLongitud());

            // Comprueba si la distancia actual es menor que la distancia mínima encontrada hasta ahora.
            if (distancia < distanciaMinima) {
                // Actualiza la estación más cercana y la distancia mínima.
                distanciaMinima = distancia;
                estacionMasCercana = estacion;
            }
        }

        // Devuelve la estación más cercana encontrada.
        return estacionMasCercana;
    }

    public double calcularDistancia(double lat1, double lon1, double lat2, double lon2) {
        // Cada grado corresponde a 110 kilometros
        double distanciaLat = (lat2 - lat1) * 110;
        double distanciaLon = (lon2 - lon1) * 110;

        // Calcula la distancia euclidiana
        double distancia = Math.sqrt(Math.pow(distanciaLat, 2) + Math.pow(distanciaLon, 2));
        return distancia;
    }
}