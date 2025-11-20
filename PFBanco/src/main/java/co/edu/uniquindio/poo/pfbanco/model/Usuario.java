package co.edu.uniquindio.poo.pfbanco.model;

public abstract class Usuario {

    private String id;
    private String nombre;
    private String usuario;
    private String contraseña;

    public Usuario(String id, String nombre, String usuario, String contraseña) {
        this.id = id;
        this.nombre = nombre;
        this.usuario = usuario;
        this.contraseña = contraseña;
    }
    public boolean iniciarSesion(String usuario, String contraseña){
        return  this.usuario.equals(usuario) && this.contraseña.equals(contraseña);
    }

    public void cerrarSesion(){
        System.out.println(nombre + "ha cerrado sesion");
    }
    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}