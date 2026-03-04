package bugbusters.vista;

import bugbusters.controlador.Controlador;
import bugbusters.modelo.Articulo;
import bugbusters.modelo.ArticuloNoEncontradoException;
import bugbusters.modelo.ArticuloYaExisteException;
import bugbusters.modelo.Cliente;
import bugbusters.modelo.ClienteNoEncontradoException;
import bugbusters.modelo.ClienteYaExisteException;
import bugbusters.modelo.Pedido;
import bugbusters.modelo.PedidoNoCancelableException;
import bugbusters.modelo.PedidoNoEncontradoException;

import java.util.List;
import java.util.Scanner;

/*
 * Clase Vista
 *
 * Esta clase se encarga de toda la interacción con el usuario:
 * - mostrar menús
 * - pedir datos por teclado
 * - mostrar resultados por pantalla
 *
 * Importante (MVC):
 * - La vista NO accede al modelo directamente.
 * - La vista solo usa el controlador.
 */
public class Vista {

    private Scanner teclado;
    private Controlador controlador;

    /*
     * Constructor:
     * - Crea el Scanner para leer por teclado
     * - Crea el Controlador que conecta con el modelo
     */
    public Vista() {
        teclado = new Scanner(System.in);
        controlador = new Controlador();
    }

    /*
     * iniciar():
     * Muestra el menú principal en un bucle hasta que el usuario sale.
     */
    public void iniciar() {
        int opcion;

        do {
            mostrarMenuPrincipal();
            opcion = leerEnteroSeguro("Selecciona una opción: ");

            switch (opcion) {
                case 1:
                    menuArticulos();
                    break;
                case 2:
                    menuClientes();
                    break;
                case 3:
                    menuPedidos();
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);
    }

    /* =========================================================
       =================== MENÚ PRINCIPAL ======================
       ========================================================= */

    private void mostrarMenuPrincipal() {
        System.out.println("\n====================================");
        System.out.println("         MENÚ PRINCIPAL");
        System.out.println("====================================");
        System.out.println("1. Gestión de artículos");
        System.out.println("2. Gestión de clientes");
        System.out.println("3. Gestión de pedidos");
        System.out.println("0. Salir");
        System.out.println("====================================");
    }

    /* =========================================================
       ================= MENÚ DE ARTÍCULOS =====================
       ========================================================= */

    private void menuArticulos() {
        int opcion;

        do {
            System.out.println("\n--- GESTIÓN DE ARTÍCULOS ---");
            System.out.println("1. Añadir artículo");
            System.out.println("2. Mostrar artículos");
            System.out.println("0. Volver");
            opcion = leerEnteroSeguro("Selecciona una opción: ");

            switch (opcion) {
                case 1:
                    anadirArticulo();
                    break;
                case 2:
                    mostrarArticulos();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);
    }

    private void anadirArticulo() {
        System.out.println("\nAñadir artículo");

        String codigo = leerTexto("Código: ");
        String descripcion = leerTexto("Descripción: ");
        double precioVenta = leerDoubleSeguro("Precio de venta: ");
        double gastosEnvio = leerDoubleSeguro("Gastos de envío: ");
        int tiempoPreparacion = leerEnteroSeguro("Tiempo de preparación: ");

        try {
            controlador.anadirArticulo(codigo, descripcion, precioVenta, gastosEnvio, tiempoPreparacion);
            System.out.println("Artículo añadido correctamente.");
        } catch (ArticuloYaExisteException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private void mostrarArticulos() {
        System.out.println("\nListado de artículos:");

        List<Articulo> articulos = controlador.obtenerTodosArticulos();

        if (articulos.isEmpty()) {
            System.out.println("No hay artículos registrados.");
        } else {
            for (Articulo articulo : articulos) {
                System.out.println(articulo);
            }
        }
    }

    /* =========================================================
       ================= MENÚ DE CLIENTES ======================
       ========================================================= */

    private void menuClientes() {
        int opcion;

        do {
            System.out.println("\n--- GESTIÓN DE CLIENTES ---");
            System.out.println("1. Añadir cliente");
            System.out.println("2. Mostrar clientes");
            System.out.println("3. Mostrar clientes estándar");
            System.out.println("4. Mostrar clientes premium");
            System.out.println("0. Volver");
            opcion = leerEnteroSeguro("Selecciona una opción: ");

            switch (opcion) {
                case 1:
                    anadirCliente();
                    break;
                case 2:
                    mostrarClientes();
                    break;
                case 3:
                    mostrarClientesEstandar();
                    break;
                case 4:
                    mostrarClientesPremium();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);
    }

    private void anadirCliente() {
        System.out.println("\nAñadir cliente");

        String nombre = leerTexto("Nombre: ");
        String domicilio = leerTexto("Domicilio: ");
        String nif = leerTexto("NIF: ");
        String email = leerTexto("Email: ");

        System.out.println("Tipo de cliente:");
        System.out.println("1. Estándar");
        System.out.println("2. Premium");
        int tipo = leerEnteroSeguro("Selecciona una opción: ");

        try {
            if (tipo == 1) {
                controlador.anadirClienteEstandar(nombre, domicilio, nif, email);
                System.out.println("Cliente estándar añadido correctamente.");
            } else if (tipo == 2) {
                controlador.anadirClientePremium(nombre, domicilio, nif, email);
                System.out.println("Cliente premium añadido correctamente.");
            } else {
                System.out.println("Tipo de cliente no válido.");
            }
        } catch (ClienteYaExisteException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private void mostrarClientes() {
        System.out.println("\nListado de clientes:");

        List<Cliente> clientes = controlador.obtenerTodosClientes();

        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
        } else {
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
        }
    }

    private void mostrarClientesEstandar() {
        System.out.println("\nListado de clientes estándar:");

        List<Cliente> clientes = controlador.obtenerClientesEstandar();

        if (clientes.isEmpty()) {
            System.out.println("No hay clientes estándar registrados.");
        } else {
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
        }
    }

    private void mostrarClientesPremium() {
        System.out.println("\nListado de clientes premium:");

        List<Cliente> clientes = controlador.obtenerClientesPremium();

        if (clientes.isEmpty()) {
            System.out.println("No hay clientes premium registrados.");
        } else {
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
        }
    }

    /* =========================================================
       ================== MENÚ DE PEDIDOS ======================
       ========================================================= */

    private void menuPedidos() {
        int opcion;

        do {
            System.out.println("\n--- GESTIÓN DE PEDIDOS ---");
            System.out.println("1. Añadir pedido");
            System.out.println("2. Eliminar pedido");
            System.out.println("3. Mostrar pedidos pendientes");
            System.out.println("4. Mostrar pedidos enviados");
            System.out.println("0. Volver");
            opcion = leerEnteroSeguro("Selecciona una opción: ");

            switch (opcion) {
                case 1:
                    anadirPedido();
                    break;
                case 2:
                    eliminarPedido();
                    break;
                case 3:
                    mostrarPedidosPendientes();
                    break;
                case 4:
                    mostrarPedidosEnviados();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);
    }

    private void anadirPedido() {
        System.out.println("\nAñadir pedido");

        String email = leerTexto("Email del cliente: ");

        /*
         * Si el cliente NO existe, el enunciado dice que debemos crearlo.
         * Para evitar excepciones, lo comprobamos con existeCliente().
         */
        if (!controlador.existeCliente(email)) {
            System.out.println("El cliente no existe. Vamos a crearlo.");

            String nombre = leerTexto("Nombre: ");
            String domicilio = leerTexto("Domicilio: ");
            String nif = leerTexto("NIF: ");

            System.out.println("Tipo de cliente:");
            System.out.println("1. Estándar");
            System.out.println("2. Premium");
            int tipo = leerEnteroSeguro("Selecciona una opción: ");

            try {
                if (tipo == 1) {
                    controlador.anadirClienteEstandar(nombre, domicilio, nif, email);
                } else if (tipo == 2) {
                    controlador.anadirClientePremium(nombre, domicilio, nif, email);
                } else {
                    System.out.println("Tipo de cliente no válido. Pedido cancelado.");
                    return;
                }
            } catch (ClienteYaExisteException e) {
                // Esto en teoría no debería pasar porque hemos comprobado antes, pero lo controlamos igualmente
                System.out.println("ERROR: " + e.getMessage());
                return;
            }
        }

        String codigoArticulo = leerTexto("Código del artículo: ");
        int cantidad = leerEnteroSeguro("Cantidad: ");

        try {
            Pedido pedido = controlador.crearPedido(email, codigoArticulo, cantidad);
            System.out.println("Pedido creado correctamente:");
            System.out.println(pedido);
        } catch (ClienteNoEncontradoException | ArticuloNoEncontradoException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private void eliminarPedido() {
        System.out.println("\nEliminar pedido");

        int numeroPedido = leerEnteroSeguro("Número de pedido: ");

        try {
            controlador.borrarPedido(numeroPedido);
            System.out.println("Pedido eliminado correctamente.");
        } catch (PedidoNoEncontradoException | PedidoNoCancelableException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private void mostrarPedidosPendientes() {
        System.out.println("\nMostrar pedidos pendientes");
        System.out.println("1. Mostrar todos");
        System.out.println("2. Filtrar por cliente");
        int opcion = leerEnteroSeguro("Selecciona una opción: ");

        List<Pedido> pedidos;

        if (opcion == 1) {
            pedidos = controlador.obtenerPedidosPendientes();
        } else if (opcion == 2) {
            String email = leerTexto("Email del cliente: ");
            pedidos = controlador.obtenerPedidosPendientesCliente(email);
        } else {
            System.out.println("Opción no válida.");
            return;
        }

        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos pendientes.");
        } else {
            for (Pedido pedido : pedidos) {
                System.out.println(pedido);
            }
        }
    }

    private void mostrarPedidosEnviados() {
        System.out.println("\nMostrar pedidos enviados");
        System.out.println("1. Mostrar todos");
        System.out.println("2. Filtrar por cliente");
        int opcion = leerEnteroSeguro("Selecciona una opción: ");

        List<Pedido> pedidos;

        if (opcion == 1) {
            pedidos = controlador.obtenerPedidosEnviados();
        } else if (opcion == 2) {
            String email = leerTexto("Email del cliente: ");
            pedidos = controlador.obtenerPedidosEnviadosCliente(email);
        } else {
            System.out.println("Opción no válida.");
            return;
        }

        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos enviados.");
        } else {
            for (Pedido pedido : pedidos) {
                System.out.println(pedido);
            }
        }
    }

    /* =========================================================
       ================== MÉTODOS AUXILIARES ===================
       ========================================================= */

    /*
     * leerTexto()
     * Lee texto por teclado.
     */
    private String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return teclado.nextLine();
    }

    /*
     * leerEnteroSeguro()
     * Lee un entero pero sin romper el programa si el usuario escribe mal.
     */
    private int leerEnteroSeguro(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = teclado.nextLine();

            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Debes introducir un número entero válido.");
            }
        }
    }

    /*
     * leerDoubleSeguro()
     * Lee un double pero sin romper el programa si el usuario escribe mal.
     */
    private double leerDoubleSeguro(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = teclado.nextLine();

            try {
                return Double.parseDouble(entrada);
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Debes introducir un número decimal válido.");
            }
        }
    }
}