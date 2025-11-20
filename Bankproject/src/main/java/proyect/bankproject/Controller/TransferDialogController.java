package proyect.bankproject.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import proyect.bankproject.Model.Accounts.BankAccount;
import proyect.bankproject.Service.TransactionService;
import proyect.bankproject.Exceptions.AccountNotFoundException;
import proyect.bankproject.Exceptions.InsufficientFundsException;
import proyect.bankproject.Exceptions.InvalidAmountException;


public class TransferDialogController {

    @FXML
    private TextField accountNumberField;
    @FXML
    private TextField amountField;
    @FXML
    private Label errorLabel;
    @FXML
    private Button confirmButton;

    private final TransactionService transactionService;
    private BankAccount sourceAccount;
    private boolean transferSuccessful = false;

    public TransferDialogController() {
        this.transactionService = new TransactionService();
    }

    public void initData(BankAccount sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    @FXML
    private void initialize() {
        errorLabel.setText("");
        confirmButton.setOnAction(event -> handleConfirmTransfer());
    }

    private void handleConfirmTransfer() {
        String destinationAccountNumber = accountNumberField.getText();
        String amountText = amountField.getText();

        try {
            transactionService.performTransfer(
                    sourceAccount,
                    destinationAccountNumber,
                    amountText
            );
            this.transferSuccessful = true;
            closeWindow();

        } catch (InvalidAmountException | AccountNotFoundException | InsufficientFundsException e) {
            errorLabel.setText(e.getMessage());
        } catch (Exception e) {
            errorLabel.setText("Error inesperado: " + e.getMessage());
        }
    }

    public boolean isTransferSuccessful() {
        return transferSuccessful;
    }

    private void closeWindow() {
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }
}
