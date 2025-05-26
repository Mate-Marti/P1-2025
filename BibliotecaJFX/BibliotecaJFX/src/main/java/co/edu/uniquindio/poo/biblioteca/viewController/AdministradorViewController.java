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

public class AdministradorViewController {


    @FXML private Label LblBievendioA;
    @FXML Button BtbGestionDeEmpleados;
    @FXML Button BtbSeguridadAutentificacion;
    @FXML Button BtbManejoAutentificacion;
    @FXML Button BtbReportes;
    @FXML Button BtbVolver;

    @FXML void OnGestionEmpleados(ActionEvent event) {
        cambiarVista("/co/edu/uniquindio/poo/biblioteca/GestionEmpleados.fxml", event);
    }
    @FXML void OnSeguridadAutenticacion(ActionEvent event) {
        cambiarVista("/co/edu/uniquindio/poo/biblioteca/Seguridad.fxml", event);
    }
    @FXML void OnManejoAutenticacion(ActionEvent event) {
        cambiarVista("/co/edu/uniquindio/poo/biblioteca/Registro.fxml", event);
    }
    @FXML void OnReportes(ActionEvent event) {
        cambiarVista("/co/edu/uniquindio/poo/biblioteca/Reporte.fxml", event);
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
