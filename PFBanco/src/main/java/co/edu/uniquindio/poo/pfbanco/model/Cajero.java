package co.edu.uniquindio.poo.pfbanco.model;

public class Cajero extends Usuario {

    public Cajero(String id, String nombre, String usuario, String contraseña) {
        super(id, nombre, usuario, contraseña);
    }


    public void realizaDeposito(Banco banco, String numeroCuenta, double monto) {
        Cuentabanco cuenta = banco.buscarCuenta(numeroCuenta);
        if (cuenta != null) {
            cuenta.depositar(monto);
            banco.registrarTransaccion(new Deposito(cuenta, monto));
        }
    }

    public void realizarRetiro(Banco banco, String numeroCuenta, double monto) {
        Cuentabanco cuenta = banco.buscarCuenta(numeroCuenta);

        if (cuenta == null) {
            System.out.println("Error: Cuenta no encontrada.");
            return;
        }

        try {
            cuenta.retirar(monto);

            banco.registrarTransaccion(new Retiro(cuenta, monto));
            System.out.println("Retiro de $" + monto + " exitoso.");

        } catch (RuntimeException e) {
            System.err.println("Error en el retiro: " + e.getMessage());

        }
    }

    public void realizarTransferencia(Banco banco, String origen, String destino, double monto) {
        Cuentabanco cuentaOrigen = banco.buscarCuenta(origen);
        Cuentabanco cuentaDestino = banco.buscarCuenta(destino);

        if (cuentaOrigen == null) {
            System.err.println("Error: Cuenta de origen no encontrada.");
            return;
        }
        if (cuentaDestino == null) {
            System.err.println("Error: Cuenta de destino no encontrada.");
            return;
        }
        try {
            cuentaOrigen.transferir(cuentaDestino, monto);
            banco.registrarTransaccion(new Transferencia(cuentaOrigen, cuentaDestino, monto));
            System.out.println("Transferencia registrada con éxito.");


        } catch (RuntimeException e) {
            System.err.println("Error al realizar o registrar transacción: " + e.getMessage());
        }
    }
}
