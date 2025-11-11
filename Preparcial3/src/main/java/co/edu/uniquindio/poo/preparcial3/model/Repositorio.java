package co.edu.uniquindio.poo.preparcial3.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Repositorio {
    private static Repositorio instancia = new Repositorio();

    private ObservableList<Cliente> clientes = FXCollections.observableArrayList();
    private ObservableList<Producto> productos = FXCollections.observableArrayList();
    private ObservableList<Venta> ventas = FXCollections.observableArrayList();

    private Repositorio(){}

    public static Repositorio getInstancia() {
        return instancia; }
    public ObservableList<Cliente> getClientes() {
        return clientes; }
    public ObservableList<Producto> getProductos() {
        return productos; }
    public ObservableList<Venta> getVentas() {
        return ventas; }

    public void cargarDatos(){
        productos.addAll(
                new Producto("Lapicero", 500),
                new Producto("Cuaderno", 2000),
                new Producto("Mouse", 25000),
                new Producto("Teclado", 40000),
                new Producto("Monitor", 600000)
        );
        clientes.addAll(
                new Cliente("1001","Juan Perez","3001112233","juan@gmail.com"),
                new Cliente("1002","Ana Gomez","3102233444","ana@gmail.com")
        );
    }
}
