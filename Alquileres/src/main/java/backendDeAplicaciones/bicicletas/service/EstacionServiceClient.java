package backendDeAplicaciones.bicicletas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class EstacionServiceClient {

    @Autowired
    private RestTemplate restTemplate;

    private String apiGatewayUrl = "http://tu-url-del-api-gateway";


    public Map<String, Object> obtenerEstacionPorId(Long estacionId) {
        // Realiza una llamada al servicio de estaciones a través del API Gateway
        String url = apiGatewayUrl + "/estaciones/api/estaciones/" + estacionId;
        return restTemplate.getForObject(url, Map.class);
    }
}

// ver si cambio por id o queda all

