package bugbusters.modelo;

/*
 * Esta excepción se lanza cuando se intenta borrar
 * un pedido que ya no se puede cancelar.
 */
public class PedidoNoCancelableException extends Exception {

    public PedidoNoCancelableException(String mensaje) {
        super(mensaje);
    }
}