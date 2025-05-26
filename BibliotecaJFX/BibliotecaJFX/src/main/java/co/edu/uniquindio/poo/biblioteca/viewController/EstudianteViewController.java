package co.edu.uniquindio.poo.biblioteca.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class EstudianteViewController {

    @FXML Button BtbPrestamos;
    @FXML Button BtbDevoluciones;
    @FXML Button BtbRegistro;
    @FXML Label LblBienvenidoEstu;
    @FXML Button BtbVolver;

    @FXML void OnPrestamos(ActionEvent event) {
        cambiarVista("/co/edu/uniquindio/poo/biblioteca/Prestamos.fxml", event);
    }
    @FXML void OnDevoluciones(ActionEvent event) {
        cambiarVista("/co/edu/uniquindio/poo/biblioteca/Devoluciones.fxml", event);
    }
    @FXML void OnRegistro(ActionEvent event) {
        cambiarVista("/co/edu/uniquindio/poo/biblioteca/Registro.fxml", event);
    }
    @FXML void OnVolver(ActionEvent event) {
        cambiarVista("/co/edu/uniquindio/poo/biblioteca/crudBiblioteca.fxml", event);
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
