package co.edu.uniquindio.poo.biblioteca.model;


    public class LibroDigital extends Libro {
        /**
         * Metodo constructor de la clase LibroDigital
         * @param titulo
         * @param autor
         * @param editorial
         * @param anoPublicacion
         */
        public LibroDigital(String titulo, String autor, String editorial, String anoPublicacion) {
            super(titulo,autor,editorial,anoPublicacion,Genero.ACADEMICO,EstadoLibro.NO_EXISTENTE);
        }
    }

