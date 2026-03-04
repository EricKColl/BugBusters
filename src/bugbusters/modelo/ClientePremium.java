package bugbusters.modelo;

/*
 * Clase ClientePremium
 *
 * Hereda de Cliente.
 * Además, añade un atributo propio:
 * cuotaAnual
 */
public class ClientePremium extends Cliente {

    // Cuota anual del cliente premium
    private double cuotaAnual = 30.0;

    /*
     * Constructor
     * Reutiliza el constructor de la clase Cliente.
     */
    public ClientePremium(String nombre, String domicilio, String nif, String email) {
        super(nombre, domicilio, nif, email);
    }

    // Getter de cuotaAnual
    public double getCuotaAnual() {
        return cuotaAnual;
    }

    // Setter de cuotaAnual
    public void setCuotaAnual(double cuotaAnual) {
        this.cuotaAnual = cuotaAnual;
    }

    /*
     * Un cliente premium tiene un 20% de descuento
     * en los gastos de envío.
     */
    @Override
    public double descuentoEnvio() {
        return 0.20;
    }

    /*
     * toString()
     * Muestra la cuota anual y los datos heredados.
     */
    @Override
    public String toString() {
        return "ClientePremium{cuotaAnual=" + cuotaAnual + ", " + super.toString() + "}";
    }
}