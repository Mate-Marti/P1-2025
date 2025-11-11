package co.edu.uniquindio.poo.preparcial3.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.LocalDate;

public class Venta {
    private String fecha;
    private Cliente cliente;
    private ObservableList<ItemVenta> items = FXCollections.observableArrayList();
    private double total;

    public Venta(Cliente cliente) {
        this.fecha = LocalDate.now().toString();
        this.cliente = cliente;
    }

    public void agregarItem(ItemVenta item){
        items.add(item);
        total += item.getTotal();
    }

    public String getFecha() {
        return fecha; }
    public Cliente getCliente() {
        return cliente; }
    public double getTotal() {
        return total; }
    public ObservableList<ItemVenta> getItems() {
        return items; }
}
