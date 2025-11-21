package co.edu.uniquindio.poo.pfbanco.viewController;

import co.edu.uniquindio.poo.pfbanco.model.BancoData;
import co.edu.uniquindio.poo.pfbanco.model.Cuentabanco;
import co.edu.uniquindio.poo.pfbanco.model.Deposito;
import co.edu.uniquindio.poo.pfbanco.model.Retiro;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class DepositoyRetiroViewController {

    @FXML private TableView<Cuentabanco> tablaCuentas;
    @FXML private TableColumn<Cuentabanco, String> colNumCuenta;
    @FXML private TableColumn<Cuentabanco, String> colPropietario;
    @FXML private TableColumn<Cuentabanco, String> colSaldoActual;

    @FXML private TextField txtMonto;
    @FXML private Label lblMensaje;
    @FXML private Label lblTitulo;

    private BancoData data = BancoData.getInstancia();

    @FXML
    public void initialize() {

        colNumCuenta.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNumeroCuenta()));

        colPropietario.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTitular().getNombre()));

        colSaldoActual.setCellValueFactory(c -> {
            double saldo = c.getValue().consultarSaldo();
            return new SimpleStringProperty(String.format("%,.2f", saldo));
        });

        tablaCuentas.getItems().addAll(data.getBanco().getCuentas());
    }


    @FXML
    void depositarAction(ActionEvent event) {
        realizarTransaccion(true);
    }

    @FXML
    void retirarAction(ActionEvent event) {
        realizarTransaccion(false);
    }

    private void realizarTransaccion(boolean esDeposito) {
        lblMensaje.setText("");

        Cuentabanco cuenta = tablaCuentas.getSelectionModel().getSelectedItem();

        if (cuenta == null) {
            lblMensaje.setText("Debe seleccionar una cuenta de la tabla.");
            return;
        }

        String montoText = txtMonto.getText();
        if (montoText.isEmpty()) {
            lblMensaje.setText("Debe ingresar el monto.");
            return;
        }

        double monto;
        try {
            monto = Double.parseDouble(montoText);
            if (monto <= 0) {
                lblMensaje.setText("El monto debe ser un valor positivo.");
                return;
            }
        } catch (NumberFormatException e) {
            lblMensaje.setText("El monto ingresado no es un número válido.");
            return;
        }

        try {
            if (esDeposito) {
                new Deposito(cuenta, monto);
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Depósito realizado", "El depósito de $" + monto + " se realizó con éxito.\nNuevo saldo: $" + cuenta.consultarSaldo());
            } else {
                new Retiro(cuenta, monto);
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Retiro realizado", "El retiro de $" + monto + " se realizó con éxito.\nNuevo saldo: $" + cuenta.consultarSaldo());
            }

            tablaCuentas.refresh();
            txtMonto.clear();

        } catch (RuntimeException e) {
            lblMensaje.setText("Error de Operación: " + e.getMessage());
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String encabezado, String contenido) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(encabezado);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    @FXML
    void volverAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) lblTitulo.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/pfbanco/CajeroMenu.fxml"));
        Parent root = loader.load();

        stage.setScene(new Scene(root));
    }
}