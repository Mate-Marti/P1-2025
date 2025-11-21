package co.edu.uniquindio.poo.pfbanco.viewController;

import co.edu.uniquindio.poo.pfbanco.model.BancoData;
import co.edu.uniquindio.poo.pfbanco.model.Cliente;
import co.edu.uniquindio.poo.pfbanco.model.Ahorro;
import co.edu.uniquindio.poo.pfbanco.model.Corriente;
import co.edu.uniquindio.poo.pfbanco.model.Cuentabanco;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistroClienteViewController {

    @FXML private TextField txtId;
    @FXML private TextField txtNombre;
    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtContrasena;

    @FXML private ComboBox<String> comboTipoCuenta;
    @FXML private TextField txtSaldoInicial;
    @FXML private TextField txtTasaInteres;
    @FXML private TextField txtLimiteSobregiro;

    private BancoData data = BancoData.getInstancia();

    @FXML
    public void initialize() {
        comboTipoCuenta.setItems(FXCollections.observableArrayList("Ahorro", "Corriente"));

        comboTipoCuenta.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            boolean isAhorro = "Ahorro".equals(newVal);
            boolean isCorriente = "Corriente".equals(newVal);

            txtTasaInteres.setDisable(!isAhorro);
            txtLimiteSobregiro.setDisable(!isCorriente);

            if (!isAhorro) txtTasaInteres.clear();
            if (!isCorriente) txtLimiteSobregiro.clear();
        });
    }

    @FXML
    void guardar(ActionEvent event) {
        String id = txtId.getText();
        String nombre = txtNombre.getText();
        String usuario = txtUsuario.getText();
        String contrasena = txtContrasena.getText();
        String tipoCuenta = comboTipoCuenta.getValue();

        if (id.isEmpty() || nombre.isEmpty() || usuario.isEmpty() || contrasena.isEmpty() || tipoCuenta == null) {
            mostrarMensaje("Todos los campos obligatorios deben ser llenados (incluido el tipo de cuenta).");
            return;
        }

        double saldoInicial;
        try {
            saldoInicial = Double.parseDouble(txtSaldoInicial.getText().isEmpty() ? "0.0" : txtSaldoInicial.getText());
        } catch (NumberFormatException e) {
            mostrarMensaje("El saldo inicial debe ser un número válido.");
            return;
        }

        Cliente cliente = new Cliente(id, nombre, usuario, contrasena);

        Cuentabanco nuevaCuenta = null;
        try {
            if (tipoCuenta.equals("Ahorro")) {
                double tasaInteres = Double.parseDouble(txtTasaInteres.getText().isEmpty() ? "0.0" : txtTasaInteres.getText());
                nuevaCuenta = new Ahorro(id + "A", cliente, saldoInicial, tasaInteres);
            } else if (tipoCuenta.equals("Corriente")) {
                double limiteSobregiro = Double.parseDouble(txtLimiteSobregiro.getText().isEmpty() ? "0.0" : txtLimiteSobregiro.getText());
                nuevaCuenta = new Corriente(id + "C", cliente, saldoInicial, limiteSobregiro);
            }
        } catch (NumberFormatException e) {
            mostrarMensaje("Los parámetros de la cuenta deben ser números válidos.");
            return;
        }

        if(nuevaCuenta == null) {
            mostrarMensaje("No se pudo crear la cuenta, verifique los datos.");
            return;
        }
        data.getBanco().registrarCliente(cliente);
        data.getBanco().abrirCuenta(cliente, nuevaCuenta);
        data.getListaClientes().add(cliente);

        mostrarMensaje("Cliente y Cuenta Bancaria registrados correctamente.");
        limpiarCampos();
    }

    @FXML
    void actualizar(ActionEvent event) {
        Cliente cliente = buscarCliente(txtId.getText());

        if (cliente != null) {
            cliente.setNombre(txtNombre.getText());
            cliente.setUsuario(txtUsuario.getText());
            cliente.setContrasena(txtContrasena.getText());

            mostrarMensaje("Cliente actualizado correctamente.");
        } else {
            mostrarMensaje("Cliente no encontrado.");
        }
    }

    @FXML
    void eliminar(ActionEvent event) {
        Cliente cliente = buscarCliente(txtId.getText());

        if (cliente != null) {
            data.getBanco().getClientes().remove(cliente);
            data.getListaClientes().remove(cliente);
            mostrarMensaje("Cliente eliminado.");
            limpiarCampos();
        } else {
            mostrarMensaje("Cliente no encontrado.");
        }
    }

    private Cliente buscarCliente(String id) {
        for (Cliente c : data.getBanco().getClientes()) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null;
    }

    private void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(mensaje);
        alert.show();
    }

    private void limpiarCampos() {
        txtId.clear();
        txtNombre.clear();
        txtUsuario.clear();
        txtContrasena.clear();
        comboTipoCuenta.getSelectionModel().clearSelection();
        txtSaldoInicial.clear();
        txtTasaInteres.clear();
        txtLimiteSobregiro.clear();
    }

    @FXML
    void volver(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/poo/pfbanco/CajeroMenu.fxml"));
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }
}