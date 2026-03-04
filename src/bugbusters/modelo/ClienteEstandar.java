package bugbusters.modelo;

/*
 * Clase ClienteEstandar
 *
 * Hereda de Cliente.
 * Esto significa que reutiliza los atributos y métodos
 * de la clase Cliente.
 */
public class ClienteEstandar extends Cliente {

    /*
     * Constructor
     * Llama al constructor de la clase padre (Cliente)
     * usando super(...).
     */
    public ClienteEstandar(String nombre, String domicilio, String nif, String email) {
        super(nombre, domicilio, nif, email);
    }

    /*
     * Un cliente estándar no tiene descuento en envío.
     */
    @Override
    public double descuentoEnvio() {
        return 0.0;
    }

    /*
     * toString()
     * Muestra el tipo de cliente y sus datos heredados.
     */
    @Override
    public String toString() {
        return "ClienteEstandar{" + super.toString() + "}";
    }
}