package co.edu.uniquindio.poo.pfbanco.model;

public class Transferencia extends Transaccion{
    private Cuentabanco cuentaOrigen;
    private Cuentabanco cuentaDestino;

    public Transferencia(Cuentabanco cuentaOrigen, Cuentabanco cuentaDestino,double monto) {
        super(monto);
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
        ejecutar();
    }

    @Override
    public  void ejecutar(){
        cuentaOrigen.transferir(cuentaDestino,monto);
        this.setEstado("COMPLETADO");
        BancoData.getInstancia().getBanco().registrarTransaccion(this);
    }


}