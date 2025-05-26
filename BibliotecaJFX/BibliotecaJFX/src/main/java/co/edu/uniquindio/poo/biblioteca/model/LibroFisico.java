package co.edu.uniquindio.poo.biblioteca.model;

public class LibroFisico extends Libro {
    /**
     *Metodo costructor de la clase LibroFisico
     * @param titulo
     * @param autor
     * @param editorial
     * @param anoPublicacion
     */
    public LibroFisico(String titulo, String autor, String editorial, String anoPublicacion) {
        super(titulo,autor,editorial,anoPublicacion,Genero.ACADEMICO,EstadoLibro.NO_EXISTENTE);
    }
}
