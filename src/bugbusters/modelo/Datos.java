package bugbusters.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/*
 * Clase Datos
 *
 * Esta es la clase más importante del modelo en este producto.
 * Su función es guardar y gestionar todos los datos de la aplicación:
 * - clientes
 * - artículos
 * - pedidos
 *
 * En vez de usar listas para todo, aquí usamos Map porque:
 * - permite buscar más rápido por clave
 * - cliente -> por email
 * - artículo -> por código
 * - pedido -> por número de pedido
 *
 * LinkedHashMap mantiene el orden de inserción,
 * lo cual viene bien para mostrar datos en consola de forma ordenada.
 */
public class Datos {

    // Colección de clientes: clave = email
    private Map<String, Cliente> clientes;

    // Colección de artículos: clave = código
    private Map<String, Articulo> articulos;

    // Colección de pedidos: clave = número de pedido
    private Map<Integer, Pedido> pedidos;

    // Contador para generar automáticamente el número del siguiente pedido
    private int siguienteNumeroPedido;

    /*
     * Constructor
     * Inicializa todas las colecciones vacías.
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
     * Añade un cliente al mapa usando su email como clave.
     */
    public void anadirCliente(Cliente cliente) {
        clientes.put(cliente.getEmail().toLowerCase(), cliente);
    }

    /*
     * buscarCliente()
     * Busca un cliente por email.
     * Si no existe, devuelve null.
     */
    public Cliente buscarCliente(String email) {
        return clientes.get(email.toLowerCase());
    }

    /*
     * obtenerTodosClientes()
     * Devuelve una lista con todos los clientes.
     */
    public List<Cliente> obtenerTodosClientes() {
        return new ArrayList<>(clientes.values());
    }

    /*
     * obtenerClientesEstandar()
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
     * Añade un artículo al mapa usando su código como clave.
     */
    public void anadirArticulo(Articulo articulo) {
        articulos.put(articulo.getCodigo().toLowerCase(), articulo);
    }

    /*
     * buscarArticulo()
     * Busca un artículo por código.
     * Si no existe, devuelve null.
     */
    public Articulo buscarArticulo(String codigo) {
        return articulos.get(codigo.toLowerCase());
    }

    /*
     * obtenerTodosArticulos()
     * Devuelve todos los artículos en forma de lista.
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
     * Crea un pedido nuevo a partir de:
     * - email del cliente
     * - código del artículo
     * - cantidad
     *
     * Si el cliente o el artículo no existen, devuelve null.
     *
     * IMPORTANTE:
     * Más adelante esto lo mejoraremos con excepciones personalizadas,
     * porque el producto lo pide.
     */
    public Pedido crearPedido(String email, String codigoArticulo, int cantidad) {
        Cliente cliente = buscarCliente(email);
        Articulo articulo = buscarArticulo(codigoArticulo);

        // Si falta alguno de los dos datos, no se puede crear el pedido
        if (cliente == null || articulo == null) {
            return null;
        }

        // Se crea el pedido con número automático y fecha/hora actual
        Pedido pedido = new Pedido(
                siguienteNumeroPedido,
                cliente,
                articulo,
                cantidad,
                LocalDateTime.now()
        );

        // Se guarda en el mapa
        pedidos.put(siguienteNumeroPedido, pedido);

        // Se incrementa el contador para el siguiente pedido
        siguienteNumeroPedido++;

        return pedido;
    }

    /*
     * buscarPedido()
     * Busca un pedido por su número.
     */
    public Pedido buscarPedido(int numeroPedido) {
        return pedidos.get(numeroPedido);
    }

    /*
     * borrarPedido()
     *
     * Elimina un pedido solo si existe y se puede cancelar.
     * Devuelve:
     * - true si se ha borrado
     * - false si no se ha podido borrar
     */
    public boolean borrarPedido(int numeroPedido) {
        Pedido pedido = buscarPedido(numeroPedido);

        if (pedido == null) {
            return false;
        }

        if (!pedido.puedeCancelar()) {
            return false;
        }

        pedidos.remove(numeroPedido);
        return true;
    }

    /*
     * obtenerPedidosPendientes()
     * Devuelve los pedidos que todavía no han sido enviados.
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
     * Devuelve los pedidos ya enviados.
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