package backendDeAplicaciones.bicicletas.implementation;

import backendDeAplicaciones.bicicletas.response.EstacionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class EstacionServiceImpl {

    public EstacionDto obtenerEstacion(Long estacionId) {
        RestTemplate restTemplate = new RestTemplate();
        if (estacionId != null) {
            ResponseEntity<EstacionDto> responseEntity = restTemplate.getForEntity(
                    "http://localhost:8080/estaciones/" + estacionId,
                    EstacionDto.class);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return responseEntity.getBody();
            } else {
                throw new RuntimeException("Error al buscar la estacion!");
            }
        }
        return null;
    }
}