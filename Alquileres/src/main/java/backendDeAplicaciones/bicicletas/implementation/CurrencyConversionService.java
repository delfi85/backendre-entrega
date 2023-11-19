package backendDeAplicaciones.bicicletas.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;
import backendDeAplicaciones.bicicletas.response.MyResponseClass;

@Service
public class CurrencyConversionService {
    private final RestTemplate restTemplate;
    private String accessToken; // Token de acceso para la API

    @Autowired
    public CurrencyConversionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void obtainAccessToken(String username, String password) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("client_id", "cliente-tpi");
        body.add("client_secret", "ZmiMMce6zh4xKHde1FFKbfkiThDyYpyn");
        body.add("scope", "openid tpi-scope");
        body.add("grant_type", "password");
        body.add("username", "85680@sistemas.frc.utn.edu.ar");
        body.add("password", "85680-2023");

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, headers);

        ResponseEntity<TokenResponse> response = restTemplate.postForEntity(
                "https://labsys.frc.utn.edu.ar/aim/realms/backend-tps/protocol/openid-connect/token",
                entity,
                TokenResponse.class
        );

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            this.accessToken = response.getBody().getToken();
        } else {
            throw new RuntimeException("No se pudo obtener el token de acceso");
        }
    }

    public String getAccessToken() {
        return accessToken;
    }

    public Double convertCurrency(String targetCurrency, Double amount) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(accessToken);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("moneda_destino", targetCurrency);
        requestBody.put("importe", amount);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<MyResponseClass> response = restTemplate.postForEntity(
                "http://34.82.105.125:8080/convertir",
                requestEntity,
                MyResponseClass.class
        );

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return response.getBody().getImporte();
        } else {
            throw new RuntimeException("Error al realizar la conversión de moneda");
        }


    }



    // Clases internas o adicionales si son necesarias
    private static class TokenResponse {
        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }


}






