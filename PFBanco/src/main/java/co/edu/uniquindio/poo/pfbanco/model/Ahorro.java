package co.edu.uniquindio.poo.pfbanco.model;

public class Ahorro extends Cuentabanco {
    private double tasaInteres;

    public Ahorro(String numeroCuenta, Cliente titular, double saldoInicial, double tasaInteres) {
        super(numeroCuenta, titular, saldoInicial);
        this.tasaInteres = tasaInteres;
    }

    public void aplicarInteresMensual() {
        saldo += saldo * tasaInteres;
        System.out.println("Interés aplicado. Nuevo saldo: " + saldo);
    }
}
