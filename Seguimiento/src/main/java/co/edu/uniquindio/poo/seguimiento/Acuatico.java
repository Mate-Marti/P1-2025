package co.edu.uniquindio.poo.seguimiento;

public class Acuatico extends Vehiculo implements IEncenderApagar {

    public Acuatico( String nombre, String marca) {
        super(nombre, marca);
    }

    public void encender() {
        System.out.println("Encendiendo vehiculo");

    }
    public void apagar() {
        System.out.println("Apagando vehiculo");

    }
}