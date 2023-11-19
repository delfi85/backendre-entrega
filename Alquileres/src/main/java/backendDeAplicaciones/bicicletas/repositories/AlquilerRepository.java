package backendDeAplicaciones.bicicletas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlquilerRepository extends JpaRepository<Alquiler, Long> {
    List<Alquiler> findByIdCliente(String idCliente);
}

