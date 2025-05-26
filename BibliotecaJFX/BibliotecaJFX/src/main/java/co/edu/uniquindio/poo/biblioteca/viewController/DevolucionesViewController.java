package co.edu.uniquindio.poo.biblioteca.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class DevolucionesViewController {

    @FXML private Button BtbDevolverLibro;
    @FXML private Button BtbVolver;

    @FXML private void OnVolver(ActionEvent event) {
        cambiarVista("/co/edu/uniquindio/poo/biblioteca/crudBiblioteca.fxml", event);
    }
    @FXML private void OnDevolver(ActionEvent event) {

    }
    @FXML
    private void cambiarVista(String rutaFXML, ActionEvent event) {
        try {
            URL fxmlLocation = getClass().getResource(rutaFXML);
            if (fxmlLocation == null) {
                System.err.println("No se encontró el archivo FXML en la ruta: " + rutaFXML);
                return;
            }
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
