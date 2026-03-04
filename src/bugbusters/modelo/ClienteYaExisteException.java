package bugbusters.modelo;

/*
 * Esta excepción se lanza cuando se intenta añadir
 * un cliente con un email que ya existe.
 */
public class ClienteYaExisteException extends Exception {

    public ClienteYaExisteException(String mensaje) {
        super(mensaje);
    }
}