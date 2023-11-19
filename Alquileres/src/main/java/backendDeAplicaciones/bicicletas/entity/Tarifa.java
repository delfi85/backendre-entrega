package backendDeAplicaciones.bicicletas.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "TARIFAS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tarifa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TIPO_TARIFA")
    private Long tipoTarifa;

    @Column(name = "DEFINICION")
    private String definicion;

    @Column(name = "DIA_SEMANA")
    private Long diaSemana;

    @Column(name = "DIA_MES")
    private Long diaMes;

    @Column(name = "MES")
    private Long mes;

    @Column(name = "ANIO")
    private Long anio;

    @Column(name = "MONTO_FIJO_ALQUILER")
    private float montoFijoAlquiler;

    @Column(name = "MONTO_MINUTO_FRACCION")
    private float montoMinutoFraccion;

    @Column(name = "MONTO_KM")
    private float montoKm;

    @Column(name = "MONTO_HORA")
    private float MontoHora;

}
