package co.edu.uniquindio.poo.preparcial3.model;

public class Cliente {
    private String documento;
    private String nombre;
    private String telefono;
    private String correo;

    public Cliente(String documento, String nombre, String telefono, String correo) {
        this.documento = documento;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
    }

    public String getDocumento() {
        return documento; }
    public String getNombre() {
        return nombre; }
    public String getTelefono() {
        return telefono; }
    public String getCorreo() {
        return correo; }

    public void setDocumento(String documento) {
        this.documento = documento; }
    public void setNombre(String nombre) {
        this.nombre = nombre; }
    public void setTelefono(String telefono) {
        this.telefono = telefono; }
    public void setCorreo(String correo) {
        this.correo = correo; }

    @Override
    public String toString() {
        return nombre + " (" + documento + ")";
    }
}