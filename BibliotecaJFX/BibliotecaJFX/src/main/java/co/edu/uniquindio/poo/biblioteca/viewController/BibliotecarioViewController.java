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

public class BibliotecarioViewController {

    @FXML
    Button BtbAgregarLibro;
    @FXML Button BtbRegistrarUsuarios;
    @FXML Button BtbPrestamosRegistros;
    @FXML Button BtbDevolucionesRegistros;
    @FXML Button BtbVolver;
    @FXML Button BtbReporte;

    @FXML Label LblBienvenidoBiblo;

    @FXML void OnAgregarLibro(ActionEvent event) {
        cambiarVista("/co/edu/uniquindio/poo/biblioteca/AñadirLibro.fxml", event);
    }
    @FXML void OnRegistrarUsuarios(ActionEvent event) {
        cambiarVista("/co/edu/uniquindio/poo/biblioteca/RegistroUsuarios.fxml", event);
    }
    @FXML void OnPrestamosRegistros(ActionEvent event) {
        cambiarVista("/co/edu/uniquindio/poo/biblioteca/PrestamosRegistros.fxml", event);
    }
    @FXML void OnDevolucionesRegistros(ActionEvent event) {
        cambiarVista("/co/edu/uniquindio/poo/biblioteca/DevolucionesRegistros.fxml", event);
    }
    @FXML void OnVolver(ActionEvent event) {
        cambiarVista("/co/edu/uniquindio/poo/biblioteca/crudBiblioteca.fxml", event);
    }
    @FXML void OnReporte(ActionEvent event) {
        cambiarVista("/co/edu/uniquindio/poo/biblioteca/Registro.fxml", event);
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
