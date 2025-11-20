package proyect.bankproject.Service;

import proyect.bankproject.Exceptions.InsufficientFundsException;
import proyect.bankproject.Exceptions.InvalidAmountException;
import proyect.bankproject.Model.Accounts.BankAccount;

public class TransactionService {

    private final BankService bankService;

    public TransactionService() {
        this.bankService = BankService.getInstance();
    }

    private double parseAmount(String amountText) throws InvalidAmountException {
        double amount;
        if (amountText == null || amountText.isBlank()) {
            throw new InvalidAmountException("El monto no puede estar vacío.");
        }
        try {
            amount = Double.parseDouble(amountText);
        } catch (NumberFormatException e) {

            throw new InvalidAmountException("El monto debe ser un número válido");
        }

        if (amount <= 0) {

            throw new InvalidAmountException("El monto debe ser positivo");
        }
        return amount;
    }

    public void performDeposit(BankAccount account, String amountText) throws InvalidAmountException {

        double amount = parseAmount(amountText);

        account.deposit(amount);

        bankService.saveAllData();
        System.out.println("TransactionService: Depósito exitoso.");
    }

    public void performWithdrawal(BankAccount account, String amountText)
            throws InvalidAmountException, InsufficientFundsException {

        double amount = parseAmount(amountText);

        account.withdraw(amount);

        bankService.saveAllData();
        System.out.println("TransactionService: Retiro exitoso.");
    }

    public void performTransfer(BankAccount fromAccount, String toAccountNumber, String amountText)
            throws InvalidAmountException, InsufficientFundsException, Exception {

        double amount = parseAmount(amountText);

        BankAccount toAccount;
        try {
            toAccount = bankService.findAccountByNumber(toAccountNumber);
        } catch (Exception e) {
            throw new Exception("La cuenta de destino no existe.");
        }

        fromAccount.transfer(toAccount, amount);

        bankService.saveAllData();
    }
}
