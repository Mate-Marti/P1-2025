package co.edu.uniquindio.poo.pfbanco.viewController;

import co.edu.uniquindio.poo.pfbanco.model.BancoData;
import co.edu.uniquindio.poo.pfbanco.model.Transaccion;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.beans.property.SimpleStringProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MonitoreoTransaccionesViewController {

    @FXML private TableView<Transaccion> tablaTransacciones;
    @FXML private TableColumn<Transaccion, String> colId;
    @FXML private TableColumn<Transaccion, String> colFecha;
    @FXML private TableColumn<Transaccion, String> colMonto;
    @FXML private TableColumn<Transaccion, String> colEstado;
    @FXML private TableColumn<Transaccion, String> colOrigen;
    @FXML private TableColumn<Transaccion, String> colDestino;

    private BancoData data = BancoData.getInstancia();
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @FXML
    public void initialize() {
        try {
            colId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));

            colFecha.setCellValueFactory(cellData -> {
                Date fecha = cellData.getValue().getFecha();
                return new SimpleStringProperty(fecha != null ? dateFormat.format(fecha) : "N/A");
            });

            colMonto.setCellValueFactory(cellData ->
                    new SimpleStringProperty(String.format("%,.2f", cellData.getValue().getMonto()))
            );

            colEstado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEstado()));
            colOrigen.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClienteOrigen()));
            colDestino.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClienteDestino()));

            tablaTransacciones.setItems(data.getListaTransacciones());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}