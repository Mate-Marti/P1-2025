package co.edu.uniquindio.poo.pfbanco.model;

public abstract class Cuentabanco {
    protected String numeroCuenta;
    protected Cliente titular;
    protected double saldo;

    public Cuentabanco(String numerocuenta, Cliente titular, double saldoInicial) {
        this.numeroCuenta = numerocuenta;
        this.titular = titular;
        this.saldo = saldoInicial;
    }

    public void depositar (double monto) {
        saldo += monto;
    }

    public void retirar(double monto) {
        if (saldo >= monto) {
            saldo -= monto;
        } else {

            throw new RuntimeException("Fondos insuficientes");
        }
    }

    public void transferir(Cuentabanco destino, double monto) {
        retirar(monto);
        destino.depositar(monto);

    }

    public double consultarSaldo() {return saldo;}
    public String getNumeroCuenta(){return numeroCuenta;}

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public Cliente getTitular() {
        return titular;
    }

    public void setTitular(Cliente titular) {
        this.titular = titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}