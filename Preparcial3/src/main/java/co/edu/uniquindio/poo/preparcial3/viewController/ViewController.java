package co.edu.uniquindio.poo.preparcial3.viewController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;

public class ViewController {

    @FXML private StackPane contenido;
    @FXML private Button btnClientes, btnVentas, btnRegistro;

    @FXML
    public void initialize() {
        cargarVista("Clientes.fxml");

        btnClientes.setOnAction(e -> cargarVista("Clientes.fxml"));
        btnVentas.setOnAction(e -> cargarVista("Ventas.fxml"));
        btnRegistro.setOnAction(e -> cargarVista("Registro.fxml"));
    }

    private void cargarVista(String nombre) {
        try {
            Node nodo = FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/poo/preparcial3/" + nombre));
            contenido.getChildren().setAll(nodo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}