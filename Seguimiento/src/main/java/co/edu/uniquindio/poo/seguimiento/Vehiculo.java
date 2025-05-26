package co.edu.uniquindio.poo.seguimiento;

import java.util.ArrayList;
import java.util.List;

public class Vehiculo {
    private String nombre;
    private  String marca;
    private List<Vehiculo> listvehiculo;
    public Vehiculo(String nombre, String marca) {
        this.nombre = nombre;
        this.marca = marca;
        this.listvehiculo = new ArrayList<Vehiculo>();
    }
    public void agregarVehiculo(Vehiculo vehiculo) {
        this.listvehiculo.add(vehiculo);
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }


}