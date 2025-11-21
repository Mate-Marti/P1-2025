package co.edu.uniquindio.poo.pfbanco.model;

import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BancoData {

    private static BancoData instancia;
    private Banco banco;

    private ObservableList<Cliente> listaClientesObservable;
    private ObservableList<Cuentabanco> listaCuentasObservable;
    private ObservableList<Transaccion> listaTransaccionesObservable;
    private ObservableList<Cajero> listaCajerosObservable;

    private BancoData() {
        banco = new Banco("Banco UQ");
        inicializarDatosPrueba();

        this.listaClientesObservable = FXCollections.observableArrayList(banco.getClientes());
        this.listaCuentasObservable = FXCollections.observableArrayList(banco.getCuentas());
        this.listaTransaccionesObservable = FXCollections.observableArrayList(banco.getTransacciones());
        this.listaCajerosObservable = FXCollections.observableArrayList();
    }

    public static BancoData getInstancia() {
        if (instancia == null) {
            instancia = new BancoData();
        }
        return instancia;
    }

    public Banco getBanco() {
        return banco;
    }

    public ObservableList<Cliente> getListaClientes() {
        return listaClientesObservable;
    }

    public ObservableList<Cuentabanco> getListaCuentas() {
        return listaCuentasObservable;
    }

    public ObservableList<Cajero> getListaCajeros() {
        return listaCajerosObservable;
    }

    public ObservableList<Transaccion> getListaTransacciones() {
        return listaTransaccionesObservable;
    }

    private void inicializarDatosPrueba() {
        Cliente juan = new Cliente("1001", "Juan Pérez", "juanp", "123");
        Cuentabanco cAhorroJuan = new Ahorro("1001A", juan, 500000.0, 0.05);
        Cuentabanco cCorrienteJuan = new Corriente("1001C", juan, 150000.0, 100000.0);

        banco.registrarCliente(juan);
        banco.abrirCuenta(juan, cAhorroJuan);
        banco.abrirCuenta(juan, cCorrienteJuan);

        Cliente ana = new Cliente("1002", "Ana López", "anal", "456");
        Cuentabanco cAhorroAna = new Ahorro("1002A", ana, 80000.0, 0.04);

        banco.registrarCliente(ana);
        banco.abrirCuenta(ana, cAhorroAna);

        Date now = new Date();

        Transaccion t1 = new Transaccion("T001", now, 100000.0, "COMPLETADA", "N/A", juan.getId());
        banco.registrarTransaccion(t1);

        Transaccion t2 = new Transaccion("T002", new Date(now.getTime() - 3600000), 50000.0, "COMPLETADA", ana.getId(), juan.getId());
        banco.registrarTransaccion(t2);

        Transaccion t3 = new Transaccion("T003", new Date(now.getTime() - 7200000), 5000000.0, "FALLIDA", juan.getId(), ana.getId());
        banco.registrarTransaccion(t3);
    }
    public void registrarCajero(Cajero cajero) {
        listaCajerosObservable.add(cajero);
    }

    public Cajero buscarCajeroPorUsuario(String usuario) {
        for (Cajero c : listaCajerosObservable) {
            if (c.getUsuario().equals(usuario)) {
                return c;
            }
        }
        return null;
    }

    public void eliminarCajero(Cajero cajero) {
        listaCajerosObservable.remove(cajero);
    }
}