package co.edu.uniquindio.poo.pfbanco.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CajeroMenuViewController {

    @FXML
    private Label lblTituloCajeroMenu;

    @FXML
    void abrirRegistrarCliente(ActionEvent event) throws IOException {
        cambiarVista("/co/edu/uniquindio/poo/pfbanco/RegistroCliente.fxml", event);
    }
    @FXML
    void abrirListaCliente(ActionEvent event) throws IOException {
        cambiarVista("/co/edu/uniquindio/poo/pfbanco/ListaClientes.fxml", event);
    }
    @FXML
    void abrirGenerarReporte(ActionEvent event) throws IOException {
        cambiarVista("/co/edu/uniquindio/poo/pfbanco/GenerarReporte.fxml", event);
    }
    @FXML
    void abrirDepositoyRetiro(ActionEvent event) throws IOException {
        cambiarVista("/co/edu/uniquindio/poo/pfbanco/DepositoyRetiro.fxml", event);
    }
    @FXML
    void abrirTransferencia(ActionEvent event) throws IOException {
        cambiarVista("/co/edu/uniquindio/poo/pfbanco/Transferencia.fxml", event);
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
        Stage stage = (Stage) lblTituloCajeroMenu.getScene().getWindow();
        stage.setScene(new Scene(root));
    }


}
