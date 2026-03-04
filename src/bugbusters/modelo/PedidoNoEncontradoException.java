package bugbusters.modelo;

/*
 * Esta excepción se lanza cuando no se encuentra
 * un pedido buscado por su número.
 */
public class PedidoNoEncontradoException extends Exception {

    public PedidoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}