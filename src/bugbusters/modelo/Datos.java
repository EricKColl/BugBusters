package bugbusters.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/*
 * Clase Datos
 *
 * Esta clase guarda y gestiona toda la información de la aplicación.
 * Aquí almacenamos:
 * - clientes
 * - artículos
 * - pedidos
 *
 * Usamos colecciones genéricas:
 * - Map para buscar rápido por clave
 * - List para devolver listados al exterior
 *
 * Claves usadas:
 * - cliente -> email
 * - artículo -> código
 * - pedido -> número de pedido
 */
public class Datos {

    // Mapa de clientes: clave = email
    private Map<String, Cliente> clientes;

    // Mapa de artículos: clave = código
    private Map<String, Articulo> articulos;

    // Mapa de pedidos: clave = número de pedido
    private Map<Integer, Pedido> pedidos;

    // Contador para asignar el siguiente número de pedido automáticamente
    private int siguienteNumeroPedido;

    /*
     * Constructor
     * Inicializa las colecciones vacías.
     */
    public Datos() {
        clientes = new LinkedHashMap<>();
        articulos = new LinkedHashMap<>();
        pedidos = new LinkedHashMap<>();
        siguienteNumeroPedido = 1;
    }

    /* =========================================================
       =============== MÉTODOS DE GESTIÓN DE CLIENTES ==========
       ========================================================= */

    /*
     * anadirCliente()
     *
     * Añade un cliente nuevo.
     * Si ya existe otro cliente con el mismo email, lanza excepción.
     */
    public void anadirCliente(Cliente cliente) throws ClienteYaExisteException {
        String emailClave = cliente.getEmail().toLowerCase();

        if (clientes.containsKey(emailClave)) {
            throw new ClienteYaExisteException("Ya existe un cliente con ese email.");
        }

        clientes.put(emailClave, cliente);
    }

    /*
     * buscarCliente()
     *
     * Busca un cliente por email.
     * Si no existe, lanza excepción.
     */
    public Cliente buscarCliente(String email) throws ClienteNoEncontradoException {
        String emailClave = email.toLowerCase();

        if (!clientes.containsKey(emailClave)) {
            throw new ClienteNoEncontradoException("No existe ningún cliente con ese email.");
        }

        return clientes.get(emailClave);
    }

    /*
     * existeCliente()
     *
     * Devuelve true si el cliente existe y false si no existe.
     * Este método es útil para algunas comprobaciones desde la vista/controlador.
     */
    public boolean existeCliente(String email) {
        return clientes.containsKey(email.toLowerCase());
    }

    /*
     * obtenerTodosClientes()
     *
     * Devuelve una lista con todos los clientes.
     */
    public List<Cliente> obtenerTodosClientes() {
        return new ArrayList<>(clientes.values());
    }

    /*
     * obtenerClientesEstandar()
     *
     * Devuelve solo los clientes estándar.
     */
    public List<Cliente> obtenerClientesEstandar() {
        List<Cliente> resultado = new ArrayList<>();

        for (Cliente cliente : clientes.values()) {
            if (cliente instanceof ClienteEstandar) {
                resultado.add(cliente);
            }
        }

        return resultado;
    }

    /*
     * obtenerClientesPremium()
     *
     * Devuelve solo los clientes premium.
     */
    public List<Cliente> obtenerClientesPremium() {
        List<Cliente> resultado = new ArrayList<>();

        for (Cliente cliente : clientes.values()) {
            if (cliente instanceof ClientePremium) {
                resultado.add(cliente);
            }
        }

        return resultado;
    }

    /* =========================================================
       =============== MÉTODOS DE GESTIÓN DE ARTÍCULOS =========
       ========================================================= */

    /*
     * anadirArticulo()
     *
     * Añade un artículo nuevo.
     * Si ya existe otro artículo con el mismo código, lanza excepción.
     */
    public void anadirArticulo(Articulo articulo) throws ArticuloYaExisteException {
        String codigoClave = articulo.getCodigo().toLowerCase();

        if (articulos.containsKey(codigoClave)) {
            throw new ArticuloYaExisteException("Ya existe un artículo con ese código.");
        }

        articulos.put(codigoClave, articulo);
    }

    /*
     * buscarArticulo()
     *
     * Busca un artículo por código.
     * Si no existe, lanza excepción.
     */
    public Articulo buscarArticulo(String codigo) throws ArticuloNoEncontradoException {
        String codigoClave = codigo.toLowerCase();

        if (!articulos.containsKey(codigoClave)) {
            throw new ArticuloNoEncontradoException("No existe ningún artículo con ese código.");
        }

        return articulos.get(codigoClave);
    }

    /*
     * existeArticulo()
     *
     * Devuelve true si el artículo existe y false si no existe.
     */
    public boolean existeArticulo(String codigo) {
        return articulos.containsKey(codigo.toLowerCase());
    }

    /*
     * obtenerTodosArticulos()
     *
     * Devuelve una lista con todos los artículos.
     */
    public List<Articulo> obtenerTodosArticulos() {
        return new ArrayList<>(articulos.values());
    }

    /* =========================================================
       =============== MÉTODOS DE GESTIÓN DE PEDIDOS ===========
       ========================================================= */

    /*
     * crearPedido()
     *
     * Crea un pedido nuevo.
     * Para ello:
     * - el cliente debe existir
     * - el artículo debe existir
     *
     * Si alguno no existe, se lanzará la excepción correspondiente.
     */
    public Pedido crearPedido(String email, String codigoArticulo, int cantidad)
            throws ClienteNoEncontradoException, ArticuloNoEncontradoException {

        Cliente cliente = buscarCliente(email);
        Articulo articulo = buscarArticulo(codigoArticulo);

        Pedido pedido = new Pedido(
                siguienteNumeroPedido,
                cliente,
                articulo,
                cantidad,
                LocalDateTime.now()
        );

        pedidos.put(siguienteNumeroPedido, pedido);
        siguienteNumeroPedido++;

        return pedido;
    }

    /*
     * buscarPedido()
     *
     * Busca un pedido por su número.
     * Si no existe, lanza excepción.
     */
    public Pedido buscarPedido(int numeroPedido) throws PedidoNoEncontradoException {
        if (!pedidos.containsKey(numeroPedido)) {
            throw new PedidoNoEncontradoException("No existe ningún pedido con ese número.");
        }

        return pedidos.get(numeroPedido);
    }

    /*
     * borrarPedido()
     *
     * Elimina un pedido solo si:
     * - existe
     * - todavía se puede cancelar
     *
     * Si no existe o no se puede cancelar, lanza excepción.
     */
    public void borrarPedido(int numeroPedido)
            throws PedidoNoEncontradoException, PedidoNoCancelableException {

        Pedido pedido = buscarPedido(numeroPedido);

        if (!pedido.puedeCancelar()) {
            throw new PedidoNoCancelableException("El pedido ya no se puede cancelar porque ya ha sido enviado.");
        }

        pedidos.remove(numeroPedido);
    }

    /*
     * obtenerPedidosPendientes()
     *
     * Devuelve todos los pedidos que todavía no han sido enviados.
     */
    public List<Pedido> obtenerPedidosPendientes() {
        List<Pedido> resultado = new ArrayList<>();

        for (Pedido pedido : pedidos.values()) {
            if (!pedido.estaEnviado()) {
                resultado.add(pedido);
            }
        }

        return resultado;
    }

    /*
     * obtenerPedidosEnviados()
     *
     * Devuelve todos los pedidos que ya han sido enviados.
     */
    public List<Pedido> obtenerPedidosEnviados() {
        List<Pedido> resultado = new ArrayList<>();

        for (Pedido pedido : pedidos.values()) {
            if (pedido.estaEnviado()) {
                resultado.add(pedido);
            }
        }

        return resultado;
    }

    /*
     * obtenerPedidosPendientesCliente()
     *
     * Devuelve los pedidos pendientes de un cliente concreto.
     */
    public List<Pedido> obtenerPedidosPendientesCliente(String email) {
        List<Pedido> resultado = new ArrayList<>();

        for (Pedido pedido : pedidos.values()) {
            if (!pedido.estaEnviado()
                    && pedido.getCliente().getEmail().equalsIgnoreCase(email)) {
                resultado.add(pedido);
            }
        }

        return resultado;
    }

    /*
     * obtenerPedidosEnviadosCliente()
     *
     * Devuelve los pedidos enviados de un cliente concreto.
     */
    public List<Pedido> obtenerPedidosEnviadosCliente(String email) {
        List<Pedido> resultado = new ArrayList<>();

        for (Pedido pedido : pedidos.values()) {
            if (pedido.estaEnviado()
                    && pedido.getCliente().getEmail().equalsIgnoreCase(email)) {
                resultado.add(pedido);
            }
        }

        return resultado;
    }
}