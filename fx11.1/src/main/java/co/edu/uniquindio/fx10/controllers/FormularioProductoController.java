package co.edu.uniquindio.fx10.controllers;

import co.edu.uniquindio.fx10.App;
import co.edu.uniquindio.fx10.models.Producto;
import co.edu.uniquindio.fx10.repositories.ProductoRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Controlador para el formulario de creación de productos
 */
public class FormularioProductoController {

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtPrecio;

    @FXML
    private TextField txtStock;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnCancelar;

    private ProductoRepository productoRepository;
    private DashboardController dashboardController; // Referencia al controlador principal

    @FXML
    public void initialize() {
        productoRepository = ProductoRepository.getInstancia();
    }

    /**
     * Establece el controlador del dashboard para poder regresar
     */
    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
        // Ya no necesitamos el contenedorPrincipal aquí, la navegación la maneja el DashboardController.
    }

    /**
     * Maneja el evento de guardar producto
     */
    @FXML
    private void onGuardarProducto() {
        if (!validarCampos()) {
            return;
        }

        try {
            String codigo = txtCodigo.getText().trim();
            String nombre = txtNombre.getText().trim();
            String descripcion = txtDescripcion.getText().trim();
            double precio = Double.parseDouble(txtPrecio.getText().trim());
            int stock = Integer.parseInt(txtStock.getText().trim());

            if (productoRepository.buscarPorCodigo(codigo) != null) {
                mostrarAlerta("Error", "Ya existe un producto con ese código", Alert.AlertType.ERROR);
                return;
            }

            Producto nuevoProducto = new Producto(codigo, nombre, descripcion, precio, stock);
            productoRepository.agregarProducto(nuevoProducto);

            mostrarAlerta("Éxito", "Producto creado correctamente", Alert.AlertType.INFORMATION);

            // Volver al dashboard usando el nuevo método centralizado
            volverAlDashboard();

        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "El precio y stock deben ser valores numéricos válidos", Alert.AlertType.ERROR);
        }
    }

    /**
     * Maneja el evento de cancelar
     */
    @FXML
    private void onCancelar() {
        volverAlDashboard();
    }

    /**
     * Vuelve a mostrar la vista de listado
     */
    private void volverAlDashboard() {
        // El DashboardController sabe cómo restaurar la vista de listado
        dashboardController.restaurarVistaListado();
    }

    private boolean validarCampos() {
        // ... (Tu lógica de validación sin cambios)
        if (txtCodigo.getText().trim().isEmpty() || txtNombre.getText().trim().isEmpty() ||
                txtDescripcion.getText().trim().isEmpty() || txtPrecio.getText().trim().isEmpty() ||
                txtStock.getText().trim().isEmpty()) {
            mostrarAlerta("Error de validación", "Todos los campos son obligatorios", Alert.AlertType.WARNING);
            return false;
        }
        return true;
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}