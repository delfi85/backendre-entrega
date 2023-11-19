package backendDeAplicaciones.bicicletas.response;

public class MyResponseClass {
    private String moneda;
    private Double importe;

    // Constructor por defecto
    public MyResponseClass() {
    }

    // Constructor con todos los argumentos
    public MyResponseClass(String moneda, Double importe) {
        this.moneda = moneda;
        this.importe = importe;
    }

    // Getters y setters
    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    // Método toString para la representación en forma de texto de la clase
    @Override
    public String toString() {
        return "MyResponseClass{" +
                "moneda='" + moneda + '\'' +
                ", importe=" + importe +
                '}';
    }
}
