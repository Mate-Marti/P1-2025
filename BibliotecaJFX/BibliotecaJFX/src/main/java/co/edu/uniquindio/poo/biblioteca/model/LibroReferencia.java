package co.edu.uniquindio.poo.biblioteca.model;

public class LibroReferencia extends Libro {
    /**
     * Metodo constructor de la clase LibroReferencia
     * @param titulo
     * @param autor
     * @param editorial
     * @param anoPublicacion
     */
    public LibroReferencia(String titulo, String autor, String editorial, String anoPublicacion) {

        super(titulo,autor,editorial,anoPublicacion,Genero.ACADEMICO,EstadoLibro.NO_EXISTENTE);

    }
}
