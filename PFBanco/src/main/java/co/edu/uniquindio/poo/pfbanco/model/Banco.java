package co.edu.uniquindio.poo.pfbanco.model;
import java.util.ArrayList;
import java.util.List;

public class Banco {
    private String nombre;
    private List<Cliente> clientes;
    private List<Cuentabanco> cuentas;
    private List<Transaccion> transacciones;

    public Banco(String nombre) {
        this.nombre = nombre;
        this.clientes = new ArrayList<>();
        this.cuentas = new ArrayList<>();
        this.transacciones = new ArrayList<>();
    }

    public void registrarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void abrirCuenta(Cliente cliente, Cuentabanco cuenta) {
        cliente.agregarCuenta(cuenta);
        cuentas.add(cuenta);
    }

    public Cuentabanco buscarCuenta(String numeroCuenta) {
        for (Cuentabanco c : cuentas) {
            if (c.getNumeroCuenta().equals(numeroCuenta)) {
                return c;
            }
        }
        return null;
    }

    public void cerrarCuenta(String numeroCuenta) {
        Cuentabanco cuenta = buscarCuenta(numeroCuenta);
        if (cuenta != null) {
            cuentas.remove(cuenta);
        }
    }
    public void generarReporteTransacciones() {
        System.out.println("===== Reporte de Transacciones del Banco: " + nombre + " =====");
        for (Transaccion t : transacciones) {
            System.out.println("ID: " + t.getId());
            System.out.println("Fecha: " + t.getFecha());
            System.out.println("Monto: " + t.getMonto());
            System.out.println("Estado: " + t.getEstado());
            System.out.println("Cliente Origen: " + t.getClienteOrigen());
            System.out.println("Cliente Destino: " + t.getClienteDestino());
            System.out.println("-----------------------------------------");
        }
    }

    public void registrarTransaccion(Transaccion transaccion) {
        transacciones.add(transaccion);
    }

    public void mostrarResumenGeneral() {
        System.out.println("Banco: " + nombre);
        System.out.println("Clientes registrados: " + clientes.size());
        System.out.println("Cuentas activas: " + cuentas.size());
        System.out.println("Transacciones realizadas: " + transacciones.size());
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Cuentabanco> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuentabanco> cuentas) {
        this.cuentas = cuentas;
    }

    public List<Transaccion> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(List<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }
}


