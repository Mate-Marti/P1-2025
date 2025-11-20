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

public class AdministradorViewController {

    @FXML
    private TextField txtUsuarioAdmin;

    @FXML
    private PasswordField txtContrasenaAdmin;

    @FXML
    private Button btnEntrar;

    @FXML
    private Button btnVolver;

    private final String USUARIO_ADMIN = "admin";
    private final String PASS_ADMIN = "1234";

    @FXML
    void abrirAdminMenu(ActionEvent event) {
        String usuario = txtUsuarioAdmin.getText() != null ? txtUsuarioAdmin.getText().trim() : "";
        String clave = txtContrasenaAdmin.getText() != null ? txtContrasenaAdmin.getText().trim() : "";

        if (usuario.equals(USUARIO_ADMIN) && clave.equals(PASS_ADMIN)) {
            // Correcto: abrir vista del menú de administrador
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/pfbanco/AdministradorMenu.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) btnEntrar.getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                mostrarAlerta("Error", "No se pudo cargar la vista del administrador: " + e.getMessage(), Alert.AlertType.ERROR);
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
        Stage stage = (Stage) txtUsuarioAdmin.getScene().getWindow();
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

