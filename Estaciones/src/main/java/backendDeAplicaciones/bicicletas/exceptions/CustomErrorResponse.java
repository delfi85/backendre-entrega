package backendDeAplicaciones.bicicletas.exceptions;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomErrorResponse {
    private String error;
    private String message;

    public CustomErrorResponse(String error, String message) {
        this.error = error;
        this.message = message;
    }

    // Getters y setters
}





