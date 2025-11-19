package proyect.parcial3.ViewController;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import proyect.parcial3.Model.ClinicData;
import proyect.parcial3.Model.Patient;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class PatientController implements Initializable {

    @FXML private TextField txtName, txtId, txtAge, txtGenre, txtBloodType, txtEps;
    @FXML private TableView<Patient> tablePatients;
    @FXML private TableColumn<Patient, String> colName, colId, colEps;

    private Patient selectedPatient;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configureTable();
        loadExistingPatients();

        tablePatients.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedPatient = newSelection;
                fillForm(newSelection);
            }
        });
    }


    private void fillForm(Patient patient) {
        txtName.setText(patient.getFullName());
        txtId.setText(patient.getIdentification());
        txtAge.setText(patient.getAge());
        txtGenre.setText(patient.getGenre());
        txtBloodType.setText(patient.getBloodType());
        txtEps.setText(patient.getEps());
    }

    @FXML
    public void updatePatient() {
        if (selectedPatient == null) {
            showAlert("Error", "Debe seleccionar un paciente de la tabla primero.");
            return;
        }

        try {

            selectedPatient.setFullName(txtName.getText());
            selectedPatient.setIdentification(txtId.getText());
            selectedPatient.setAge(txtAge.getText());
            selectedPatient.setGenre(txtGenre.getText());
            selectedPatient.setBloodType(txtBloodType.getText());
            selectedPatient.setEps(txtEps.getText());

            tablePatients.refresh();

            clearFields();
            selectedPatient = null;
            tablePatients.getSelectionModel().clearSelection();

            showAlert("Éxito", "Paciente actualizado correctamente.");

        } catch (Exception exception) {
            showAlert("Error", "No se pudo actualizar.");
        }
    }


    @FXML
    public void deletePatient() {
        if (selectedPatient == null) {
            showAlert("Error", "Seleccione un paciente para eliminar.");
            return;
        }


        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Eliminar Paciente");
        confirm.setHeaderText(null);
        confirm.setContentText("¿Está seguro de eliminar a " + selectedPatient.getFullName() + "?");

        Optional<ButtonType> result = confirm.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {

            ClinicData.getInstance().getPatients().remove(selectedPatient);


            tablePatients.getItems().remove(selectedPatient);

            clearFields();
            selectedPatient = null;
        }
    }

    @FXML
    public void addPatient() {
        try {
            String name = txtName.getText();
            String id = txtId.getText();
            String age = txtAge.getText();
            String genre = txtGenre.getText();
            String bloodType = txtBloodType.getText();
            String eps = txtEps.getText();

            Patient newPatient = new Patient(name, genre, age, id, bloodType, eps);
            ClinicData.getInstance().getPatients().add(newPatient);
            tablePatients.getItems().add(newPatient);
            clearFields();

            showAlert("Éxito", "Paciente agregado correctamente.");

        } catch (Exception e) {
            showAlert("Error", "Datos inválidos o campos incompletos.");
            e.printStackTrace();
        }
    }

    private void configureTable() {
        colName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        colId.setCellValueFactory(new PropertyValueFactory<>("identification"));
        colEps.setCellValueFactory(new PropertyValueFactory<>("eps"));
    }

    private void loadExistingPatients() {
        tablePatients.getItems().setAll(ClinicData.getInstance().getPatients());
    }
    private void clearFields() {
        txtName.clear(); txtId.clear(); txtAge.clear();
        txtGenre.clear(); txtBloodType.clear(); txtEps.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title); alert.setHeaderText(null); alert.setContentText(message);
        alert.showAndWait();
    }
}