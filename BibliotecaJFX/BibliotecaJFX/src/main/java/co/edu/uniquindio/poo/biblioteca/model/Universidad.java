package co.edu.uniquindio.poo.biblioteca.model;

public class Universidad {
    private String nombre;
    private String nit;

    /**
     * metodo constructor para la clase universidad
     * @param nombre
     * @param nit
     */
    public Universidad(String nombre, String nit) {
        this.nombre = nombre;
        this.nit = nit;

    }
    /**
     * metodo get para obtener el nombre de la universidad
     * @return
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * metodo set para modificar el nombre de la universidad
     * @return
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * metodo get para obtener el nit de la universidad
     * @return
     */
    public String getNit() {
        return nit;
    }
    /**
     * metodo set para modificar el ni de la universidad
     * @return
     */
    public void setNit(String nit) {
        this.nit = nit;
    }
}
