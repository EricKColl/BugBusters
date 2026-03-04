package bugbusters.test;

import bugbusters.modelo.Articulo;
import bugbusters.modelo.Cliente;
import bugbusters.modelo.ClienteEstandar;
import bugbusters.modelo.ClientePremium;
import bugbusters.modelo.Pedido;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Clase de pruebas unitarias para Pedido
 *
 * Aquí comprobamos dos métodos con lógica de negocio:
 * - calcularTotal()
 * - puedeCancelar()
 */
public class PedidoTest {

    /*
     * Prueba 1:
     * Cliente estándar -> no tiene descuento en envío
     *
     * Cálculo esperado:
     * - precio = 10
     * - cantidad = 2
     * - subtotal = 20
     * - envío = 5
     * - total = 25
     */
    @Test
    public void testCalcularTotalClienteEstandar() {
        Cliente cliente = new ClienteEstandar("Ana", "Calle A", "12345678A", "ana@email.com");
        Articulo articulo = new Articulo("A1", "Teclado", 10.0, 5.0, 60);
        Pedido pedido = new Pedido(1, cliente, articulo, 2, LocalDateTime.now());

        double totalEsperado = 25.0;
        double totalReal = pedido.calcularTotal();

        assertEquals(totalEsperado, totalReal, 0.001);
    }

    /*
     * Prueba 2:
     * Cliente premium -> tiene 20% de descuento en envío
     *
     * Cálculo esperado:
     * - precio = 10
     * - cantidad = 2
     * - subtotal = 20
     * - envío base = 5
     * - envío final = 5 * 0.8 = 4
     * - total = 24
     */
    @Test
    public void testCalcularTotalClientePremium() {
        Cliente cliente = new ClientePremium("Luis", "Calle B", "87654321B", "luis@email.com");
        Articulo articulo = new Articulo("A2", "Ratón", 10.0, 5.0, 60);
        Pedido pedido = new Pedido(2, cliente, articulo, 2, LocalDateTime.now());

        double totalEsperado = 24.0;
        double totalReal = pedido.calcularTotal();

        assertEquals(totalEsperado, totalReal, 0.001);
    }

    /*
     * Prueba 3:
     * Si el pedido se acaba de crear, todavía se puede cancelar.
     */
    @Test
    public void testPuedeCancelarTrue() {
        Cliente cliente = new ClienteEstandar("Mario", "Calle C", "11111111C", "mario@email.com");
        Articulo articulo = new Articulo("A3", "Pantalla", 100.0, 10.0, 60);
        Pedido pedido = new Pedido(3, cliente, articulo, 1, LocalDateTime.now());

        assertTrue(pedido.puedeCancelar());
    }

    /*
     * Prueba 4:
     * Si ya ha pasado bastante tiempo desde que se hizo el pedido,
     * ya no se puede cancelar.
     */
    @Test
    public void testPuedeCancelarFalse() {
        Cliente cliente = new ClienteEstandar("Laura", "Calle D", "22222222D", "laura@email.com");
        Articulo articulo = new Articulo("A4", "Portátil", 500.0, 15.0, 60);
        Pedido pedido = new Pedido(4, cliente, articulo, 1, LocalDateTime.now().minusHours(2));

        assertFalse(pedido.puedeCancelar());
    }
}