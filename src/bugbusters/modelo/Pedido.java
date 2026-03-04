package bugbusters.modelo;

import java.time.Duration;
import java.time.LocalDateTime;

/*
 * Clase Pedido
 *
 * Representa un pedido realizado por un cliente.
 * Cada pedido tiene:
 * - un número de pedido
 * - un cliente
 * - un artículo
 * - una cantidad
 * - una fecha y hora
 */
public class Pedido {

    private int numeroPedido;
    private Cliente cliente;
    private Articulo articulo;
    private int cantidad;
    private LocalDateTime fechaHora;

    /*
     * Constructor
     * Crea un pedido con todos sus datos.
     */
    public Pedido(int numeroPedido, Cliente cliente, Articulo articulo, int cantidad, LocalDateTime fechaHora) {
        this.numeroPedido = numeroPedido;
        this.cliente = cliente;
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.fechaHora = fechaHora;
    }

    // Getter y Setter de numeroPedido
    public int getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    // Getter y Setter de cliente
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    // Getter y Setter de articulo
    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    // Getter y Setter de cantidad
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    // Getter y Setter de fechaHora
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    /*
     * calcularTotal()
     *
     * Calcula el precio total del pedido.
     * Fórmula:
     * - subtotal = precio del artículo * cantidad
     * - envío final = gastos de envío aplicando descuento si el cliente es premium
     * - total = subtotal + envío final
     */
    public double calcularTotal() {
        double subtotal = articulo.getPrecioVenta() * cantidad;
        double envioBase = articulo.getGastosEnvio();
        double envioFinal = envioBase * (1 - cliente.descuentoEnvio());

        return subtotal + envioFinal;
    }

    /*
     * puedeCancelar()
     *
     * Un pedido se puede cancelar solo si todavía no ha pasado
     * el tiempo de preparación del artículo.
     */
    public boolean puedeCancelar() {
        long minutosTranscurridos = Duration.between(fechaHora, LocalDateTime.now()).toMinutes();
        return minutosTranscurridos < articulo.getTiempoPreparacion();
    }

    /*
     * estaEnviado()
     *
     * Si ya NO se puede cancelar, lo consideramos enviado.
     */
    public boolean estaEnviado() {
        return !puedeCancelar();
    }

    /*
     * toString()
     * Muestra la información principal del pedido.
     */
    @Override
    public String toString() {
        return "Pedido{" +
                "numeroPedido=" + numeroPedido +
                ", cliente=" + cliente.getEmail() +
                ", articulo=" + articulo.getCodigo() +
                ", cantidad=" + cantidad +
                ", fechaHora=" + fechaHora +
                ", total=" + calcularTotal() +
                ", enviado=" + estaEnviado() +
                '}';
    }
}