package co.edu.uniquindio.poo.seguimiento;

public class Anfibio extends Vehiculo implements IMovimiento, IEncenderApagar {

    public Anfibio(String nombre, String marca) {

        super(nombre, marca);
    }
    public void detenerse() {
        System.out.println("Terrestres detenido");

    }
    public void girar() {
        System.out.println("Terrestres girando");
    }
    public void retroceder() {
        System.out.println("Terrestres detenido");
    }
    public void avanzar() {
        System.out.println("Terrestres avanzado");
    }
    public void encender() {
        System.out.println("Encendiendo vehiculo");

    }
    public void apagar() {
        System.out.println("Apagando vehiculo");

    }


}