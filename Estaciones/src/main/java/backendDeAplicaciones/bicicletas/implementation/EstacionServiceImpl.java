package backendDeAplicaciones.bicicletas.implementation;

import backendDeAplicaciones.bicicletas.entity.Estacion;
import backendDeAplicaciones.bicicletas.repositories.EstacionRepository;
import backendDeAplicaciones.bicicletas.service.EstacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*

////////////////     EstacionServiceImplementation la implementa,            ///////////////
///////////     conectando la lógica de negocio con la capa de persistencia.   //////////////

Esta clase es una implementación concreta de la interfaz EstacionService.
    Contiene la lógica de negocio real de tu aplicación relacionada con las estaciones.

En esta clase, implementas los métodos definidos en EstacionService, utilizando
    EstacionRepository u otras dependencias necesarias para realizar operaciones
    en la base de datos.

Es aquí donde se encuentran los detalles concretos de cómo se gestionan las estaciones
en tu aplicación.
*/

@Service
public class EstacionServiceImpl implements EstacionService {

    private final EstacionRepository estacionRepository;

    @Autowired
    public EstacionServiceImpl(EstacionRepository estacionRepository) {
        this.estacionRepository = estacionRepository;
    }

    @Override
    public List<Estacion> getAll() {
        return estacionRepository.findAll();
    }

    @Override
    public void add(Estacion entity) {
        estacionRepository.save(entity);
    }

    @Override
    public void update(Estacion entity) {
        estacionRepository.save(entity);
    }

    @Override
    public Estacion delete(Long id) {
        Estacion estacion = estacionRepository.findById(id).orElse(null);
        if (estacion != null) {
            estacionRepository.delete(estacion);
        }
        return estacion;
    }

    @Override
    public Estacion getById(Long id) {
        return estacionRepository.findById(id).orElse(null);
    }

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
