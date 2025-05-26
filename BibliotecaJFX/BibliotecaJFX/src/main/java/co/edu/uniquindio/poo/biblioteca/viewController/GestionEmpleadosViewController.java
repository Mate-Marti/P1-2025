package co.edu.uniquindio.poo.biblioteca.viewController;

import co.edu.uniquindio.poo.biblioteca.model.Persona;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class GestionEmpleadosViewController {

    @FXML
    private Button BtbAgregar;
    @FXML
    private Button BtbVolver;
    @FXML
    private TextField TxfNombre;
    @FXML private TextField TxfCedula;

    @FXML private TableView<Persona> TvEmpleado;
    @FXML private TableColumn<Persona, String> TbcNombre;
    @FXML private TableColumn<Persona, String> TbcCedula;
    @FXML Button BtbActualizar;
    @FXML Button BtbEliminar;


    @FXML private void OnVolver(ActionEvent event) {
        cambiarVista("/co/edu/uniquindio/poo/biblioteca/AdministradorPantalla.fxml", event);
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
    private ObservableList<Persona> listaPersonas = FXCollections.observableArrayList();
    @FXML
    public void initialize() {
        // Asociar columnas con los campos de Persona
        TbcNombre.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNombre()));
        TbcCedula.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCedula()));


        // Asociar lista con tabla
        TvEmpleado.setItems(listaPersonas);
    }

    @FXML
    private void OnAgregar() {
        String nombre = TxfNombre.getText();
        String cedula = TxfCedula.getText();



        if (!nombre.isEmpty() && !cedula.isEmpty()) {
            Persona persona = new Persona(nombre, cedula);
            listaPersonas.add(persona);

            // Limpiar campos
            TxfNombre.clear();
            TxfCedula.clear();

        } else {
            // Puedes mostrar una alerta aquí si lo deseas
            System.out.println("Todos los campos son obligatorios.");
        }
    }
    @FXML private void OnActualizar(ActionEvent event) {
        Persona seleccionado = TvEmpleado.getSelectionModel().getSelectedItem();

        if (seleccionado != null) {
            seleccionado.setNombre(TxfNombre.getText());
            seleccionado.setCedula(TxfCedula.getText());


            TvEmpleado.refresh(); // Refresca la tabla para mostrar los cambios

 }

}
    @FXML
    void OnEliminar(ActionEvent event) {
        Persona seleccionado = TvEmpleado.getSelectionModel().getSelectedItem();

        if (seleccionado != null) {
            listaPersonas.remove(seleccionado);

        }
    }
}
