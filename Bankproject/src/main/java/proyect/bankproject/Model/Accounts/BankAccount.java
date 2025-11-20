package proyect.bankproject.Model.Accounts;

import proyect.bankproject.Model.Users.Client;
import proyect.bankproject.Exceptions.InsufficientFundsException;
import proyect.bankproject.Model.Transactions.Transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class BankAccount implements ITransaccionable {
    private final String accountNumber;
    private double balance;
    private Client client;
    private List<Transaction> transactions;

    public BankAccount(String accountNumber, double initialBalance, Client client) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.client = client;
        this.transactions = new ArrayList<>();

        if (initialBalance > 0) {
            this.addTransaction(initialBalance, "DEPOSITO_INICIAL");
        }
    }

    public final double getBalance() {
        return this.balance;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }
    protected void setBalance(double newBalance) {
        this.balance = newBalance;
    }
    public void restoreTransactions(Transaction transaction) {
        this.transactions.add(transaction);
    }
    public void restoreTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }
    protected abstract void calculateInterestOrFees();

    protected void addTransaction(double amount, String type) {
        Transaction tx = new Transaction(amount, type, this.accountNumber);
        this.transactions.add(tx);
    }

    protected void performDepositLogic(double amount) {
        setBalance(this.balance + amount);
    }

    @Override
    public boolean deposit(double amount) {
        if (amount <= 0) {
            return false;
        }
        performDepositLogic(amount);
        this.addTransaction(amount, "DEPOSITO");
        return true;
    }

    public abstract boolean performWithdrawalLogic(double amount) throws InsufficientFundsException;

    @Override
    public boolean withdraw(double amount) throws InsufficientFundsException {
        if (this.performWithdrawalLogic(amount)) {
            this.addTransaction(amount, "RETIRO");
            return true;
        }
        return false;
    }

    @Override
    public boolean transfer(BankAccount targetAccount, double amount) throws InsufficientFundsException {

        if (this.performWithdrawalLogic(amount)) {

            targetAccount.performDepositLogic(amount);
            this.addTransaction(amount, "TRANSFERENCIA_ENVIADA");
            targetAccount.addTransaction(amount, "TRANSFERENCIA_RECIBIDA");

            return true;
        }
        return false;
    }
}