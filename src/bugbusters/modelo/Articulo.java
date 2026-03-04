package bugbusters.modelo;

/*
 * Clase Articulo
 *
 * Representa un producto que vende la tienda.
 */
public class Articulo {

    // Atributos del artículo
    private String codigo;
    private String descripcion;
    private double precioVenta;
    private double gastosEnvio;
    private int tiempoPreparacion;

    /*
     * Constructor
     * Crea un artículo con todos sus datos.
     */
    public Articulo(String codigo, String descripcion, double precioVenta, double gastosEnvio, int tiempoPreparacion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precioVenta = precioVenta;
        this.gastosEnvio = gastosEnvio;
        this.tiempoPreparacion = tiempoPreparacion;
    }

    // Getter y Setter de codigo
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    // Getter y Setter de descripcion
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Getter y Setter de precioVenta
    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    // Getter y Setter de gastosEnvio
    public double getGastosEnvio() {
        return gastosEnvio;
    }

    public void setGastosEnvio(double gastosEnvio) {
        this.gastosEnvio = gastosEnvio;
    }

    // Getter y Setter de tiempoPreparacion
    public int getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public void setTiempoPreparacion(int tiempoPreparacion) {
        this.tiempoPreparacion = tiempoPreparacion;
    }

    /*
     * toString()
     * Muestra el artículo como texto.
     */
    @Override
    public String toString() {
        return "Articulo{" +
                "codigo='" + codigo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precioVenta=" + precioVenta +
                ", gastosEnvio=" + gastosEnvio +
                ", tiempoPreparacion=" + tiempoPreparacion +
                '}';
    }
}