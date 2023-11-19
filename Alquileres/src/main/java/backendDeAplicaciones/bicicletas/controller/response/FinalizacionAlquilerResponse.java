package backendDeAplicaciones.bicicletas.controller.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FinalizacionAlquilerResponse {
     Alquiler alquiler;
     String monedaElegida;

}
