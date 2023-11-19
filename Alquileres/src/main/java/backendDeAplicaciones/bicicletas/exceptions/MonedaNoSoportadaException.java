package backendDeAplicaciones.bicicletas.exceptions;

public class MonedaNoSoportadaException extends RuntimeException {
    public MonedaNoSoportadaException(String message) {
        super(message);
    }
}
