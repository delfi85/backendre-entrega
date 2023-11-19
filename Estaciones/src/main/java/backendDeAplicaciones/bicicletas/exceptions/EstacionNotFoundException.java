package backendDeAplicaciones.bicicletas.exceptions;

public class EstacionNotFoundException extends RuntimeException {
    public EstacionNotFoundException(String message) {
        super(message);
    }
}