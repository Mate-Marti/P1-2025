package co.edu.uniquindio.poo.preparcial3.viewController;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import co.edu.uniquindio.poo.preparcial3.model.Cliente;
import co.edu.uniquindio.poo.preparcial3.model.Producto;
import co.edu.uniquindio.poo.preparcial3.model.ItemVenta;
import co.edu.uniquindio.poo.preparcial3.model.Venta;
import co.edu.uniquindio.poo.preparcial3.model.Repositorio;


import java.util.Optional;
import javafx.scene.control.Alert.AlertType;


public class ClientesViewController {
    @FXML private TextField txtDocumento, txtNombre, txtTelefono, txtCorreo;
    @FXML private Button btnGuardar, btnActualizar, btnEliminar;
    @FXML private TableView<Cliente> tablaClientes;
    @FXML private TableColumn<Cliente, String> colDocumento, colNombre, colTelefono, colCorreo;

    @FXML
    public void initialize() {

        colDocumento.setCellValueFactory(new PropertyValueFactory<>("documento"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));

        tablaClientes.setItems(Repositorio.getInstancia().getClientes());


        tablaClientes.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                mostrarDatosCliente(newSelection);
            }
        });


        btnGuardar.setOnAction(e -> guardar());
        btnActualizar.setOnAction(e -> actualizar());
        btnEliminar.setOnAction(e -> eliminar());
    }

    private void guardar(){

        if(txtDocumento.isDisabled()){
            limpiar();
            return;
        }

        String documento = txtDocumento.getText();
        String nombre = txtNombre.getText();
        String telefono = txtTelefono.getText();
        String email = txtCorreo.getText();


        if(documento.isEmpty() || nombre.isEmpty() || telefono.isEmpty() || email.isEmpty()){
            mostrarAlerta("Campos Vacíos", "Datos incompletos", "Por favor, llene todos los campos.", AlertType.WARNING);
            return;
        }


        if (!esCorreoValido(email)) {
            mostrarAlerta("Error de Validación", "Formato de correo inválido", "El correo debe tener un formato válido (ej: juan@gmail.com).", AlertType.ERROR);
            return;
        }

        boolean duplicado = Repositorio.getInstancia().getClientes().stream()
                .anyMatch(c -> c.getDocumento().equals(documento));
        if(duplicado){
            mostrarAlerta("Error al Guardar", "Cliente duplicado", "Ya existe un cliente con ese documento.", AlertType.ERROR);
            return;
        }


        Cliente cliente = new Cliente(documento, nombre, telefono, email);
        Repositorio.getInstancia().getClientes().add(cliente);
        limpiar();
    }


    private void actualizar(){
        Cliente seleccionado = tablaClientes.getSelectionModel().getSelectedItem();


        if(seleccionado == null){
            mostrarAlerta("Error al Actualizar", "No ha seleccionado ningún cliente", "Por favor, seleccione un cliente de la tabla para actualizarlo.", AlertType.WARNING);
            return;
        }

        String nombre = txtNombre.getText();
        String telefono = txtTelefono.getText();
        String email = txtCorreo.getText();


        if(nombre.isEmpty() || telefono.isEmpty() || email.isEmpty()){
            mostrarAlerta("Campos Vacíos", "Datos incompletos", "Por favor, llene todos los campos.", AlertType.WARNING);
            return;
        }

        if (!esCorreoValido(email)) {
            mostrarAlerta("Error de Validación", "Formato de correo inválido", "El correo debe tener un formato válido (ej: juan@gmail.com).", AlertType.ERROR);
            return;
        }



        seleccionado.setNombre(nombre);
        seleccionado.setTelefono(telefono);
        seleccionado.setCorreo(email);


        tablaClientes.refresh();

        mostrarAlerta("Actualización Exitosa", null, "Cliente actualizado correctamente.", AlertType.INFORMATION);
        limpiar();
    }

    private void eliminar(){
        Cliente seleccionado = tablaClientes.getSelectionModel().getSelectedItem();

        if(seleccionado != null){
            Alert alerta = new Alert(AlertType.CONFIRMATION);
            alerta.setTitle("Confirmar Eliminación");
            alerta.setHeaderText("¿Está seguro de que desea eliminar al cliente?");
            alerta.setContentText(seleccionado.getNombre() + " (" + seleccionado.getDocumento() + ")");

            Optional<ButtonType> resultado = alerta.showAndWait();

            if (resultado.isPresent() && resultado.get() == ButtonType.OK){
                Repositorio.getInstancia().getClientes().remove(seleccionado);
                limpiar();
            }
        } else {

            mostrarAlerta("Error al Eliminar", "No ha seleccionado ningún cliente", "Por favor, seleccione un cliente de la tabla para eliminarlo.", AlertType.WARNING);
        }
    }

    private void limpiar(){
        txtDocumento.clear();
        txtNombre.clear();
        txtTelefono.clear();
        txtCorreo.clear();

        txtDocumento.setDisable(false);
        tablaClientes.getSelectionModel().clearSelection();
    }

    private void mostrarDatosCliente(Cliente cliente) {
        if (cliente != null) {
            txtDocumento.setText(cliente.getDocumento());
            txtNombre.setText(cliente.getNombre());
            txtTelefono.setText(cliente.getTelefono());
            txtCorreo.setText(cliente.getCorreo());

            txtDocumento.setDisable(true);
        }
    }


    private boolean esCorreoValido(String correo) {

        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

        return correo.matches(emailRegex);
    }

    private void mostrarAlerta(String titulo, String header, String contenido, AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(header);
        alerta.setContentText(contenido);
        alerta.showAndWait();
    }
}