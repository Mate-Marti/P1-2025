package co.edu.uniquindio.poo.seguimiento;

public class Terrestre extends Vehiculo implements IMovimiento {

    public Terrestre( String nombre, String marca) {
        super(nombre, marca);
    }

    public void avanzar() {
        System.out.println("Avanzando vehiculo");

    }
    public void retroceder() {
        System.out.println("Retrocediendo vehiculo");

    }
    public void girar() {
        System.out.println("Girando vehiculo");
    }

    public void detenerse() {
        System.out.println("Deteniendo vehiculo");
    }
}
