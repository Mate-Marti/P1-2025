package co.edu.uniquindio.poo.pfbanco.model;

import java.util.Date;
import java.util.UUID;

public class Transaccion {

    private String id;
    private Date fecha;
    protected double monto;
    private String estado;
    private String clienteDestino;
    private String clienteOrigen;

    public Transaccion(double monto) {
        this.monto = monto;
        this.fecha = new Date();
        this.estado = "pendiente";
        this.id = UUID.randomUUID().toString();
    }

    public Transaccion(String id, Date fecha, double monto, String estado, String clienteDestino, String clienteOrigen) {
        this.id = id;
        this.fecha = fecha;
        this.monto = monto;
        this.estado = estado;
        this.clienteDestino = clienteDestino;
        this.clienteOrigen = clienteOrigen;

    }

    public Transaccion(String id, Date fecha, String estado, String clienteDestino, String clienteOrigen) {
        this.id = id;
        this.fecha = fecha;
        this.monto = 0; // Se asume 0 si no se da
        this.estado = estado;
        this.clienteDestino = clienteDestino;
        this.clienteOrigen = clienteOrigen;

    }

    public void ejecutar() {
    }

    public Transaccion() {
        this.fecha = new Date();
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getClienteDestino() {
        return clienteDestino;
    }

    public void setClienteDestino(String clienteDestino) {
        this.clienteDestino = clienteDestino;
    }

    public String getClienteOrigen() {
        return clienteOrigen;
    }

    public void setClienteOrigen(String clienteOrigen) {
        this.clienteOrigen = clienteOrigen;
    }
}
