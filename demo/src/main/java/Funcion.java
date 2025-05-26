public abstract class Funcion {
    private String tituloPelicula;
    private String descripcionPelicula;
    private String horarioPelicula;
    private int preciobase;

    public Funcion(String tituloPelicula, String descripcionPelicula, String horarioPelicula, int preciobase) {
        this.tituloPelicula = tituloPelicula;
        this.descripcionPelicula = descripcionPelicula;
        this.horarioPelicula = horarioPelicula;
        this.preciobase = preciobase;
    }

    public abstract void calcularEntrada() {
    }
}

