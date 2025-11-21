package co.edu.uniquindio.poo.pfbanco.viewController;

import co.edu.uniquindio.poo.pfbanco.model.BancoData;
import co.edu.uniquindio.poo.pfbanco.model.Cuentabanco;
import co.edu.uniquindio.poo.pfbanco.model.Transferencia;
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
import java.util.List;

public class TransferenciaViewController {

    @FXML private TableView<Cuentabanco> tablaOrigen;
    @FXML private TableColumn<Cuentabanco, String> colNumCuentaOrigen;
    @FXML private TableColumn<Cuentabanco, String> colPropietarioOrigen;
    @FXML private TableColumn<Cuentabanco, String> colSaldoOrigen;

    @FXML private TableView<Cuentabanco> tablaDestino;
    @FXML private TableColumn<Cuentabanco, String> colNumCuentaDestino;
    @FXML private TableColumn<Cuentabanco, String> colPropietarioDestino;
    @FXML private TableColumn<Cuentabanco, String> colSaldoDestino;

    @FXML private TextField txtMonto;
    @FXML private Label lblMensaje;
    @FXML private Label lblTitulo;

    private BancoData data = BancoData.getInstancia();

    @FXML
    public void initialize() {
        List<Cuentabanco> todasLasCuentas = data.getBanco().getCuentas();

        configurarColumnas(colNumCuentaOrigen, colPropietarioOrigen, colSaldoOrigen);
        tablaOrigen.getItems().addAll(todasLasCuentas);

        configurarColumnas(colNumCuentaDestino, colPropietarioDestino, colSaldoDestino);
        tablaDestino.getItems().addAll(todasLasCuentas);

        lblMensaje.setText("");
    }
    private void configurarColumnas(TableColumn<Cuentabanco, String> colNum,
                                    TableColumn<Cuentabanco, String> colProp,
                                    TableColumn<Cuentabanco, String> colSaldo) {

        colNum.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNumeroCuenta()));
        colProp.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTitular().getNombre()));
        colSaldo.setCellValueFactory(c -> {
            double saldo = c.getValue().consultarSaldo();
            return new SimpleStringProperty(String.format("%,.2f", saldo));
        });
    }

    @FXML
    void transferirAction(ActionEvent event) {
        lblMensaje.setText("");
        Cuentabanco cuentaOrigen = tablaOrigen.getSelectionModel().getSelectedItem();
        Cuentabanco cuentaDestino = tablaDestino.getSelectionModel().getSelectedItem();

        if (cuentaOrigen == null || cuentaDestino == null) {
            lblMensaje.setText("Debe seleccionar la cuenta de Origen y la de Destino.");
            return;
        }

        if (cuentaOrigen.equals(cuentaDestino)) {
            lblMensaje.setText("No puede transferir dinero a la misma cuenta.");
            return;
        }
        String montoText = txtMonto.getText();
        if (montoText.isEmpty()) {
            lblMensaje.setText("Debe ingresar el monto a transferir.");
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
            new Transferencia(cuentaOrigen, cuentaDestino, monto);

            mostrarAlerta(Alert.AlertType.INFORMATION,
                    "Éxito",
                    "Transferencia realizada",
                    "Transferencia de $" + monto + " exitosa.\nNuevo saldo Origen: $" + cuentaOrigen.consultarSaldo() + "\nNuevo saldo Destino: $" + cuentaDestino.consultarSaldo());

            tablaOrigen.refresh();
            tablaDestino.refresh();
            txtMonto.clear();

        } catch (RuntimeException e) {
            lblMensaje.setText("Error de Transferencia: " + e.getMessage());
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
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/pfbanco/CajeroMenu.fxml"));
        Parent root = loader.load();

        stage.setScene(new Scene(root));
    }
}