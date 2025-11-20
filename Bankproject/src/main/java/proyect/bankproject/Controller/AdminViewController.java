package proyect.bankproject.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.stage.Modality;
import javafx.stage.Stage;

import proyect.bankproject.Model.Accounts.BankAccount;
import proyect.bankproject.Model.Transactions.Transaction;
import proyect.bankproject.Model.Users.Administrator;
import proyect.bankproject.Model.Users.Cashier;
import proyect.bankproject.Model.Users.Employee;
import proyect.bankproject.Model.Users.User;
import proyect.bankproject.Model.Users.Client;
import proyect.bankproject.Service.BankService;
import proyect.bankproject.Service.RegistrationService;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class AdminViewController {

    // --- Campos Globales ---
    @FXML private Label welcomeLabel;
    @FXML private Label errorLabel;
    @FXML private TabPane tabPane;
    @FXML private Button logoutButton;

    // --- PESTAÑA 1: Gestión de Empleados ---
    @FXML private TableView<Employee> employeesTableView;
    @FXML private TableColumn<Employee, String> colEmployeeId;
    @FXML private TableColumn<Employee, String> colEmployeeName;
    @FXML private TableColumn<Employee, String> colEmployeeUsername;
    @FXML private TableColumn<Employee, String> colEmployeeRole;
    @FXML private TableColumn<Employee, Double> colEmployeeSalary;
    @FXML private Button registerEmployeeButton;
    @FXML private Button modifyEmployeeButton;
    @FXML private Button deleteEmployeeButton;

    // --- PESTAÑA 2: Reportes y Monitoreo ---
    @FXML private Button reportAllTransactionsButton;
    @FXML private Button reportUserActivityButton;
    @FXML private TextArea reportArea;

    // --- Servicios ---
    private BankService bankService;
    private RegistrationService registrationService;

    // Lista observable para la tabla
    private ObservableList<Employee> employeeObservableList;

    // Formateador de moneda para reportes
    private final NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));

    /**
     * Constructor
     */
    public AdminViewController() {
        this.bankService = BankService.getInstance();
        this.registrationService = new RegistrationService();
    }

    public void initData(Administrator admin) {
        welcomeLabel.setText("Bienvenido, Administrador " + admin.getName() + " " + admin.getLastName());
    }

    /**
     * Se llama cuando FXML está listo.
     */
    @FXML
    private void initialize() {
        errorLabel.setText("");

        // --- Pestaña 1: Configurar la Tabla de Empleados ---
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
        colEmployeeName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmployeeUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colEmployeeSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colEmployeeRole.setCellValueFactory(cellData -> {
            Employee employee = cellData.getValue();
            String role = (employee instanceof Cashier) ? "Cajero" : "Administrador";
            return new SimpleStringProperty(role);
        });
        loadEmployeeTable();

        // Conectar botones
        registerEmployeeButton.setOnAction(event -> handleRegisterEmployee());
        modifyEmployeeButton.setOnAction(event -> handleModifyEmployee());
        deleteEmployeeButton.setOnAction(event -> handleDeleteEmployee());

        // Conectar Logout
        if (logoutButton != null) {
            logoutButton.setOnAction(this::handleLogout);
        }

        // --- Pestaña 2: Conectar botones de reporte ---
        reportAllTransactionsButton.setOnAction(event -> handleReportAllTransactions());
        reportUserActivityButton.setOnAction(event -> handleReportUserActivity());
    }

    // ===================================================================
    // --- LÓGICA DE LOGOUT CON CONFIRMACIÓN ---
    // ===================================================================
    @FXML
    private void handleLogout(ActionEvent event) {
        // 1. Crear la alerta de confirmación
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cerrar Sesión");
        alert.setHeaderText("¿Está seguro que desea salir?");
        alert.setContentText("Se cerrará la sesión actual y volverá a la pantalla de inicio.");

        // 2. Mostrar y esperar a que el usuario decida
        Optional<ButtonType> result = alert.showAndWait();

        // 3. Si el usuario presiona "Aceptar" (OK), procedemos a cerrar sesión
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                // Cargar el FXML del Login
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/login-view.fxml"));
                Parent root = loader.load();

                // Obtener el Stage actual y cambiar la escena
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Banco - Login");
                stage.centerOnScreen();
                stage.show();

                System.out.println("Admin: Sesión cerrada correctamente.");

            } catch (IOException e) {
                showError("No se pudo cargar la pantalla de Login.");
                e.printStackTrace();
            }
        }
        // Si presiona cancelar, no hacemos nada (el método termina y se queda en la pantalla actual)
    }

    // ===================================================================
    // --- LÓGICA DE PESTAÑA 1: GESTIÓN DE EMPLEADOS ---
    // ===================================================================

    private void handleRegisterEmployee() {
        boolean saveClicked = showEmployeeDialog(null);
        if (saveClicked) {
            loadEmployeeTable();
            showSuccess("Empleado registrado exitosamente.");
        }
    }

    private void handleModifyEmployee() {
        Employee selectedEmployee = employeesTableView.getSelectionModel().getSelectedItem();
        if (selectedEmployee == null) {
            showError("Por favor, seleccione un empleado de la tabla para modificar.");
            return;
        }

        boolean saveClicked = showEmployeeDialog(selectedEmployee);

        if (saveClicked) {
            loadEmployeeTable();
            showSuccess("Empleado actualizado exitosamente.");
        }
    }

    private void handleDeleteEmployee() {
        Employee selectedEmployee = employeesTableView.getSelectionModel().getSelectedItem();
        if (selectedEmployee == null) {
            showError("Por favor, seleccione un empleado de la tabla para eliminar.");
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar Eliminación");
        alert.setHeaderText("¿Está seguro que desea eliminar a este empleado?");
        alert.setContentText(selectedEmployee.getName() + " (" + selectedEmployee.getUsername() + ")");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                registrationService.deleteEmployee(selectedEmployee);
                loadEmployeeTable();
                showSuccess("Empleado eliminado exitosamente.");
            } catch (Exception e) {
                showError("Error al eliminar: " + e.getMessage());
            }
        }
    }

    // ===================================================================
    // --- LÓGICA DE PESTAÑA 2: REPORTES ---
    // ===================================================================

    private void handleReportAllTransactions() {
        reportArea.clear();
        StringBuilder report = new StringBuilder("--- REPORTE DE MONITOREO DE TRANSACCIONES ---\n\n");
        List<BankAccount> allAccounts = bankService.getAllAccounts();
        if (allAccounts.isEmpty()) {
            report.append("No hay cuentas registradas en el banco.");
            reportArea.setText(report.toString());
            return;
        }
        int totalTransactions = 0;
        double totalVolume = 0.0;
        for (BankAccount account : allAccounts) {
            report.append("Cuenta: " + account.getAccountNumber() + " (Cliente: " + account.getClient().getName() + ")\n");
            List<Transaction> transactions = account.getTransactions();
            if (transactions.isEmpty()) {
                report.append("  (Sin transacciones)\n");
            } else {
                for (Transaction tx : transactions) {
                    report.append("  -> " + tx.toString() + "\n");
                    totalTransactions++;
                    totalVolume += tx.getAmount();
                }
            }
            report.append("\n");
        }
        report.append("--- FIN DEL REPORTE ---\n");
        report.append("Total de Cuentas: " + allAccounts.size() + "\n");
        report.append("Total de Transacciones: " + totalTransactions + "\n");
        report.append("Volumen Total Transado: " + currencyFormatter.format(totalVolume) + "\n");
        reportArea.setText(report.toString());
        showSuccess("Reporte de transacciones generado.");
    }

    private void handleReportUserActivity() {
        reportArea.clear();
        StringBuilder report = new StringBuilder("--- REPORTE DE ACTIVIDAD DE USUARIOS (CLIENTES) ---\n\n");
        List<User> allUsers = bankService.getAllUsers();
        double totalBalanceEnBanco = 0.0;
        int totalCuentas = 0;
        for (User user : allUsers) {
            if (user instanceof Client) {
                Client client = (Client) user;
                report.append("Cliente: " + client.getName() + " " + client.getLastName() + " (Cédula: " + client.getIdentification() + ")\n");
                List<BankAccount> clientAccounts = bankService.getAllAccounts().stream()
                        .filter(acc -> acc.getClient().equals(client))
                        .toList();
                if (clientAccounts.isEmpty()) {
                    report.append("  (Sin cuentas bancarias registradas)\n");
                } else {
                    for (BankAccount account : clientAccounts) {
                        report.append("  -> Cuenta: " + account.getAccountNumber() + " | Saldo: " + currencyFormatter.format(account.getBalance()) + "\n");
                        totalBalanceEnBanco += account.getBalance();
                        totalCuentas++;
                    }
                }
                report.append("\n");
            }
        }
        report.append("--- FIN DEL REPORTE ---\n");
        report.append("Total de Cuentas en el Banco: " + totalCuentas + "\n");
        report.append("Dinero Total Depositado: " + currencyFormatter.format(totalBalanceEnBanco) + "\n");
        reportArea.setText(report.toString());
        showSuccess("Reporte de actividad de clientes generado.");
    }

    // ===================================================================
    // --- MÉTODOS 'HELPER' ---
    // ===================================================================

    private void loadEmployeeTable() {
        List<Employee> employeeList = new ArrayList<>();
        List<User> allUsers = bankService.getAllUsers();
        for (User user : allUsers) {
            if (user instanceof Employee) {
                employeeList.add((Employee) user);
            }
        }
        employeeObservableList = FXCollections.observableArrayList(employeeList);
        employeesTableView.setItems(employeeObservableList);
    }

    private boolean showEmployeeDialog(Employee employee) {
        try {
            URL fxmlLocation = getClass().getResource("/employee-dialog.fxml");
            if (fxmlLocation == null) {
                showError("No se pudo encontrar 'employee-dialog.fxml'.");
                return false;
            }
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root = loader.load();
            EmployeeDialogController controller = loader.getController();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Formulario de Empleado");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.setScene(new Scene(root));
            controller.setDialogStage(dialogStage);
            if (employee != null) {
                controller.loadEmployeeData(employee);
            }
            dialogStage.showAndWait();
            return controller.isSaveClicked();
        } catch (IOException e) {
            showError("Error al cargar el diálogo de empleado: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    private void showError(String message) {
        errorLabel.setText("Error: " + message);
        errorLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
    }

    private void showSuccess(String message) {
        errorLabel.setText("Éxito: " + message);
        errorLabel.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
    }
}