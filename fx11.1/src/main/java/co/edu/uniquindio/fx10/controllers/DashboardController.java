package co.edu.uniquindio.fx10.controllers;

import co.edu.uniquindio.fx10.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Controlador principal que maneja la navegación de las vistas (Listado y Formulario).
 */
public class DashboardController {

    @FXML
    private VBox contenedorPrincipal; // El VBox raíz de Dashboard.fxml

    @FXML
    private VBox contentPane; // El VBox donde se cargan las vistas dinámicas (Tabla o Formulario)

    @FXML
    private Button btnCrearProducto;

    private Parent listadoView; // Para guardar la vista de listado y restaurarla rápidamente.
    private ListadoProductoController listadoController; // Referencia al controlador de la tabla.

    @FXML
    public void initialize() {
        // Al iniciar, cargar el listado de productos
        cargarListadoProducto();
    }

    /**
     * Carga la vista de ListadoProducto.fxml en el contentPane
     */
    public void cargarListadoProducto() {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("/co/edu/uniquindio/fx10/vista/ListadoProducto.fxml"));
            listadoView = loader.load();
            listadoController = loader.getController();

            // Pasamos una referencia a este DashboardController
            listadoController.setDashboardController(this);

            // Mostrar la vista de listado
            contentPane.getChildren().clear();
            contentPane.getChildren().add(listadoView);

        } catch (IOException e) {
            mostrarAlerta("Error de carga", "No se pudo cargar la vista de Listado de Productos.", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    /**
     * Navega al formulario de creación de producto
     */
    @FXML
    private void onCrearProducto() {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("/co/edu/uniquindio/fx10/vista/FormularioProducto.fxml"));
            Parent formulario = loader.load();

            FormularioProductoController controller = loader.getController();
            controller.setDashboardController(this); // Pasamos este controlador para que pueda regresar.

            // Reemplazar el contenido del contentPane por el formulario
            contentPane.getChildren().clear();
            contentPane.getChildren().add(formulario);

        } catch (IOException e) {
            mostrarAlerta("Error de carga", "No se pudo cargar el formulario de producto.", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    /**
     * Vuelve a la vista de listado y refresca los datos.
     * Llamado desde FormularioProductoController.
     */
    public void restaurarVistaListado() {
        contentPane.getChildren().clear();
        contentPane.getChildren().add(listadoView);
        if (listadoController != null) {
            listadoController.cargarProductos(); // Refrescar los datos de la tabla
        }
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    // Método para acceder al contentPane (aunque con el nuevo diseño ya no es necesario)
    public VBox getContentPane() {
        return contentPane;
    }
}