package co.edu.uniquindio.poo.pfbanco.viewController;

import co.edu.uniquindio.poo.pfbanco.model.BancoData;
import co.edu.uniquindio.poo.pfbanco.model.Cliente;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class GenerarReporteViewController {

    @FXML
    private TableView<Cliente> tablaClientes;
    @FXML
    private TableColumn<Cliente, String> colId;
    @FXML
    private TableColumn<Cliente, String> colNombre;
    @FXML
    private TableColumn<Cliente, String> colUsuario;
    @FXML
    private TableColumn<Cliente, String> colReporte;

    @FXML
    private TextArea txtReporte;

    private BancoData data = BancoData.getInstancia();

    @FXML
    public void initialize() {

        colId.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getId()));
        colNombre.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getNombre()));
        colUsuario.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getUsuario()));
        colReporte.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getReporte()));

        tablaClientes.setItems(data.getListaClientes());

        tablaClientes.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                txtReporte.setText(newSel.getReporte());
            }
        });
    }

    @FXML
    private void guardarReporte() {

        Cliente cliente = tablaClientes.getSelectionModel().getSelectedItem();

        if (cliente == null) {
            mostrarAlerta("Seleccione un cliente primero");
            return;
        }

        cliente.setReporte(txtReporte.getText());
        tablaClientes.refresh();

        mostrarAlerta("Reporte guardado correctamente.");
    }

    @FXML
    private void volver() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/poo/pfbanco/CajeroMenu.fxml"));
        Stage stage = (Stage) tablaClientes.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}