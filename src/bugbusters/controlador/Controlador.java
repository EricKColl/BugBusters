package bugbusters.controlador;

import bugbusters.modelo.Articulo;
import bugbusters.modelo.Cliente;
import bugbusters.modelo.ClienteEstandar;
import bugbusters.modelo.ClientePremium;
import bugbusters.modelo.Datos;
import bugbusters.modelo.Pedido;

import java.util.List;

/*
 * Clase Controlador
 *
 * Esta clase hace de puente entre la Vista y el Modelo.
 *
 * La Vista NO debe acceder directamente a Datos ni a las demás clases del modelo.
 * Toda la comunicación debe pasar por aquí.
 */
public class Controlador {

    // Referencia al modelo principal de la aplicación
    private Datos datos;

    /*
     * Constructor
     * Crea el objeto Datos que almacenará toda la información.
     */
    public Controlador() {
        datos = new Datos();
    }

    /* =========================================================
       =============== MÉTODOS DE GESTIÓN DE CLIENTES ==========
       ========================================================= */

    /*
     * anadirClienteEstandar()
     * Crea un cliente estándar y lo guarda en Datos.
     */
    public void anadirClienteEstandar(String nombre, String domicilio, String nif, String email) {
        Cliente cliente = new ClienteEstandar(nombre, domicilio, nif, email);
        datos.anadirCliente(cliente);
    }

    /*
     * anadirClientePremium()
     * Crea un cliente premium y lo guarda en Datos.
     */
    public void anadirClientePremium(String nombre, String domicilio, String nif, String email) {
        Cliente cliente = new ClientePremium(nombre, domicilio, nif, email);
        datos.anadirCliente(cliente);
    }

    /*
     * buscarCliente()
     * Devuelve un cliente a partir de su email.
     */
    public Cliente buscarCliente(String email) {
        return datos.buscarCliente(email);
    }

    /*
     * obtenerTodosClientes()
     * Devuelve todos los clientes.
     */
    public List<Cliente> obtenerTodosClientes() {
        return datos.obtenerTodosClientes();
    }

    /*
     * obtenerClientesEstandar()
     * Devuelve solo los clientes estándar.
     */
    public List<Cliente> obtenerClientesEstandar() {
        return datos.obtenerClientesEstandar();
    }

    /*
     * obtenerClientesPremium()
     * Devuelve solo los clientes premium.
     */
    public List<Cliente> obtenerClientesPremium() {
        return datos.obtenerClientesPremium();
    }

    /* =========================================================
       =============== MÉTODOS DE GESTIÓN DE ARTÍCULOS =========
       ========================================================= */

    /*
     * anadirArticulo()
     * Crea un artículo y lo guarda en Datos.
     */
    public void anadirArticulo(String codigo, String descripcion, double precioVenta, double gastosEnvio, int tiempoPreparacion) {
        Articulo articulo = new Articulo(codigo, descripcion, precioVenta, gastosEnvio, tiempoPreparacion);
        datos.anadirArticulo(articulo);
    }

    /*
     * buscarArticulo()
     * Busca un artículo por su código.
     */
    public Articulo buscarArticulo(String codigo) {
        return datos.buscarArticulo(codigo);
    }

    /*
     * obtenerTodosArticulos()
     * Devuelve todos los artículos.
     */
    public List<Articulo> obtenerTodosArticulos() {
        return datos.obtenerTodosArticulos();
    }

    /* =========================================================
       =============== MÉTODOS DE GESTIÓN DE PEDIDOS ===========
       ========================================================= */

    /*
     * crearPedido()
     * Pide a Datos que cree un pedido nuevo.
     *
     * Devuelve el pedido creado, o null si no ha podido crearse.
     */
    public Pedido crearPedido(String email, String codigoArticulo, int cantidad) {
        return datos.crearPedido(email, codigoArticulo, cantidad);
    }

    /*
     * buscarPedido()
     * Busca un pedido por número.
     */
    public Pedido buscarPedido(int numeroPedido) {
        return datos.buscarPedido(numeroPedido);
    }

    /*
     * borrarPedido()
     * Intenta borrar un pedido.
     * Devuelve true si se ha borrado y false si no.
     */
    public boolean borrarPedido(int numeroPedido) {
        return datos.borrarPedido(numeroPedido);
    }

    /*
     * obtenerPedidosPendientes()
     * Devuelve todos los pedidos pendientes de envío.
     */
    public List<Pedido> obtenerPedidosPendientes() {
        return datos.obtenerPedidosPendientes();
    }

    /*
     * obtenerPedidosEnviados()
     * Devuelve todos los pedidos enviados.
     */
    public List<Pedido> obtenerPedidosEnviados() {
        return datos.obtenerPedidosEnviados();
    }

    /*
     * obtenerPedidosPendientesCliente()
     * Devuelve los pedidos pendientes de un cliente concreto.
     */
    public List<Pedido> obtenerPedidosPendientesCliente(String email) {
        return datos.obtenerPedidosPendientesCliente(email);
    }

    /*
     * obtenerPedidosEnviadosCliente()
     * Devuelve los pedidos enviados de un cliente concreto.
     */
    public List<Pedido> obtenerPedidosEnviadosCliente(String email) {
        return datos.obtenerPedidosEnviadosCliente(email);
    }
}