package proyect.parcial3.ViewController;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import proyect.parcial3.Model.ClinicData;
import proyect.parcial3.Model.Doctor;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class DoctorController implements Initializable {

    @FXML private TextField txtName, txtId, txtAge, txtGenre, txtLicense, txtSpecialization;
    @FXML private TableView<Doctor> tableDoctors;
    @FXML private TableColumn<Doctor, String> colName, colSpecialization, colLicense;

    private Doctor selectedDoctor;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configureTable();
        tableDoctors.getItems().setAll(ClinicData.getInstance().getDoctors());

        tableDoctors.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                selectedDoctor = newSel;
                fillForm(newSel);
            }
        });
    }

    private void fillForm(Doctor doctor) {
        txtName.setText(doctor.getFullName());
        txtId.setText(doctor.getIdentification());
        txtAge.setText(doctor.getAge());
        txtGenre.setText(doctor.getGenre());
        txtLicense.setText(doctor.getLicenseNumber());
        txtSpecialization.setText(doctor.getSpecialization());
    }

    private void configureTable() {
        colName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        colSpecialization.setCellValueFactory(new PropertyValueFactory<>("specialization"));
        colLicense.setCellValueFactory(new PropertyValueFactory<>("licenseNumber"));
    }

    @FXML
    public void updateDoctor() {
        if (selectedDoctor == null) {
            showAlert("Error", "Seleccione un médico primero.");
            return;
        }
        try {
            selectedDoctor.setFullName(txtName.getText());
            selectedDoctor.setIdentification(txtId.getText());
            selectedDoctor.setAge(txtAge.getText());
            selectedDoctor.setGenre(txtGenre.getText());
            selectedDoctor.setLicenseNumber(txtLicense.getText());
            selectedDoctor.setSpecialization(txtSpecialization.getText());

            tableDoctors.refresh();
            clearFields();
            selectedDoctor = null;
            tableDoctors.getSelectionModel().clearSelection();
            showAlert("Éxito", "Médico actualizado.");
        } catch (Exception e) { showAlert("Error", "Error al actualizar."); }
    }

    @FXML
    public void deleteDoctor() {
        if (selectedDoctor == null) {
            showAlert("Error", "Seleccione un médico para eliminar.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setContentText("¿Eliminar al Dr. " + selectedDoctor.getFullName() + "?");

        Optional<ButtonType> result = confirm.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            ClinicData.getInstance().getDoctors().remove(selectedDoctor);
            tableDoctors.getItems().remove(selectedDoctor);
            clearFields();
            selectedDoctor = null;
        }
    }
    @FXML
    public void addDoctor() {
        try {
            Doctor newDoc = new Doctor(txtName.getText(), txtGenre.getText(), txtAge.getText(),
                    txtId.getText(), txtLicense.getText(), txtSpecialization.getText());
            ClinicData.getInstance().getDoctors().add(newDoc);
            tableDoctors.getItems().add(newDoc);
            clearFields();
        } catch (Exception exception) { showAlert("Error", "Datos inválidos"); }
    }
    private void clearFields() {
        txtName.clear(); txtId.clear(); txtAge.clear();
        txtGenre.clear(); txtLicense.clear(); txtSpecialization.clear();
    }

    private void showAlert(String t, String m) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(t); a.setContentText(m); a.show();
    }
}