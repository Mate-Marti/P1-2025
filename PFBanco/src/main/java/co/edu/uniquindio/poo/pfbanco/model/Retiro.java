package co.edu.uniquindio.poo.pfbanco.model;

public class Retiro extends Transaccion{
    private Cuentabanco cuentaOrigen;

    public Retiro(Cuentabanco cuentaOrigen, double monto) {
        super(monto);
        this.cuentaOrigen = cuentaOrigen;
        ejecutar();
    }
    @Override
    public void ejecutar() {
        cuentaOrigen.retirar(getMonto());
    }



}
