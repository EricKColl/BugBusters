package bugbusters.modelo;

/*
 * Esta excepción se lanza cuando se intenta añadir
 * un artículo con un código que ya existe.
 */
public class ArticuloYaExisteException extends Exception {

    public ArticuloYaExisteException(String mensaje) {
        super(mensaje);
    }
}