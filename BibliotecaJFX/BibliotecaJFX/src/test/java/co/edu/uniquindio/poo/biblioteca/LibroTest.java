package co.edu.uniquindio.poo.biblioteca;

import co.edu.uniquindio.poo.biblioteca.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LibroTest {

    @Test
    public void testConstructorYGetters() {
        Libro libro = new Libro( "Cien años de soledad", "Gabriel García Márquez", "Sudamericana", "1967", Genero.SUSPENSO , EstadoLibro.DISPONIBLE);
        assertEquals("Cien años de soledad", libro.getTitulo());
        assertEquals("Gabriel García Márquez", libro.getAutor());
        assertEquals("Sudamericana", libro.getEditorial());
        assertEquals("1967", libro.getAnoPublicacion());
        assertEquals(Genero.SUSPENSO, libro.getGenero());
        assertEquals(EstadoLibro.DISPONIBLE, libro.getEstadoLibro());
    }

    @Test
    public void testSetters() {
        Libro libro = new Libro( "Titulo", "Autor", "Editorial", "2000", Genero.CIENCIA_FICCION, EstadoLibro.DISPONIBLE);

        libro.setTitulo("Nuevo Titulo");
        libro.setAutor("Nuevo Autor");
        libro.setEditorial("Nueva Editorial");
        libro.setAnoPublicacion("2024");
        libro.setGenero(Genero.SUSPENSO);
        libro.setEstadoLibro(EstadoLibro.PRESTADO);

        assertEquals("Nuevo Titulo", libro.getTitulo());
        assertEquals("Nuevo Autor", libro.getAutor());
        assertEquals("Nueva Editorial", libro.getEditorial());
        assertEquals("2024", libro.getAnoPublicacion());
        assertEquals(Genero.SUSPENSO, libro.getGenero());
        assertEquals(EstadoLibro.PRESTADO, libro.getEstadoLibro());
    }
}
