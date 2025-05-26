package co.edu.uniquindio.poo.biblioteca;

import co.edu.uniquindio.poo.biblioteca.model.EstadoLibro;
import co.edu.uniquindio.poo.biblioteca.model.Genero;
import co.edu.uniquindio.poo.biblioteca.model.Libro;
import co.edu.uniquindio.poo.biblioteca.model.Biblioteca;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

    public class BibliotecaTest {

        private Biblioteca biblioteca;
        private Libro libro1;
        private Libro libro2;

        @BeforeEach
        public void setUp() {
            libro1 = new Libro("Cien Años de Soledad", "Gabriel García Márquez", "Disponible", "1988", Genero.CIENCIA_FICCION, EstadoLibro.DISPONIBLE);
            libro2 = new Libro("El Principito", "Antoine de Saint-Exupéry", "Oveja negra", "1099", Genero.POLICIAL ,EstadoLibro.DISPONIBLE);

            biblioteca = new Biblioteca("Biblioteca Central", 12345, libro1);
        }

        @Test
        public void testAgregarLibro() {
            biblioteca.agregarLibro(libro1);
            List<Libro> libros = biblioteca.getListLibros();
            assertEquals(1, libros.size());

        }

        @Test
        public void testActualizarLibro() {
            biblioteca.agregarLibro(libro2);
            Libro libroActualizado = new Libro("El Principito", "Antoine de Saint-Exupéry", "Santillana","1234", Genero.POLICIAL ,EstadoLibro.DISPONIBLE);
            biblioteca.actualizarLibro(libroActualizado, 12345);

            Libro libro = biblioteca.getListLibros().get(0);
            assertEquals(EstadoLibro.DISPONIBLE, libro.getEstadoLibro());
        }

        @Test
        public void testEliminarLibro() {
            biblioteca.agregarLibro(libro2);
            biblioteca.eliminarLibro(libro2);
            assertEquals(0, biblioteca.getListLibros().size());
        }
    }



