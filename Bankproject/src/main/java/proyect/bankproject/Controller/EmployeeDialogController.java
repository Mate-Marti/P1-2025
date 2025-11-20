package proyect.bankproject.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import proyect.bankproject.Model.Users.Administrator;
import proyect.bankproject.Model.Users.Cashier;
import proyect.bankproject.Model.Users.Employee;
import proyect.bankproject.Service.RegistrationService;
import proyect.bankproject.Exceptions.DuplicateUserException;

import java.time.LocalDate;

public class EmployeeDialogController {

    @FXML private Label titleLabel;
    @FXML private TextField idField;
    @FXML private TextField nameField;
    @FXML private TextField lastNameField;
    @FXML private DatePicker birthDatePicker;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private TextField salaryField;
    @FXML private ComboBox<String> roleComboBox;
    @FXML private Label errorLabel;
    @FXML private Button saveButton;

    private RegistrationService registrationService;
    private Stage dialogStage;
    private Employee employeeToModify;
    private boolean saveClicked = false;

    @FXML
    private void initialize() {
        this.registrationService = new RegistrationService();
        roleComboBox.getItems().addAll("Cajero", "Administrador");
        saveButton.setOnAction(event -> handleSave());
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void loadEmployeeData(Employee employee) {
        this.employeeToModify = employee;
        titleLabel.setText("Modificar Empleado");

        idField.setText(employee.getIdentification());
        nameField.setText(employee.getName());
        lastNameField.setText(employee.getLastName());
        birthDatePicker.setValue(employee.getBirthDate());
        usernameField.setText(employee.getUsername());
        salaryField.setText(String.valueOf(employee.getSalary()));

        if (employee instanceof Cashier) {
            roleComboBox.setValue("Cajero");
        } else if (employee instanceof Administrator) {
            roleComboBox.setValue("Administrador");
        }

        idField.setEditable(false);
        usernameField.setEditable(false);

        nameField.setEditable(true);
        lastNameField.setEditable(true);
        birthDatePicker.setEditable(true);
        passwordField.setEditable(true);
        salaryField.setEditable(true);
        roleComboBox.setDisable(false);
        saveButton.setDisable(false);

        errorLabel.setText("");
        passwordField.setPromptText("Dejar vacío para no cambiar");
    }

    private void handleSave() {
        try {
            String name = nameField.getText();
            String lastName = lastNameField.getText();
            LocalDate birthDate = birthDatePicker.getValue();
            String password = passwordField.getText();
            String salaryText = salaryField.getText();
            String role = roleComboBox.getValue();

            if (name.isBlank() || lastName.isBlank() || birthDate == null ||
                    salaryText.isBlank() || role == null) {
                throw new Exception("Todos los campos (excepto contraseña) son obligatorios.");
            }
            double salary = Double.parseDouble(salaryText);

            if (employeeToModify != null) {
                registrationService.updateEmployee(
                        employeeToModify,
                        name,
                        lastName,
                        birthDate,
                        password,
                        salary,
                        role
                );

            } else {

                String id = idField.getText();
                String username = usernameField.getText();
                if (id.isBlank() || username.isBlank() || password.isBlank()) {
                    throw new Exception("Para un nuevo empleado, ID, Usuario y Contraseña son obligatorios.");
                }

                registrationService.registerNewEmployee(
                        id,
                        name,
                        lastName,
                        birthDate,
                        username,
                        password,
                        salary,
                        role
                );
            }

            saveClicked = true;
            dialogStage.close();

        } catch (DuplicateUserException e) {
            errorLabel.setText("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            errorLabel.setText("Error: El salario debe ser un número.");
        } catch (Exception e) {
            errorLabel.setText("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean isSaveClicked() {
        return saveClicked;
    }
}