package co.edu.uniquindio.poo.parcial2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.control.ChoiceBox;

public class FormularioController {


    @FXML
    private ChoiceBox<String> choiceTipo;

    @FXML
    private TextField txtCiudad;

    @FXML
    private TextField txtNHabitaciones;

    @FXML
    private TextField txtNPisos;

    @FXML
    private TextField txtPrecio;



    @FXML
    public void initialize() {
        choiceTipo.getItems().addAll("casa" , "apartamento" , "finca" , "local");
    }

    @FXML
    void guardar(ActionEvent event) {
        String tipo = choiceTipo.getValue();
        String ciudad = txtCiudad.getText();
        String nHabitaciones = txtNHabitaciones.getText();
        String nPisos = txtNPisos.getText();
        String precio = txtPrecio.getText();


        if (tipo.isEmpty() || ciudad.isEmpty() || nHabitaciones.isEmpty() || nPisos.isEmpty() || precio.isEmpty()) {
            mostrarAlerta("Llene todos los campos, no sea bruto.");
        } else {
            RegistroController.listaInmuebles.add(new Inmueble(tipo, ciudad, nHabitaciones, nPisos, precio));
            mostrarAlerta("Inmueble registrado con éxito.");
            choiceTipo.setValue(null);
            txtCiudad.clear();
            txtNHabitaciones.clear();
            txtNPisos.clear();
            txtPrecio.clear();

        }
    }


    @FXML
    void volver(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/parcial2/View.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) choiceTipo.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
