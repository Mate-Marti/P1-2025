package co.edu.uniquindio.poo.seguimiento;

public class Aereo extends Vehiculo implements IDespegarAterrizar {



    public Aereo( String nombre, String marca) {

        super(nombre, marca);
    }

    public void despegar() {

        System.out.println("Despegando vehiculo");
    }

    public void aterrizar() {

        System.out.println("Aterrizando vehiculo");

    }


}