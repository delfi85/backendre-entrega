package backendDeAplicaciones.bicicletas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(AlquilerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public CustomErrorResponse handleAlquilerNotFoundException(AlquilerNotFoundException ex) {
        return new CustomErrorResponse("Alquiler no encontrado", ex.getMessage());
    }

    @ExceptionHandler(EstacionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public CustomErrorResponse handleEstacionNotFoundException(EstacionNotFoundException ex) {
        return new CustomErrorResponse("Estación de devolución no encontrada", ex.getMessage());
    }



    @ExceptionHandler(AlquilerFinalizadoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public CustomErrorResponse handleAlquilerFinalizadoException(AlquilerFinalizadoException ex) {
        return new CustomErrorResponse("El alquiler ya ha finalizado", ex.getMessage());
    }

    @ExceptionHandler(MonedaNoSoportadaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public CustomErrorResponse handleMonedaNoSoportadaException(MonedaNoSoportadaException ex) {
        return new CustomErrorResponse("Moneda no soportada", ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public CustomErrorResponse handleGeneralException(Exception ex) {
        return new CustomErrorResponse("Error interno del servidor", ex.getMessage());
    }
}