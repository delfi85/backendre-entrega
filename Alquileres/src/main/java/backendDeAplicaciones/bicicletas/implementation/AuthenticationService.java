package backendDeAplicaciones.bicicletas.implementation;


import backendDeAplicaciones.bicicletas.implementation.CurrencyConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final CurrencyConversionService currencyConversionService;

    @Autowired
    public AuthenticationService(CurrencyConversionService currencyConversionService) {
        this.currencyConversionService = currencyConversionService;
    }

    public String login(String username, String password) {
        currencyConversionService.obtainAccessToken(username, password);
        return currencyConversionService.getAccessToken();
    }
}