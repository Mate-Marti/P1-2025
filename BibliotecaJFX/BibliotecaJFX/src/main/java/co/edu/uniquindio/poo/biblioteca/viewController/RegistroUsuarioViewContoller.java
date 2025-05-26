package co.edu.uniquindio.poo.biblioteca.viewController;

import co.edu.uniquindio.poo.biblioteca.model.Biblioteca;
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

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

public class RegistroUsuarioViewContoller {

    @FXML
    private TextField TxFNombre;
    @FXML
    private TextField TxFCedula;
    @FXML
    private TableView<Persona> TvPersona;
    @FXML
    private TableColumn<Persona, String> TbCNombre;
    @FXML
    private TableColumn<Persona, String> TbCCedula;
    @FXML
    private Button BtbAgregarPersona;
    @FXML
    private Button BtbVolver;
    @FXML
    private Button BtbActualizar;
    @FXML
    private Button BtbEliminar;

    private ObservableList<Persona> listaPersonas = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Asociar columnas con los campos de Persona
        TbCNombre.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNombre()));
        TbCCedula.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCedula()));


        // Asociar lista con tabla
        TvPersona.setItems(listaPersonas);
    }

    @FXML
    private void OnAgregarPersona() {
        String nombre = TxFNombre.getText();
        String cedula = TxFCedula.getText();


        if (!nombre.isEmpty() && !cedula.isEmpty()) {
            Persona persona = new Persona(nombre, cedula);
            listaPersonas.add(persona);

            // Limpiar campos
            TxFNombre.clear();
            TxFCedula.clear();

        } else {
            // Puedes mostrar una alerta aquí si lo deseas
            System.out.println("Todos los campos son obligatorios.");
        }
    }

    private void guardarPersonaEnArchivo(Persona persona) {
        try (FileWriter writer = new FileWriter("personas.txt", true)) {
            writer.write(persona.getNombre() + "," + persona.getCedula());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void OnVolver(ActionEvent event) {
        cambiarVista("/co/edu/uniquindio/poo/biblioteca/BibliotecarioPantalla.fxml", event);
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

    @FXML
    private void OnActualizar(ActionEvent event) {
        Persona seleccionado = TvPersona.getSelectionModel().getSelectedItem();

        if (seleccionado != null) {
            seleccionado.setNombre(TxFNombre.getText());
            seleccionado.setCedula(TxFCedula.getText());


            TvPersona.refresh(); // Refresca la tabla para mostrar los cambios

        }

    }

    @FXML
    void OnEliminar(ActionEvent event) {
        Persona seleccionado = TvPersona.getSelectionModel().getSelectedItem();

        if (seleccionado != null) {
            listaPersonas.remove(seleccionado);

        }
    }
}