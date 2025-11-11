package co.edu.uniquindio.poo.preparcial3.model;

public class ItemVenta {
    private Producto producto;
    private int cantidad;
    private double total;

    public ItemVenta(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.total = producto.getPrecio() * cantidad;
    }

    public Producto getProducto() {
        return producto; }
    public int getCantidad() {
        return cantidad; }
    public double getTotal() {
        return total; }
}
