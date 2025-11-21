package co.edu.uniquindio.poo.pfbanco.model;

public class Administrador extends Usuario {

    public Administrador(String id, String nombre, String usuario, String contraseña) {
        super(id, nombre, usuario, contraseña);
    }

    public void crearCuenta(Banco banco, Cliente cliente, Cuentabanco cuenta) {
        banco.abrirCuenta(cliente, cuenta);
        System.out.println("Cuenta creada para el cliente: " + cliente.getNombre());
    }

    public void eliminarCuenta(Banco banco, String numeroCuenta) {
        Cuentabanco cuenta = banco.buscarCuenta(numeroCuenta);
        if (cuenta != null) {
            banco.cerrarCuenta(numeroCuenta);
            System.out.println("Cuenta eliminada: " + numeroCuenta);
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

    public void editarCuenta(Banco banco, String numeroCuenta, Cliente nuevoTitular, double nuevoSaldo) {
        Cuentabanco cuenta = banco.buscarCuenta(numeroCuenta);
        if (cuenta != null) {

            cuenta.titular = nuevoTitular;
            cuenta.saldo = nuevoSaldo;
            System.out.println("Cuenta " + numeroCuenta + " editada correctamente.");
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }
    public void verReporteTransacciones(Banco banco) {
        banco.generarReporteTransacciones();
    }


    public void verHistorialTransacciones(Banco banco) {
        banco.mostrarResumenGeneral();
    }
}

