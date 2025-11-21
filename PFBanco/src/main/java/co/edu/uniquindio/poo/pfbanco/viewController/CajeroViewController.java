package co.edu.uniquindio.poo.pfbanco.viewController;

import co.edu.uniquindio.poo.pfbanco.model.BancoData;
import co.edu.uniquindio.poo.pfbanco.model.Cajero;
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

    private BancoData bancoData = BancoData.getInstancia();


    @FXML
    void abrirCajeroMenu(ActionEvent event) {

        String usuarioIngresado = txtUsuarioCajero.getText().trim();
        String contrasenaIngresada = txtContrasenaCajero.getText().trim();

        Cajero cajero = bancoData.buscarCajeroPorUsuario(usuarioIngresado);

        if (cajero != null && cajero.getContrasena().equals(contrasenaIngresada)) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/pfbanco/CajeroMenu.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) btnEntrar.getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (Exception e) {
                mostrarAlerta("Error", "No se pudo cargar la vista del cajero.", Alert.AlertType.ERROR);
            }
        } else {
            mostrarAlerta("Acceso denegado", "Usuario o contraseña incorrectos." , Alert.AlertType.ERROR);
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