package co.edu.uniquindio.poo.pfbanco.viewController;

import co.edu.uniquindio.poo.pfbanco.model.BancoData;
import co.edu.uniquindio.poo.pfbanco.model.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class ListaClientesViewController {

    @FXML private TableView<Cliente> tablaClientes;
    @FXML private TableColumn<Cliente, String> colId;
    @FXML private TableColumn<Cliente, String> colNombre;
    @FXML private TableColumn<Cliente, String> colUsuario;
    @FXML private TableColumn<Cliente, String> colContrasena;
    @FXML private TableColumn<Cliente, String> colSaldo;

    private BancoData data = BancoData.getInstancia();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getId()));
        colNombre.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNombre()));
        colUsuario.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getUsuario()));
        colContrasena.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getContraseña()));

        colSaldo.setCellValueFactory(c -> {
            if (c.getValue().getCuentas() != null && !c.getValue().getCuentas().isEmpty()) {
                double saldo = c.getValue().getCuentas().get(0).consultarSaldo();
                return new SimpleStringProperty(String.format("%,.2f", saldo));
            }
            return new SimpleStringProperty("Sin Cuenta");
        });

        tablaClientes.setItems(data.getListaClientes());
    }

    @FXML
    void volver(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/pfbanco/CajeroMenu.fxml"));
        Parent root = loader.load();

        stage.setScene(new Scene(root));
    }
}