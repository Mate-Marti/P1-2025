package co.edu.uniquindio.poo.preparcial3.viewController;


import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import co.edu.uniquindio.poo.preparcial3.model.Venta;
import co.edu.uniquindio.poo.preparcial3.model.Repositorio;

public class RegistroViewController {
    @FXML private TableView<Venta> tablaVentas;
    @FXML private TableColumn<Venta, String> colFecha, colCliente;
    @FXML private TableColumn<Venta, Double> colTotal;

    @FXML
    public void initialize(){
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colCliente.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getCliente().getNombre()));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        tablaVentas.setItems(Repositorio.getInstancia().getVentas());
    }
}
