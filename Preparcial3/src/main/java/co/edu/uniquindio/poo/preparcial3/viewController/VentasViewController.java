package co.edu.uniquindio.poo.preparcial3.viewController;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.LocalDate;
import co.edu.uniquindio.poo.preparcial3.model.Cliente;
import co.edu.uniquindio.poo.preparcial3.model.Producto;
import co.edu.uniquindio.poo.preparcial3.model.ItemVenta;
import co.edu.uniquindio.poo.preparcial3.model.Venta;
import co.edu.uniquindio.poo.preparcial3.model.Repositorio;

public class VentasViewController {
    @FXML private TextField txtFecha;
    @FXML private ComboBox<Cliente> comboCliente;
    @FXML private ComboBox<Producto> comboProducto;
    @FXML private Spinner<Integer> spinnerCantidad;
    @FXML private Button btnGuardar;
    @FXML private TableView<ItemVenta> tablaItems;
    @FXML private TableColumn<ItemVenta, String> colProducto;
    @FXML private TableColumn<ItemVenta, Integer> colCantidad;
    @FXML private TableColumn<ItemVenta, Double> colTotal;

    private Venta ventaActual;

    @FXML
    public void initialize() {
        txtFecha.setText(LocalDate.now().toString());
        comboCliente.setItems(Repositorio.getInstancia().getClientes());
        comboProducto.setItems(Repositorio.getInstancia().getProductos());

        colProducto.setCellValueFactory(new PropertyValueFactory<>("producto"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        spinnerCantidad.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1));

        btnGuardar.setOnAction(e -> guardarVenta());
    }

    private void guardarVenta(){
        Cliente cliente = comboCliente.getValue();
        Producto producto = comboProducto.getValue();
        if(cliente==null || producto==null) return;

        if(ventaActual==null) ventaActual = new Venta(cliente);

        ItemVenta item = new ItemVenta(producto, spinnerCantidad.getValue());
        ventaActual.agregarItem(item);
        tablaItems.setItems(ventaActual.getItems());

        Repositorio.getInstancia().getVentas().add(ventaActual);
    }
}