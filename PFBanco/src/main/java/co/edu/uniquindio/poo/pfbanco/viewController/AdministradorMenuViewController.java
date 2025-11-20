package co.edu.uniquindio.poo.pfbanco.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class AdministradorMenuViewController {


    @FXML
    private Label lblTituloAdminMenu;

    @FXML
    void abrirGestionEmpleados(ActionEvent event) throws IOException {
        cambiarVista("/co/edu/uniquindio/poo/pfbanco/GestionEmpleados.fxml", event);
    }
    @FXML
    void abrirMonitoreoTransacciones(ActionEvent event) throws IOException {
        cambiarVista("/co/edu/uniquindio/poo/pfbanco/MonitoreoTransacciones.fxml", event);
    }
    @FXML
    void abrirAutenticacion(ActionEvent event) throws IOException {
        cambiarVista("/co/edu/uniquindio/poo/pfbanco/Autenticacion.fxml", event);
    }
    @FXML
    void abrirReportesAvanzados(ActionEvent event) throws IOException {
        cambiarVista("/co/edu/uniquindio/poo/pfbanco/ReportesAvanzados.fxml", event);
    }




    private void cambiarVista(String rutaFXML, ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFXML));
        Parent root = loader.load();
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void volver(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/pfbanco/View.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) lblTituloAdminMenu.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
}
