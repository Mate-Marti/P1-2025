package proyect.parcial3.ViewController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import java.io.IOException;


public class ViewController {

    @FXML
    private StackPane contentArea;

    @FXML
    public void openPatients() {
        loadView("/proyect/parcial3/Patient.fxml");
    }

    @FXML
    public void openDoctors() {
        loadView("/proyect/parcial3/Doctor.fxml");
    }

    @FXML
    public void openAppointments() {
        loadView("/proyect/parcial3/Appointment.fxml");
    }

    private void loadView(String fxmlPath) {
        try {
            Parent view = FXMLLoader.load(getClass().getResource(fxmlPath));
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(view);
        } catch (IOException exception) {
            System.out.println("Error al cargar el FXML: " + fxmlPath);
            exception.printStackTrace();
        }
    }
}
