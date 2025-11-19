package proyect.parcial3.ViewController;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import proyect.parcial3.Model.Appointment;
import proyect.parcial3.Model.ClinicData;
import proyect.parcial3.Model.Doctor;
import proyect.parcial3.Model.Patient;
import proyect.parcial3.Model.ClinicData;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class AppointmentController implements Initializable {

    @FXML private ComboBox<Patient> comboPatient;
    @FXML private ComboBox<Doctor> comboDoctor;
    @FXML private DatePicker datePicker;
    @FXML private TextField txtPrice;

    @FXML private TableView<Appointment> tableAppointments;
    @FXML private TableColumn<Appointment, String> colDate;
    @FXML private TableColumn<Appointment, String> colPatient;
    @FXML private TableColumn<Appointment, String> colDoctor;
    @FXML private TableColumn<Appointment, Double> colPrice;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboPatient.setItems(FXCollections.observableArrayList(ClinicData.getInstance().getPatients()));
        comboDoctor.setItems(FXCollections.observableArrayList(ClinicData.getInstance().getDoctors()));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colPatient.setCellValueFactory(new PropertyValueFactory<>("patient"));
        colDoctor.setCellValueFactory(new PropertyValueFactory<>("doctor"));
        tableAppointments.getItems().setAll(ClinicData.getInstance().getAppointments());
    }

    @FXML
    public void scheduleAppointment() {
        try {
            Patient patient = comboPatient.getValue();
            Doctor doctor = comboDoctor.getValue();
            LocalDate date = datePicker.getValue();
            double price = Double.parseDouble(txtPrice.getText());

            if (patient == null || doctor == null || date == null) {
                return;
            }


            Appointment app = new Appointment(patient, doctor, date, LocalTime.now(), price);

            ClinicData.getInstance().getAppointments().add(app);
            tableAppointments.getItems().add(app);
            comboPatient.getSelectionModel().clearSelection();
            comboDoctor.getSelectionModel().clearSelection();
            txtPrice.clear();
            datePicker.setValue(null);

        } catch (NumberFormatException exception) {
            System.out.println("Precio inválido");
        }
    }
}
