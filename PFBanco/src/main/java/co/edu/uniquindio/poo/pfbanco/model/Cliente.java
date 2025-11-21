package co.edu.uniquindio.poo.pfbanco.model;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario {

    private List<Cuentabanco> cuentas;
    private String reporte;

    public Cliente(String id, String nombre, String usuario, String contraseña) {
        super(id, nombre, usuario, contraseña);
        this.cuentas = new ArrayList<>();
    }

    public void agregarCuenta(Cuentabanco cuenta) {
        cuentas.add(cuenta);
    }

    public void mostrarCuentas() {
        for (Cuentabanco c : cuentas) {
            System.out.println("cuenta:" + c.getNumeroCuenta() + "saldo:" + c.consultarSaldo());
        }
    }

    public Cuentabanco obtenerCuenta(String numero) {
        for (Cuentabanco c : cuentas) {
            if (c.getNumeroCuenta().equals(numero)) return c;
        }
        return null;
    }

    public String getReporte() {
        return reporte;
    }

    public void setReporte(String reporte) {
        this.reporte = reporte;
    }

    public List<Cuentabanco> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuentabanco> cuentas) {
        this.cuentas = cuentas;
    }
}