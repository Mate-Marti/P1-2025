package co.edu.uniquindio.poo.biblioteca.viewController;

import java.io.IOException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import co.edu.uniquindio.poo.biblioteca.App;
import co.edu.uniquindio.poo.biblioteca.controller.BibliotecaController;
import co.edu.uniquindio.poo.biblioteca.model.Libro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;

public class BibliotecaViewController {

    BibliotecaController BibliotecaController;
    ObservableList<Libro> listLibro = FXCollections.observableArrayList();
    Libro selectedLibro;

    @FXML private Label LblNombre;
    @FXML private Label LblCedula;
    @FXML private Label LblBievenidos;
    @FXML private Label LblQueEres;
    @FXML private TextField txtNombre;
    @FXML private TextField txtCedula;
    @FXML private Button BtbProfesor;
    @FXML private Button BtbEstudiante;
    @FXML private Button BtbAdministrador;
    @FXML private Button BtbBibliotecario;
    @FXML private Button BtbVisitante;



    private App app;

    public void setApp(App app) {
        this.app = app;
    }

    @FXML
    private void OnProfesorPantalla(ActionEvent event) {
        cambiarVista("/co/edu/uniquindio/poo/biblioteca/ProfesorPantalla.fxml", event);
    }

    @FXML
    private void OnEstudiantePantalla(ActionEvent event) {
        cambiarVista("/co/edu/uniquindio/poo/biblioteca/EstudiantePantalla.fxml", event);
    }

    @FXML
    private void OnVisitantePantalla(ActionEvent event) {
        cambiarVista("/co/edu/uniquindio/poo/biblioteca/VisitantePantalla.fxml", event);
    }

    @FXML
    private void OnBibliotecarioPantalla(ActionEvent event) {
        cambiarVista("/co/edu/uniquindio/poo/biblioteca/BibliotecarioPantalla.fxml", event);
    }

    @FXML
    private void onAdministradorPantalla(ActionEvent event) {
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

    public TextField getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(TextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    public TextField getTxtCedula() {
        return txtCedula;
    }

    public void setTxtCedula(TextField txtCedula) {
        this.txtCedula = txtCedula;
    }

    @FXML
    public void initialize() {
        System.out.println("Vista de biblioteca cargada.");
    }
}

