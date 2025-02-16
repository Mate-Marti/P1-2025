import java.util.Collection;
import java.util.LinkedList;


public class Universidad {
    private String nombre;

    public Estudiante estudiante;
    public Collection<Estudiante> estudiantes;


    public Universidad(String nombre, double notaDef){
        this.nombre=nombre;


        estudiantes = new LinkedList<>();
    }


    public boolean verificarEstudiante(String id){
        boolean centinela = false;
        for (Estudiante estudiante : estudiantes) {
            if(estudiante.getId().equals(id)){
                centinela = true;

            }
        }

        return centinela;

    }

    public void agregarEstudiante(Estudiante estudiante){
        if(verificarEstudiante(estudiante.getId())){}
        estudiantes.add(estudiante);

    }



    public void eliminarEstudiante(String id){

    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }





    public Collection<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(Collection<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    @Override
    public String toString() {
        return "curso{" +
                "nombre='" + nombre + '\'' +
                ", estudiante=" + estudiante +
                '}';
    }

}
