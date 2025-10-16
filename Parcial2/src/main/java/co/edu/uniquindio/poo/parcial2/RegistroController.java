package co.edu.uniquindio.poo.parcial2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;

public class RegistroController {

    @FXML
    private TableView<Inmueble> tablaInmuebles;

    @FXML
    private TableColumn<Inmueble, String> colTipo;

    @FXML
    private TableColumn<Inmueble, String> colCiudad;

    @FXML
    private TableColumn<Inmueble, String> colNHabitaciones;

    @FXML
    private TableColumn<Inmueble, String> colNPisos;

    @FXML
    private TableColumn<Inmueble, String> colPrecio;

    public static ObservableList<Inmueble> listaInmuebles = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        colNHabitaciones.setCellValueFactory(new PropertyValueFactory<>("nHabitaciones"));
        colNPisos.setCellValueFactory(new PropertyValueFactory<>("nPisos"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        tablaInmuebles.setItems(listaInmuebles);
    }

    @FXML
    void eliminarInmueble() {
        Inmueble seleccionado = tablaInmuebles.getSelectionModel().getSelectedItem();

        if (seleccionado != null) {
            listaInmuebles.remove(seleccionado);
        }
    }

    @FXML
    public void verDatos(){
        for (Inmueble Inmueble : listaInmuebles) {
            System.out.println(listaInmuebles.toString());
        }
    }

    @FXML
    void volver() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/parcial2/View.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) tablaInmuebles.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
}
