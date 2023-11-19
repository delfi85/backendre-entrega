package backendDeAplicaciones.bicicletas.repositories;

import backendDeAplicaciones.bicicletas.entity.Estacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*

////////    EstacionRepository se encarga de la interacción con la base de datos   /////////

Esta clase generalmente es una interfaz que extiende JpaRepository.

Se utiliza para realizar operaciones de persistencia en la base de datos, como guardar,
    actualizar, recuperar y eliminar registros relacionados con la entidad "Estación"
    en tu aplicación.

Define métodos de consulta que se traducen automáticamente en consultas SQL
    por Spring Data JPA.

No contiene lógica de negocio, solo se encarga de la interacción con la base de datos.*/

@Repository
public interface EstacionRepository extends JpaRepository<Estacion, Long> {
}
