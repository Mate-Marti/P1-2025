package proyect.bankproject.Model.Accounts;

import proyect.bankproject.Exceptions.InsufficientFundsException;
import proyect.bankproject.Model.Users.Client;

public class SavingsAccount extends BankAccount {
    private double interestRate;

    public SavingsAccount(String accountNumber, double initialBalance, Client client, double interestRate) {
        super(accountNumber, initialBalance, client);
        this.interestRate = interestRate;
    }

    @Override
    public boolean performWithdrawalLogic(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            return false;
        }
        if (getBalance() < amount) {
            throw new InsufficientFundsException("Fondos insuficientes. Las cuentas de ahorro no permiten sobregiros.",
                    amount, getBalance());
        }
        setBalance(getBalance() - amount);
        return true;
    }

    @Override
    protected void calculateInterestOrFees() {
        if (getBalance() > 0 && this.interestRate > 0) {
            double interest = getBalance() * this.interestRate;
            setBalance(getBalance() + interest);
            addTransaction(interest, "Pago Interes");
        }
    }

    public double getInterestRate() {
        return interestRate;
    }
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public String toString() {
        return "Ahorros" + " (" + this.getAccountNumber() + ")";
    }
}