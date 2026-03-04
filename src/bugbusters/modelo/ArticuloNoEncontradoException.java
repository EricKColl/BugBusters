package bugbusters.modelo;

/*
 * Esta excepción se lanza cuando no se encuentra
 * un artículo buscado por código.
 */
public class ArticuloNoEncontradoException extends Exception {

    public ArticuloNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}