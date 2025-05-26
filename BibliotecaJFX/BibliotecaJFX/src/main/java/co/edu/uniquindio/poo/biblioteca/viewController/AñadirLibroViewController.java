package co.edu.uniquindio.poo.biblioteca.viewController;

import co.edu.uniquindio.poo.biblioteca.model.EstadoLibro;
import co.edu.uniquindio.poo.biblioteca.model.Genero;

import co.edu.uniquindio.poo.biblioteca.model.Libro;
import co.edu.uniquindio.poo.biblioteca.viewController.PrestamosViewController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class AñadirLibroViewController {

    @FXML
    private ComboBox<Genero> CboxGenero;
    @FXML private ComboBox<EstadoLibro> CboxEstado;
    @FXML private TextField TxFNombre;
    @FXML private TextField TxFAutor;
    @FXML private TextField TxFEditorial;
    @FXML private TextField TxFAñoPubli;
    @FXML private Button BtnGuardar;
    @FXML private Button BtnVolver;
    private ObservableList<Libro> listaLibros = FXCollections.observableArrayList();

    // Ya definida en tu código
    private FilteredList<Libro> librosFiltrados;

    @FXML void initialize() {
        CboxGenero.setItems(FXCollections.observableArrayList(Genero.values()));
        CboxEstado.setItems(FXCollections.observableArrayList(EstadoLibro.values()));
    }


    @FXML
    private void OnGenero() {
        Genero generoSeleccionado = CboxGenero.getValue();

        System.out.println("Género seleccionado: " + generoSeleccionado);


        }
    @FXML
    private void OnEstado() {
        EstadoLibro estadoSeleccionado = CboxEstado.getValue();

        System.out.println("Género seleccionado: " + estadoSeleccionado);


    }
    @FXML
    private void OnGuardarLibro() {
        String titulo = TxFNombre.getText();
        String autor = TxFAutor.getText();
        String editorial = TxFEditorial.getText();
        String anio = TxFAñoPubli.getText();
        Genero genero = CboxGenero.getValue();
        EstadoLibro estado = CboxEstado.getValue();

        if (titulo.isEmpty() || autor.isEmpty() || editorial.isEmpty() || anio.isEmpty() || genero == null || estado == null) {
            System.out.println("Por favor complete todos los campos.");
            return;
        }

        Libro nuevoLibro = new Libro(titulo, autor, editorial, anio, genero, estado);
        listaLibros.add(nuevoLibro); // Se conecta directamente a la tabla a través de librosFiltrados

        System.out.println("Libro agregado: " + nuevoLibro);

        // Limpiar los campos
        TxFNombre.clear();
        TxFAutor.clear();
        TxFEditorial.clear();
        TxFAñoPubli.clear();
        CboxGenero.setValue(null);
        CboxEstado.setValue(null);
    }
    @FXML void OnVolver(ActionEvent event) {
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
    }

