package co.edu.uniquindio.poo.biblioteca.viewController;

import co.edu.uniquindio.poo.biblioteca.model.EstadoLibro;
import co.edu.uniquindio.poo.biblioteca.model.Genero;
import co.edu.uniquindio.poo.biblioteca.model.Libro;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PrestamosRegistrosViewController {

    @FXML private TableView<Libro> TvPrestamosRegistros;
    @FXML private TableColumn<Libro, String> TbNombre;
    @FXML private TableColumn<Libro, String> TbCedula;
    @FXML private TableColumn<Libro, String> TbTitulo;
    @FXML private TableColumn<Libro, String> TbAutor;
@FXML private TableColumn<Libro, String> TbEditorial;
@FXML private TableColumn<Libro, String> TbDisponibilidad;
@FXML private TableColumn<Libro, String> TbAño;
    @FXML private void OnVolver(ActionEvent event) {
        cambiarVista("/co/edu/uniquindio/poo/biblioteca/BibliotecarioPantalla.fxml", event);;
    };
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

    public void setListaLibros(ObservableList<Libro> prestamos) {
        // Evitar duplicados
        for (Libro libro : prestamos) {
            if (!listaLibros.contains(libro)) {
                listaLibros.add(libro);
            }
        }

        // Refrescar tabla
        TvPrestamosRegistros.setItems(listaLibros);

    }
    private ObservableList<Libro> listaLibros = FXCollections.observableArrayList();


    @FXML
    public void initialize() {
        // Configurar columnas

        TbTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        TbAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        TbEditorial.setCellValueFactory(new PropertyValueFactory<>("editorial"));
        TbDisponibilidad.setCellValueFactory(new PropertyValueFactory<>("EstadoLibro"));
        TbAño.setCellValueFactory(new PropertyValueFactory<>("anoPublicacion")); // Asegúrate del nombre exacto

        // Agregar libros quemados una sola vez
        listaLibros.addAll(
                new Libro("Cien Años de Soledad", "Gabriel García Márquez", "Sudamericana", "1967", Genero.CIENCIA_FICCION, EstadoLibro.DISPONIBLE),
                new Libro("Neuromante", "William Gibson", "Ace Books", "1984", Genero.CIENCIA_FICCION, EstadoLibro.DISPONIBLE),
                new Libro("El Origen de las Especies", "Charles Darwin", "John Murray", "1859", Genero.POLICIAL, EstadoLibro.DISPONIBLE)
        );

        TvPrestamosRegistros.setItems(listaLibros);
    }






    private String nombreUsuario;

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
        mostrarNombreEnColumna();
    }
    private void mostrarNombreEnColumna() {
        TbNombre.setCellValueFactory(cellData -> new SimpleStringProperty(nombreUsuario));
    }
    private String cedulaUsuario;

    public void setCedulaUsuario(String cedulaUsuario) {
        this.cedulaUsuario = cedulaUsuario;
        mostrarCedulaEnColumna();
    }

    private void mostrarCedulaEnColumna() {
        TbCedula.setCellValueFactory(cellData -> new SimpleStringProperty(cedulaUsuario));
    }

}
