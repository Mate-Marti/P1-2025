public class Cine {
    private String nombre;
    private List<Funcion> listfuncion;


    public cine(String nombre) {
        this.nombre = nombre;
        this.listfuncion = new ArrayList<Funcion>();
    }

    public void agregarFuncion(Funcion funcion) {
        listfuncion.add(funcion);
    }
}