package co.edu.uniquindio.poo.parcial2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class ViewController {

    @FXML
    void abrirFormulario(ActionEvent event) throws IOException {
        cambiarVista("/co/edu/uniquindio/poo/parcial2/Formulario.fxml", event);
    }

    @FXML
    void abrirRegistro(ActionEvent event) throws IOException {
        cambiarVista("/co/edu/uniquindio/poo/parcial2/Registro.fxml", event);
    }

    private void cambiarVista(String rutaFXML, ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFXML));
        Parent root = loader.load();
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }

}