package backendDeAplicaciones.bicicletas.implementation;

import backendDeAplicaciones.bicicletas.controller.response.FinalizacionAlquilerResponse;

import backendDeAplicaciones.bicicletas.entity.Alquiler;
import backendDeAplicaciones.bicicletas.entity.Estacion;
import backendDeAplicaciones.bicicletas.entity.Tarifa;
import backendDeAplicaciones.bicicletas.exceptions.AlquilerNotFoundException;
import backendDeAplicaciones.bicicletas.exceptions.EstacionNotFoundException;
import backendDeAplicaciones.bicicletas.exceptions.MonedaNoSoportadaException;
import backendDeAplicaciones.bicicletas.repositories.AlquilerRepository;
import backendDeAplicaciones.bicicletas.repositories.EstacionRepository;
import backendDeAplicaciones.bicicletas.repositories.TarifaRepository;
import backendDeAplicaciones.bicicletas.service.AlquilerService;
import backendDeAplicaciones.bicicletas.service.UbicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AlquilerServiceImpl implements AlquilerService {

    private final CurrencyConversionService currencyConversionService;
    private final AlquilerRepository alquilerRepository;
    private final EstacionRepository estacionRepository;
    private final TarifaRepository tarifaRepository;
    private final UbicacionService ubicacionService;

    @Autowired
    public AlquilerServiceImpl(CurrencyConversionService currencyConversionService, AlquilerRepository alquilerRepository,
                               EstacionRepository estacionRepository,
                               TarifaRepository tarifaRespository,
                               UbicacionService ubicacionService) {
        this.currencyConversionService = currencyConversionService;
        this.alquilerRepository = alquilerRepository;
        this.estacionRepository = estacionRepository;
        this.tarifaRepository = tarifaRespository;
        this.ubicacionService = ubicacionService;

    }

    @Override
    public Alquiler crearNuevoAlquiler(String idCliente, Long estacionRetiroId) {
        Estacion estacionRetiro = estacionRepository.findById(estacionRetiroId).orElse(null);

        if (estacionRetiro == null) {
            // Manejar el caso en el que la estación no se encontró
            // Puedes lanzar una excepción o manejarlo de acuerdo a tus necesidades.
        }

        Alquiler alquiler = new Alquiler();
        alquiler.setIdCliente(idCliente);
        alquiler.setEstado(1L); // Estado 1 - iniciado
        alquiler.setEstacionRetiro(estacionRetiro);
        alquiler.setFechaHoraRetiro(LocalDateTime.now()); // Fecha y hora actual

//        Tarifa debería ser null al iniciar el alquiler
//        Tarifa tarifa = tarifaRepository.findById(1L).orElse(null);
//        alquiler.setTarifa(tarifa);

        // Establece estacionDevolucion, fechaHoraDevolucion, y monto como null
        alquilerRepository.save(alquiler);
        return alquiler;
    }

    @Override
    public FinalizacionAlquilerResponse finalizarAlquiler(Long idAlquiler, Long estacionDevolucion, String moneda) {
        Alquiler alquiler = alquilerRepository.findById(idAlquiler).orElseThrow(() ->
                new AlquilerNotFoundException("No se encontró el alquiler con ID: " + idAlquiler));

       // if (alquiler.getEstado() == 1) {
        //    throw new AlquilerFinalizadoException("El alquiler con ID: " + idAlquiler + " ya ha sido finalizado.");
        //}

        Estacion estacion = estacionRepository.findById(estacionDevolucion).orElseThrow(() ->
                new EstacionNotFoundException("No se encontró la estación de devolución con ID: " + estacionDevolucion));

        alquiler.setEstado(2L);
        alquiler.setEstacionDevolucion(estacion);
        alquiler.setFechaHoraDevolucion(LocalDateTime.now());

        // Obtén la tarifa aplicable
        LocalDateTime fechaActual = LocalDateTime.now();
        DayOfWeek diaSemana = fechaActual.getDayOfWeek();
        int dia = fechaActual.getDayOfMonth();
        int mes = fechaActual.getMonthValue();
        int anio = fechaActual.getYear();

        Tarifa tarifaDiaSemana = tarifaRepository.findTarifaByDiaSemana(diaSemana.getValue());
        Tarifa tarifaDiaMesAnio = tarifaRepository.findTarifaByDiaMesAndMesAndAnio(dia, mes, anio);
        Tarifa tarifa = (tarifaDiaMesAnio != null) ? tarifaDiaMesAnio : tarifaDiaSemana;

        alquiler.setTarifa(tarifa);

        // Calcula el costo del alquiler
        double monto = calcularCostoAlquiler(alquiler);

        // Realiza la conversión de moneda si es necesario
        if (!moneda.equalsIgnoreCase("ARS")) {
            try {
                monto = currencyConversionService.convertCurrency(moneda, monto);
            } catch (Exception e) {
                throw new MonedaNoSoportadaException("Error al convertir a la moneda: " + moneda);
            }
        }

        alquiler.setMonto(monto);
        alquilerRepository.save(alquiler);

        return new FinalizacionAlquilerResponse(alquiler, moneda);
    }


    @Override
    public double calcularCostoAlquiler(Alquiler alquiler) {
        Tarifa tarifa = alquiler.getTarifa();

        //empezamos el calculo

        // Calcula el costo del alquiler.
        double costo = tarifa.getMontoFijoAlquiler(); // Costo fijo por iniciar el alquiler

        // Calcula el costo de la fracción de minutos antes de la hora completa.
        int minutosFraccion = (int) Duration.between(alquiler.getFechaHoraRetiro(), alquiler.getFechaHoraDevolucion()).toMinutes();


        // Calcula el costo de las horas completas.
        int horasCompletas = minutosFraccion / 60;
        costo += tarifa.getMontoHora() * horasCompletas;

        int minutosResto = minutosFraccion % 60;

        if (minutosResto > 0 && minutosResto < 31) {
            costo += tarifa.getMontoMinutoFraccion() * minutosResto;
        }else {
            costo += tarifa.getMontoHora();
        }

        //calcular la distancia
        double distanciaKm = ubicacionService.calcularDistancia(alquiler.getEstacionRetiro().getLatitud(),
                alquiler.getEstacionRetiro().getLongitud(),
                alquiler.getEstacionDevolucion().getLatitud(),
                alquiler.getEstacionDevolucion().getLongitud());

        costo += tarifa.getMontoKm() * distanciaKm;

        return costo;
    }

    @Override
    public List<Alquiler> obtenerAlquileresPorCliente(String idCliente) {
        return alquilerRepository.findByIdCliente(idCliente);
    }
}
