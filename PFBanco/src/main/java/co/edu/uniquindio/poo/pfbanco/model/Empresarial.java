package co.edu.uniquindio.poo.pfbanco.model;

import java.util.List;

public class Empresarial extends Cuentabanco{
    private List<Cliente> representantes;
    private double montoMinimoApertura;

    public Empresarial(String numeroCuenta, Cliente titular, double saldoInicial, List<Cliente> representantes, double montoMinimoApertura) {
        super(numeroCuenta,titular,saldoInicial);
        this.representantes = representantes;
        this.montoMinimoApertura = montoMinimoApertura;
    }
}
