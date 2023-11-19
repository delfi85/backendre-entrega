package backendDeAplicaciones.bicicletas.exceptions;

public class AlquilerFinalizadoException extends RuntimeException{

    public AlquilerFinalizadoException(String message) {
        super(message);
    }
}
