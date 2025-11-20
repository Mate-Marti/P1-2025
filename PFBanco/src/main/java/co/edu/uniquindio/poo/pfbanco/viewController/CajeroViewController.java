package co.edu.uniquindio.poo.pfbanco.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CajeroViewController {

    @FXML
    private TextField txtUsuarioCajero;

    @FXML
    private PasswordField txtContrasenaCajero;

    @FXML
    private Button btnEntrar;

    @FXML
    private Button btnVolver;

    private final String USUARIO_CAJERO = "cajero";
    private final String PASS_CAJERO = "0000";

    @FXML
    void abrirCajeroMenu(ActionEvent event) {
        String usuario = txtUsuarioCajero.getText() != null ? txtUsuarioCajero.getText().trim() : "";
        String clave = txtContrasenaCajero.getText() != null ? txtContrasenaCajero.getText().trim() : "";

        if (usuario.equals(USUARIO_CAJERO) && clave.equals(PASS_CAJERO)) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/pfbanco/CajeroMenu.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) btnEntrar.getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                mostrarAlerta("Error", "No se pudo cargar la vista del cajero: " + e.getMessage(), Alert.AlertType.ERROR);
                e.printStackTrace();
            }
        } else {
            mostrarAlerta("Datos incorrectos", "Usuario o contraseña inválidos.", Alert.AlertType.WARNING);
        }
    }

    @FXML
    void volver(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/pfbanco/View.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) txtUsuarioCajero.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}