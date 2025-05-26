package co.edu.uniquindio.poo.biblioteca.model;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private String nombre;
    private int nit;
    private List<Persona> listPersona;
    private List<Libro> listLibro;
    private Libro libro;

    /**
     * Metodo constructor de la clase bibloteca
     *
     * @param nombre
     * @param nit
     */
    public Biblioteca(String nombre, int nit, Libro libro) {
        this.nombre = nombre;
        this.nit = nit;
        this.libro = libro;
        this.listPersona = new ArrayList<>();
        this.listLibro = new ArrayList<>();

    }

    /**
     * Metodo get para recibir un nombre de la bibloteca
     *
     * @return
     */

    public String getNombre() {
        return nombre;
    }

    /**
     * metodo set para cambiar el nombre de la bibloteca
     *
     * @param nombre
     */

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo para recibir el nit de la bibloteca
     *
     * @return
     */

    public int getNit() {
        return nit;
    }

    /**
     * metodo para cambiar el nit de la bibloteca
     *
     * @param nit
     */
    public void setNit(int nit) {
        this.nit = nit;
    }

    /**
     * metodo para recibir una lista persona
     *
     * @return
     */
    public List<Persona> getListPersona() {
        return listPersona;
    }

    /**
     * metodo para modificar una lista persona
     *
     * @param listPersona
     */
    public void setListPersona(List<Persona> listPersona) {
        this.listPersona = listPersona;
    }

    /**
     * metodo para recibir una lista de libros
     *
     * @return
     */
    public  List<Libro> getListLibros() {
        return listLibro;
    }

    /**
     * metodo para modificar una lista de libros
     *
     * @return
     */
    public void setListLibros(List<Libro> listLibros) {
        this.listLibro = listLibros;
    }


    /**
     * metodo para agregar un libro a una lista de libros
     *
     * @param libro
     */
    public void agregarLibro(Libro libro) {
        listLibro.add(libro);
    }

    /**
     * metodo para actualizar un libro en la lista de libros
     *
     * @param libro
     */
    public void actualizarLibro(Libro libro, int nit) {

        Boolean libroActualizar = false;
        for (int i = 0; i < listLibro.size(); i++) {
            if (listLibro.get(i).getEstadoLibro().equals(libro.getEstadoLibro())) {
                listLibro.set(i, libro);
                libroActualizar = true;
                break;
            }
        }
    }
    /**
     * metodo para eliminar un libro en la lista de libros
     *
     * @param libro
     */
    public void eliminarLibro(Libro libro){
        Boolean libroEliminar = false;
        for(int i = 0; i < listLibro.size(); i++){
            if(listLibro.get(i).getEstadoLibro().equals(libro.getEstadoLibro())){
                listLibro.remove(i);
                libroEliminar = true;
                break;
            }
        }
    }

    /**
     * metodo get para obtener la listLibro
     *
     * @param
     */
    public List<Libro> getListLibro() {
        return listLibro;
    }
    /**
     * metodo get para obtener la listLibro
     *
     * @param listLibro
     */
    public void setListLibro(List<Libro> listLibro) {
        this.listLibro = listLibro;
    }
    /**
     * metodo get para obtener la listLibro
     *
     * @param
     */
    public Libro getLibro() {
        return libro;
    }
    /**
     * metodo get para obtener la listLibro
     *
     * @param libro
     */
    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public void eliminarPersona(Persona persona){
        Boolean PersonaEliminar = false;
        for(int i = 0; i < listPersona.size(); i++){
            if(listPersona.get(i).getNombre().equalsIgnoreCase(nombre)){
                listPersona.remove(i);
                PersonaEliminar = true;
                break;
            }
        }
    }

}
