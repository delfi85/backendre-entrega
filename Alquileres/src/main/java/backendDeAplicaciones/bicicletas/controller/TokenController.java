package backendDeAplicaciones.bicicletas.controller;

import backendDeAplicaciones.bicicletas.implementation.CurrencyConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/token")
public class TokenController {

    private final CurrencyConversionService currencyConversionService;

    @Autowired
    public TokenController(CurrencyConversionService currencyConversionService) {
        this.currencyConversionService = currencyConversionService;
    }

    @PostMapping("/get-token")
    @ResponseBody
    public ResponseEntity<String> getToken(@RequestParam String username, @RequestParam String password) {
        currencyConversionService.obtainAccessToken(username, password);
        String token = currencyConversionService.getAccessToken();
        return ResponseEntity.ok(token);
    }
}

