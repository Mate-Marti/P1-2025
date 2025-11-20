package proyect.bankproject.Controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import proyect.bankproject.Exceptions.InsufficientFundsException;
import proyect.bankproject.Exceptions.InvalidAmountException;
import proyect.bankproject.Model.Accounts.BankAccount;
import proyect.bankproject.Model.Users.Client;
import proyect.bankproject.Service.BankService;
import proyect.bankproject.Service.TransactionService;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

public class ClientViewController {

    @FXML private BorderPane dashboardPane;
    @FXML private Label welcomeLabel;
    @FXML private Button logoutButton;

    @FXML private ListView<BankAccount> accountListView;

    private BankService bankService;
    private TransactionService transactionService;
    private Client currentClient;
    private final NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));

    public ClientViewController() {
        this.bankService = BankService.getInstance();
        this.transactionService = new TransactionService();
    }

    public void initData(Client client) {
        this.currentClient = client;
        welcomeLabel.setText("Bienvenido, " + client.getName());
        loadAccounts();
    }

    @FXML
    private void initialize() {

        if (logoutButton != null) {
            logoutButton.setOnAction(this::handleLogout);
        }

        accountListView.setCellFactory(param -> new ListCell<BankAccount>() {
            @Override
            protected void updateItem(BankAccount item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    String type = item.getClass().getSimpleName().replace("Account", "");
                    setText(type + " - " + item.getAccountNumber());
                    setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 14px;");
                }
            }
        });

        accountListView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                showAccountDetails(newVal);
            }
        });
    }

    private void showAccountDetails(BankAccount account) {

        VBox content = new VBox(20);
        content.setAlignment(Pos.TOP_CENTER);
        content.setPadding(new Insets(30));
        content.setStyle("-fx-background-color: white; -fx-font-family: 'Times New Roman';");

        Label title = new Label("Detalles de la Cuenta " + account.getAccountNumber());
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #003366;");

        Label balanceLabel = new Label("Saldo Disponible: " + currencyFormatter.format(account.getBalance()));
        balanceLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #28a745; -fx-font-weight: bold;");

        Separator sep = new Separator();

        Label transferTitle = new Label("Realizar Transferencia desde esta cuenta");
        transferTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(15);
        grid.setAlignment(Pos.CENTER);
        grid.setStyle("-fx-background-color: #f4f4f4; -fx-padding: 20; -fx-background-radius: 10;");

        TextField destField = new TextField();
        destField.setPromptText("Número de cuenta destino");
        destField.setStyle("-fx-font-family: 'Times New Roman';");

        TextField amountField = new TextField();
        amountField.setPromptText("0.00");
        amountField.setStyle("-fx-font-family: 'Times New Roman';");

        Button btnTransfer = new Button("Transferir Dinero");
        btnTransfer.setStyle("-fx-background-color: #003366; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-family: 'Times New Roman';");

        btnTransfer.setOnAction(e -> {
            handleTransfer(account, destField.getText(), amountField.getText());
            balanceLabel.setText("Saldo Disponible: " + currencyFormatter.format(account.getBalance()));
        });

        grid.add(new Label("Cuenta Destino:"), 0, 0);
        grid.add(destField, 1, 0);
        grid.add(new Label("Monto:"), 0, 1);
        grid.add(amountField, 1, 1);
        grid.add(btnTransfer, 1, 2);

        content.getChildren().addAll(title, balanceLabel, sep, transferTitle, grid);

        dashboardPane.setCenter(content);
    }


    private void loadAccounts() {
        if (currentClient == null) return;

        List<BankAccount> myAccounts = bankService.getAllAccounts().stream()
                .filter(acc -> acc.getClient().getIdentification().equals(currentClient.getIdentification()))
                .collect(Collectors.toList());

        accountListView.setItems(FXCollections.observableArrayList(myAccounts));
    }

    private void handleTransfer(BankAccount originAccount, String destNumber, String amountStr) {
        if (destNumber.isBlank() || amountStr.isBlank()) {
            showAlert(Alert.AlertType.WARNING, "Campos vacíos", "Por favor complete todos los campos.");
            return;
        }

        if (originAccount.getAccountNumber().equals(destNumber)) {
            showAlert(Alert.AlertType.WARNING, "Error", "No puede transferir a la misma cuenta.");
            return;
        }

        try {

            transactionService.performTransfer(originAccount, destNumber, amountStr);

            showAlert(Alert.AlertType.INFORMATION, "Éxito", "Transferencia realizada correctamente.");

            accountListView.refresh();

        } catch (InvalidAmountException | InsufficientFundsException e) {
            showAlert(Alert.AlertType.ERROR, "Error de Transacción", e.getMessage());
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
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

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
