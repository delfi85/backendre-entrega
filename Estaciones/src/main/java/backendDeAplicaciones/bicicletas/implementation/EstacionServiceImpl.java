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


}
