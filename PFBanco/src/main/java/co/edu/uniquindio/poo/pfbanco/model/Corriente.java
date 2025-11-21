package co.edu.uniquindio.poo.pfbanco.model;

public class Corriente extends Cuentabanco{
    private double limiteSobregiro;

    public Corriente(String numeroCuenta, Cliente titular, double saldoInicial, double limiteSobregiro) {
        super(numeroCuenta, titular, saldoInicial);
        this.limiteSobregiro = limiteSobregiro;
    }

    @Override
    public void retirar(double monto) {
        if (saldo + limiteSobregiro >= monto) {
            saldo -= monto;
        } else {

            throw new RuntimeException("Límite de sobregiro excedido.");
        }
    }

    public double getLimiteSobregiro() {
        return limiteSobregiro;
    }

    public void setLimiteSobregiro(double limiteSobregiro) {
        this.limiteSobregiro = limiteSobregiro;
    }
}
