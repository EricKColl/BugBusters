package bugbusters.controlador;

import bugbusters.modelo.Articulo;
import bugbusters.modelo.ArticuloNoEncontradoException;
import bugbusters.modelo.ArticuloYaExisteException;
import bugbusters.modelo.Cliente;
import bugbusters.modelo.ClienteEstandar;
import bugbusters.modelo.ClienteNoEncontradoException;
import bugbusters.modelo.ClientePremium;
import bugbusters.modelo.ClienteYaExisteException;
import bugbusters.modelo.Datos;
import bugbusters.modelo.Pedido;
import bugbusters.modelo.PedidoNoCancelableException;
import bugbusters.modelo.PedidoNoEncontradoException;

import java.util.List;

/*
 * Clase Controlador
 *
 * Esta clase hace de puente entre la Vista y el Modelo.
 * La Vista solo debe usar esta clase para acceder a la información.
 */
public class Controlador {

    // Objeto principal del modelo
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
     * Crea un cliente estándar y lo añade al modelo.
     * Si ya existe, lanza excepción.
     */
    public void anadirClienteEstandar(String nombre, String domicilio, String nif, String email)
            throws ClienteYaExisteException {
        Cliente cliente = new ClienteEstandar(nombre, domicilio, nif, email);
        datos.anadirCliente(cliente);
    }

    /*
     * anadirClientePremium()
     * Crea un cliente premium y lo añade al modelo.
     * Si ya existe, lanza excepción.
     */
    public void anadirClientePremium(String nombre, String domicilio, String nif, String email)
            throws ClienteYaExisteException {
        Cliente cliente = new ClientePremium(nombre, domicilio, nif, email);
        datos.anadirCliente(cliente);
    }

    /*
     * buscarCliente()
     * Busca un cliente por email.
     * Si no existe, lanza excepción.
     */
    public Cliente buscarCliente(String email) throws ClienteNoEncontradoException {
        return datos.buscarCliente(email);
    }

    /*
     * existeCliente()
     * Devuelve true si el cliente existe y false si no.
     */
    public boolean existeCliente(String email) {
        return datos.existeCliente(email);
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
     * Crea un artículo y lo añade al modelo.
     * Si ya existe, lanza excepción.
     */
    public void anadirArticulo(String codigo, String descripcion, double precioVenta,
                               double gastosEnvio, int tiempoPreparacion)
            throws ArticuloYaExisteException {
        Articulo articulo = new Articulo(codigo, descripcion, precioVenta, gastosEnvio, tiempoPreparacion);
        datos.anadirArticulo(articulo);
    }

    /*
     * buscarArticulo()
     * Busca un artículo por código.
     * Si no existe, lanza excepción.
     */
    public Articulo buscarArticulo(String codigo) throws ArticuloNoEncontradoException {
        return datos.buscarArticulo(codigo);
    }

    /*
     * existeArticulo()
     * Devuelve true si el artículo existe y false si no.
     */
    public boolean existeArticulo(String codigo) {
        return datos.existeArticulo(codigo);
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
     * Pide al modelo que cree un pedido.
     * Puede lanzar excepción si no existe el cliente o el artículo.
     */
    public Pedido crearPedido(String email, String codigoArticulo, int cantidad)
            throws ClienteNoEncontradoException, ArticuloNoEncontradoException {
        return datos.crearPedido(email, codigoArticulo, cantidad);
    }

    /*
     * buscarPedido()
     * Busca un pedido por número.
     * Si no existe, lanza excepción.
     */
    public Pedido buscarPedido(int numeroPedido) throws PedidoNoEncontradoException {
        return datos.buscarPedido(numeroPedido);
    }

    /*
     * borrarPedido()
     * Intenta borrar un pedido.
     * Puede lanzar excepción si no existe o no se puede cancelar.
     */
    public void borrarPedido(int numeroPedido)
            throws PedidoNoEncontradoException, PedidoNoCancelableException {
        datos.borrarPedido(numeroPedido);
    }

    /*
     * obtenerPedidosPendientes()
     * Devuelve todos los pedidos pendientes.
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