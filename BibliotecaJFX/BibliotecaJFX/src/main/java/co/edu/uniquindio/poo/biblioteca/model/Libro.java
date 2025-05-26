package co.edu.uniquindio.poo.biblioteca.model;

public class Libro {
    private String titulo;
    private String autor;
    private String editorial;
    private String anoPublicacion;
    private Genero genero;
    private EstadoLibro estadoLibro;

    /**
     * Metodo constructor de la clase libro
     * @param titulo
     * @param autor
     * @param editorial
     * @param anoPublicacion
     * @param genero
     * @param estadoLibro
     */
    public Libro(String titulo, String autor, String editorial, String anoPublicacion, Genero genero, EstadoLibro estadoLibro) {
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.anoPublicacion = anoPublicacion;
        this.genero = genero;
        this.estadoLibro = estadoLibro;
    }

    /**
     * metodo get para obtener el titulo del libro
     * @return
     */
    public String getTitulo() {
        return titulo;
    }
    /**
     * metodo set para modificar el titulo del libro
     * @return
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    /**
     * metodo get para obtener el autor del libro
     * @return
     */
    public String getAutor() {
        return autor;
    }
    /**
     * metodo set para modificar el autor del libro
     * @return
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }
    /**
     * metodo get para obtener lq editorial del libro
     * @return
     */
    public String getEditorial() {
        return editorial;
    }
    /**
     * metodo set para modificar la editorial del libro
     * @return
     */
    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }
    /**
     * metodo get para obtener el año de publicacion del libro
     * @return
     */
    public String getAnoPublicacion() {
        return anoPublicacion;
    }
    /**
     * metodo get para modificar el año de publicacion del libro
     * @return
     */
    public void setAnoPublicacion(String anoPublicacion) {
        this.anoPublicacion = anoPublicacion;

    }

    /**
     * Metodo para obtener genero del libro
     * @return
     */
    public Genero getGenero() {
        return genero;
    }

    /**
     * metodo para modificar genero del libro
     * @param genero
     */
    public void setGenero(Genero genero) {
        this.genero = genero;

    }
    /**
     * metodo get para obtener el estado del libro
     *
     * @param
     */
    public EstadoLibro getEstadoLibro() {
        return estadoLibro;
    }

    public void setEstadoLibro(EstadoLibro estadoLibro) {
        this.estadoLibro = estadoLibro;
    }
}
