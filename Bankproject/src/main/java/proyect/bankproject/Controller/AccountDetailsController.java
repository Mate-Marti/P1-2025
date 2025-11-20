package proyect.bankproject.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.*;
import proyect.bankproject.Model.Accounts.BankAccount;
import proyect.bankproject.Model.Transactions.Transaction;
import proyect.bankproject.Service.TransactionService;


import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;

public class AccountDetailsController {

    @FXML private Label accountTypeLabel;
    @FXML private Label balanceLabel;

    @FXML private Button transferButton;
    @FXML private ListView<Transaction> transactionsListView;

    private BankAccount currentAccount;
    private final TransactionService transactionService;
    private final NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));

    public AccountDetailsController() {
        this.transactionService = new TransactionService();
    }

    public void initData(BankAccount account) {
        this.currentAccount = account;
        updateUI();
    }

    @FXML
    private void initialize() {
        transferButton.setOnAction(event -> handleTransfer());
    }

    private void handleTransfer() {
        try {
            URL fxmlLocation = getClass().getResource("/transfer-dialog.fxml");
            if (fxmlLocation == null) {
                showAlert(Alert.AlertType.ERROR, "Error del Sistema", "No se pudo encontrar 'transfer-dialog.fxml'");
                return;
            }

            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root = loader.load();
            TransferDialogController dialogController = loader.getController();
            dialogController.initData(currentAccount);
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Realizar Transferencia");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.setScene(new Scene(root));
            dialogStage.showAndWait();

            if (dialogController.isTransferSuccessful()) {
                showAlert(Alert.AlertType.INFORMATION, "Transferencia Exitosa", "La transferencia se realizó correctamente.");
                updateUI();
            }
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Error del Sistema", "No se pudo cargar el diálogo de transferencia.");
            e.printStackTrace();
        }
    }

    private void updateUI() {
        accountTypeLabel.setText(currentAccount.toString());
        balanceLabel.setText(currencyFormatter.format(currentAccount.getBalance()));
        transactionsListView.getItems().clear();
        transactionsListView.getItems().addAll(currentAccount.getTransactions());
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}