package backendDeAplicaciones.bicicletas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ALQUILERES")
public class Alquiler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ID_CLIENTE")
    private String idCliente;

    @Column(name = "ESTADO")
    private Long estado;

    @Column(name = "FECHA_HORA_RETIRO")
    private LocalDateTime fechaHoraRetiro;

    @Column(name = "FECHA_HORA_DEVOLUCION")
    private LocalDateTime fechaHoraDevolucion;

    @Column(name = "MONTO")
    private double monto;

    @OneToOne
    @JoinColumn(name = "ID_TARIFA", referencedColumnName = "ID")
    private Tarifa tarifa;

    @OneToOne
    @JoinColumn(name = "ESTACION_RETIRO", referencedColumnName = "ID")
    private Estacion estacionRetiro;

    @OneToOne
    @JoinColumn(name = "ESTACION_DEVOLUCION", referencedColumnName = "ID")
    private Estacion estacionDevolucion;
}
