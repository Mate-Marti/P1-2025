package co.edu.uniquindio.poo.biblioteca.viewController;

import co.edu.uniquindio.poo.biblioteca.model.EstadoLibro;
import co.edu.uniquindio.poo.biblioteca.model.Genero;
import co.edu.uniquindio.poo.biblioteca.model.Libro;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;


import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PrestamosViewController {
    @FXML
    private ComboBox<Genero> CboxGenero;

    @FXML
    private TableView<Libro> TvLibros;

    @FXML
    private TableColumn<Libro, String> TbLibro;
    @FXML
    private TableColumn<Libro, String> TbTitulo;

    @FXML private TableColumn<Libro, String> TbAutor;
    @FXML private TableColumn<Libro, String> TbAño;
    @FXML private TableColumn<Libro, String> TbDisponibilidad;
    @FXML private TableColumn<Libro, String> TbTiempo;
    @FXML private Button BtbPrestar;
    @FXML private Button BtbVolver;
    @FXML private Button BtbRegistro;
    @FXML private TextField TxfNombre;
    @FXML private TextField TxfCedula;


    @FXML
    public void initialize() {
        // Configurar el ComboBox con los géneros
        CboxGenero.setItems(FXCollections.observableArrayList(Genero.values()));

        // Configurar las columnas de la tabla

        TbTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        TbAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        TbAño.setCellValueFactory(new PropertyValueFactory<>("editorial"));
        TbDisponibilidad.setCellValueFactory(new PropertyValueFactory<>("EstadoLibro"));
        TbTiempo.setCellValueFactory(new PropertyValueFactory<>("anoPublicacion"));
        // Simular carga de libros
        listaLibros.addAll(
                new Libro("Cien Años de Soledad", "Gabriel García Márquez", "Sudamericana", "1967", Genero.CIENCIA_FICCION, EstadoLibro.DISPONIBLE),
                new Libro("Neuromante", "William Gibson", "Ace Books", "1984", Genero.DRAMATICO, EstadoLibro.DISPONIBLE),
                new Libro("El Origen de las Especies", "Charles Darwin", "John Murray", "1859", Genero.POLICIAL, EstadoLibro.DISPONIBLE)
        );

        // Inicializar la lista filtrada
        librosFiltrados = new FilteredList<>(listaLibros, p -> true);

        // Conectar la lista filtrada a la tabla
        TvLibros.setItems(librosFiltrados);
    }

    @FXML
    private void OnGenero() {
        Genero generoSeleccionado = CboxGenero.getValue();

        System.out.println("Género seleccionado: " + generoSeleccionado);

        if (generoSeleccionado != null) {
            librosFiltrados.setPredicate(libro -> libro.getGenero() == generoSeleccionado);
        } else {
            librosFiltrados.setPredicate(libro -> true);
        }
    }
    private ObservableList<Libro> listaLibros = FXCollections.observableArrayList();
    private FilteredList<Libro> librosFiltrados;

    @FXML private void OnPrestar(ActionEvent event) {
        Libro libroSeleccionado = TvLibros.getSelectionModel().getSelectedItem();
        if (libroSeleccionado != null && !listaLibros.contains(libroSeleccionado)) {
            listaLibros.add(libroSeleccionado);

        }
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
    @FXML private void OnVolver(ActionEvent event) {
        cambiarVista("/co/edu/uniquindio/poo/biblioteca/crudBiblioteca.fxml", event);
    }

    public ObservableList<Libro> getListaLibros() {
        return listaLibros;
    }

    public void setListaLibros(ObservableList<Libro> listaLibros) {
        this.listaLibros = listaLibros;
    }
    @FXML
    private void OnRegistro(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/biblioteca/PrestamosRegistros.fxml"));
            Parent root = loader.load();

            PrestamosRegistrosViewController controller = loader.getController();

            // Obtener nombre y cédula desde los TextFields
            String nombreIngresado = TxfNombre.getText();
            String cedulaIngresada = TxfCedula.getText();

            // Pasarlos al controlador destino
            controller.setNombreUsuario(nombreIngresado);
            controller.setCedulaUsuario(cedulaIngresada);

            // Pasar libros seleccionados
            ObservableList<Libro> librosSeleccionados = FXCollections.observableArrayList();
            for (Libro libro : TvLibros.getItems()) {
                if (libro.getEstadoLibro() == EstadoLibro.DISPONIBLE) {
                    librosSeleccionados.add(libro);
                }
            }
            controller.setListaLibros(librosSeleccionados);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String nombreUsuario;

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

}
