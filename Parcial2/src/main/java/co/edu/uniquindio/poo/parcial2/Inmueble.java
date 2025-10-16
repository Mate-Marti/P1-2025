package co.edu.uniquindio.poo.parcial2;

public class Inmueble {
    private String tipo;
    private String ciudad;
    private String nHabitaciones;
    private String nPisos;
    private String precio;

    public Inmueble(String tipo, String ciudad, String nHabitaciones, String nPisos, String precio) {
        this.tipo = tipo;
        this.ciudad = ciudad;
        this.nHabitaciones = nHabitaciones;
        this.nPisos = nPisos;
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getnHabitaciones() {
        return nHabitaciones;
    }

    public void setnHabitaciones(String nHabitaciones) {
        this.nHabitaciones = nHabitaciones;
    }

    public String getnPisos() {
        return nPisos;
    }

    public void setnPisos(String nPisos) {
        this.nPisos = nPisos;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return tipo +  " " + ciudad +  " " + nHabitaciones + " " + nPisos + " " + precio;
    }
}