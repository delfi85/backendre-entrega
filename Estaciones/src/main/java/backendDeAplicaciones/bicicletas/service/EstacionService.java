package backendDeAplicaciones.bicicletas.service;

import backendDeAplicaciones.bicicletas.entity.Estacion;
import backendDeAplicaciones.bicicletas.repositories.EstacionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/*

//////////           EstacionService define la lógica de negocio               ///////////

Esta clase define operaciones o métodos relacionados con la lógica de negocio.
    Por ejemplo, podría incluir métodos para validar datos, aplicar reglas de negocio
    y orquestar operaciones relacionadas con las estaciones.

Proporciona una abstracción de alto nivel para interactuar con las estaciones sin
   preocuparse por los detalles de la base de datos o la implementación subyacente.

No contiene la implementación real de estos métodos, solo las firmas de los mismos.
*/
public interface EstacionService extends  Service<Estacion, Long> {

    List<Estacion> getAll();

}
