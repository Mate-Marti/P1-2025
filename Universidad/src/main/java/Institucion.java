import java.util.Collection;
import java.util.LinkedList;

public class Institucion {

    private String titulo;
    public Alumno alumno;
    public Collection<Alumno> listaAlumnos;

    /**
     * @param titulo
     */
    public Curso(String titulo){
        this.titulo = titulo;
        listaAlumnos = new LinkedList<>();
    }

    public boolean comprobarAlumno(String codigo){
        boolean encontrado = false;
        for (Alumno alumno : listaAlumnos) {
            if(alumno.getCodigo().equals(codigo)){
                encontrado = true;
            }
        }
        return encontrado;
    }

    public void incluirAlumno(Alumno alumno){
        if(comprobarAlumno(alumno.getCodigo())){}
        listaAlumnos.add(alumno);
    }

    public void suprimirAlumno(String codigo){
        // Método aún sin implementar
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Collection<Alumno> getListaAlumnos() {
        return listaAlumnos;
    }

    public void setListaAlumnos(Collection<Alumno> listaAlumnos) {
        this.listaAlumnos = listaAlumnos;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "titulo='" + titulo + '\'' +
                ", alumno=" + alumno +
                ", listaAlumnos=" + listaAlumnos +
                '}';
    }
}