package co.edu.uniquindio.poo.pfbanco.model;

public class Deposito extends Transaccion {
    private Cuentabanco cuentaDestino;

    public Deposito(Cuentabanco cuentaDestino, double monto) {
        super(monto);
        this.cuentaDestino = cuentaDestino;
        ejecutar();
    }

    @Override
    public void ejecutar() {
        cuentaDestino.depositar(monto);
    }

}