package co.edu.uniquindio.poo.biblioteca.model;

public class Persona {

    private String nombre;
    private String cedula;

    /**
     * metodo constructor para la clase persona
     * @param nombre
     * @param cedula
     */
    public Persona(String nombre, String cedula) {
        this.nombre = nombre;
        this.cedula = cedula;

    }
    /**
     * metodo get para obtener el nombre de la persona
     * @return
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * metodo set para modificar el nombre de la persona
     * @return
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * metodo get para obtener la cedula de la persona
     * @return
     */
    public String getCedula() {
        return cedula;
    }
    /**
     * metodo set para modificar la cedula de la persona
     * @return
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    
}

