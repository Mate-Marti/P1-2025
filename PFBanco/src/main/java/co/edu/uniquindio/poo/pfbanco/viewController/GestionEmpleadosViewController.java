package co.edu.uniquindio.poo.pfbanco.viewController;

import co.edu.uniquindio.poo.pfbanco.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class GestionEmpleadosViewController {

    @FXML private TableView<Cajero> tablaCajeros;
    @FXML private TableColumn<Cajero, String> colId;
    @FXML private TableColumn<Cajero, String> colNombre;
    @FXML private TableColumn<Cajero, String> colUsuario;
    @FXML private TableColumn<Cajero, String> colContrasena;

    @FXML private TextField txtId;
    @FXML private TextField txtNombre;
    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtContrasena;

    @FXML private Button btnCrear;
    @FXML private Button btnEliminar;

    private BancoData bancoData = BancoData.getInstancia();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        colContrasena.setCellValueFactory(new PropertyValueFactory<>("contraseña"));
        tablaCajeros.setItems(bancoData.getListaCajeros());

        btnCrear.setOnAction(e -> crearCajero());
        btnEliminar.setOnAction(e -> eliminarCajero());
    }

    private void crearCajero() {
        String id = txtId.getText();
        String nombre = txtNombre.getText();
        String usuario = txtUsuario.getText();
        String contraseña = txtContrasena.getText();

        if (id.isEmpty() || nombre.isEmpty() || usuario.isEmpty() || contraseña.isEmpty()) {
            mostrar("Complete todos los campos", Alert.AlertType.WARNING);
            return;
        }

        Cajero cajero = new Cajero(id, nombre, usuario, contraseña);
        bancoData.registrarCajero(cajero);

        limpiarCampos();
    }

    private void eliminarCajero() {
        Cajero seleccionado = tablaCajeros.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            bancoData.eliminarCajero(seleccionado);
        }
    }

    private void limpiarCampos() {
        txtId.clear();
        txtNombre.clear();
        txtUsuario.clear();
        txtContrasena.clear();
    }

    private void mostrar(String msg, Alert.AlertType tipo) {
        Alert a = new Alert(tipo, msg);
        a.show();
    }
    @FXML
    void volver(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/pfbanco/AdministradorMenu.fxml"));
        Parent root = loader.load();

        stage.setScene(new Scene(root));
    }

}
