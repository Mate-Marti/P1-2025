package proyect.bankproject.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import proyect.bankproject.Exceptions.AccountNotFoundException;
import proyect.bankproject.Exceptions.InsufficientFundsException;
import proyect.bankproject.Exceptions.InvalidAmountException;
import proyect.bankproject.Model.Accounts.BankAccount;
import proyect.bankproject.Model.Transactions.Transaction;
import proyect.bankproject.Model.Users.Cashier;
import proyect.bankproject.Service.BankService;
import proyect.bankproject.Service.RegistrationService;
import proyect.bankproject.Service.TransactionService;

import java.io.IOException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class CashierViewController {

    @FXML private Label welcomeLabel;
    @FXML private Label errorLabel;
    @FXML private TabPane tabPane;
    @FXML private Button logoutButton;

    @FXML private TextField searchField;
    @FXML private Button searchButton;

    @FXML private VBox detailsPane;
    @FXML private Label clientNameLabel;
    @FXML private Label balanceLabel;
    @FXML private TextField amountField;
    @FXML private Button depositButton;
    @FXML private Button withdrawButton;
    @FXML private Button transferButton;

    @FXML private TextField regIdField;
    @FXML private TextField regNameField;
    @FXML private TextField regLastNameField;
    @FXML private DatePicker regBirthDatePicker;
    @FXML private TextField regUsernameField;
    @FXML private PasswordField regPasswordField;
    @FXML private Button registerButton;

    @FXML private TextField reportSearchField;
    @FXML private Button reportButton;
    @FXML private TextArea reportArea;

    private BankService bankService;
    private RegistrationService registrationService;
    private TransactionService transactionService;

    private Cashier currentCashier;
    private BankAccount currentAccount;

    private final NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));

    public CashierViewController() {
        this.bankService = BankService.getInstance();
        this.registrationService = new RegistrationService();
        this.transactionService = new TransactionService();
    }

    public void initData(Cashier cashier) {
        this.currentCashier = cashier;
        welcomeLabel.setText("Bienvenido, Cajero " + cashier.getName());
    }

    @FXML
    private void initialize() {

        searchButton.setOnAction(e -> handleSearchAccount());
        depositButton.setOnAction(e -> handleDeposit());
        withdrawButton.setOnAction(e -> handleWithdraw());
        transferButton.setOnAction(e -> handleTransfer());

        registerButton.setOnAction(e -> handleRegisterClient());

        reportButton.setOnAction(e -> handleGenerateReport());

        if (logoutButton != null) {
            logoutButton.setOnAction(this::handleLogout);
        }
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cerrar Sesión");
        alert.setHeaderText("¿Está seguro que desea salir?");
        alert.setContentText("Volverá a la pantalla de inicio de sesión.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/login-view.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Banco - Login");
                stage.centerOnScreen();
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleSearchAccount() {
        String accNum = searchField.getText();
        if (accNum.isBlank()) {
            showError("Ingrese un número de cuenta.");
            return;
        }

        try {
            currentAccount = bankService.findAccountByNumber(accNum);

            clientNameLabel.setText(currentAccount.getClient().getName() + " " + currentAccount.getClient().getLastName());
            balanceLabel.setText(currencyFormatter.format(currentAccount.getBalance()));

            detailsPane.setVisible(true);
            showSuccess("Cuenta encontrada.");

        } catch (AccountNotFoundException e) {
            detailsPane.setVisible(false);
            currentAccount = null;
            showError("Cuenta no encontrada.");
        }
    }

    private void handleDeposit() {
        if (currentAccount == null) return;
        try {
            transactionService.performDeposit(currentAccount, amountField.getText());
            refreshAccountDetails();
            showSuccess("Depósito realizado con éxito.");
            amountField.clear();
        } catch (InvalidAmountException e) {
            showError(e.getMessage());
        }
    }

    private void handleWithdraw() {
        if (currentAccount == null) return;
        try {
            transactionService.performWithdrawal(currentAccount, amountField.getText());
            refreshAccountDetails();
            showSuccess("Retiro realizado con éxito.");
            amountField.clear();
        } catch (InvalidAmountException | InsufficientFundsException e) {
            showError(e.getMessage());
        }
    }

    private void handleTransfer() {
        if (currentAccount == null) return;

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Transferencia");
        dialog.setHeaderText("Transferir desde: " + currentAccount.getAccountNumber());
        dialog.setContentText("Ingrese el número de cuenta DESTINO:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String destAccountNum = result.get();
            String amountStr = amountField.getText();

            try {
                transactionService.performTransfer(currentAccount, destAccountNum, amountStr);
                refreshAccountDetails();
                showSuccess("Transferencia realizada con éxito.");
                amountField.clear();
            } catch (Exception e) {
                showError(e.getMessage());
            }
        }
    }

    private void refreshAccountDetails() {
        if (currentAccount != null) {
            balanceLabel.setText(currencyFormatter.format(currentAccount.getBalance()));
        }
    }

    private void handleRegisterClient() {
        try {
            String id = regIdField.getText();
            String name = regNameField.getText();
            String lastName = regLastNameField.getText();
            LocalDate dob = regBirthDatePicker.getValue();
            String user = regUsernameField.getText();
            String pass = regPasswordField.getText();

            if (id.isBlank() || name.isBlank() || lastName.isBlank() || dob == null || user.isBlank() || pass.isBlank()) {
                showError("Todos los campos son obligatorios.");
                return;
            }

            registrationService.registerNewClient(id, name, lastName, dob, user, pass);
            showSuccess("Cliente " + name + " registrado exitosamente.");

            regIdField.clear(); regNameField.clear(); regLastNameField.clear();
            regBirthDatePicker.setValue(null); regUsernameField.clear(); regPasswordField.clear();

        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    private void handleGenerateReport() {
        String accNum = reportSearchField.getText();
        if (accNum.isBlank()) {
            showError("Ingrese un número de cuenta para el reporte.");
            return;
        }

        try {
            BankAccount account = bankService.findAccountByNumber(accNum);
            StringBuilder report = new StringBuilder();
            report.append("--- REPORTE DE CUENTA ---\n");
            report.append("Cuenta: ").append(account.getAccountNumber()).append("\n");
            report.append("Cliente: ").append(account.getClient().getName()).append(" ").append(account.getClient().getLastName()).append("\n");
            report.append("Saldo Actual: ").append(currencyFormatter.format(account.getBalance())).append("\n");
            report.append("-------------------------\n");
            report.append("Historial de Transacciones:\n\n");

            List<Transaction> transactions = account.getTransactions();
            if (transactions.isEmpty()) {
                report.append("  (Sin movimientos registrados)");
            } else {
                for (Transaction tx : transactions) {
                    report.append(tx.toString()).append("\n");
                }
            }

            reportArea.setText(report.toString());
            showSuccess("Reporte generado.");

        } catch (AccountNotFoundException e) {
            showError("Cuenta no encontrada.");
            reportArea.clear();
        }
    }

    private void showError(String message) {
        errorLabel.setText("Error: " + message);
        errorLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold; -fx-font-family: 'Times New Roman';");
    }

    private void showSuccess(String message) {
        errorLabel.setText("Éxito: " + message);
        errorLabel.setStyle("-fx-text-fill: green; -fx-font-weight: bold; -fx-font-family: 'Times New Roman';");
    }
}