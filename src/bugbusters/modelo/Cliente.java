package bugbusters.modelo;

/*
 * Clase abstracta Cliente
 *
 * ¿Qué significa que sea abstracta?
 * Significa que esta clase sirve como base para otras,
 * pero no se crearán objetos directamente de tipo Cliente.
 *
 * En nuestro caso, los tipos reales de cliente serán:
 * - ClienteEstandar
 * - ClientePremium
 */
public abstract class Cliente {

    // Atributos comunes a cualquier cliente
    private String nombre;
    private String domicilio;
    private String nif;
    private String email;

    /*
     * Constructor
     * Sirve para crear un cliente con todos sus datos básicos.
     */
    public Cliente(String nombre, String domicilio, String nif, String email) {
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.nif = nif;
        this.email = email;
    }

    /*
     * Método abstracto
     *
     * Cada tipo de cliente deberá indicar su descuento de envío:
     * - Cliente estándar -> 0.0
     * - Cliente premium -> 0.20
     */
    public abstract double descuentoEnvio();

    // Getter y Setter de nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter y Setter de domicilio
    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    // Getter y Setter de nif
    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    // Getter y Setter de email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /*
     * toString()
     * Sirve para mostrar el objeto en forma de texto.
     * Muy útil para imprimirlo por pantalla.
     */
    @Override
    public String toString() {
        return "Cliente{" +
                "nombre='" + nombre + '\'' +
                ", domicilio='" + domicilio + '\'' +
                ", nif='" + nif + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}