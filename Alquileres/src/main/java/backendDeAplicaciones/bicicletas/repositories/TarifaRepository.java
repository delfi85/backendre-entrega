package backendDeAplicaciones.bicicletas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarifaRepository extends JpaRepository<Tarifa, Long> {
    Tarifa findTarifaByDiaSemana(int diaSemana);

    Tarifa findTarifaByDiaMesAndMesAndAnio(int diaMes, int mes, int anio);
}