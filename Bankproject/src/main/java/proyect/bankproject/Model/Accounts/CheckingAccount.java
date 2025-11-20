package proyect.bankproject.Model.Accounts;
import proyect.bankproject.Exceptions.InsufficientFundsException;
import proyect.bankproject.Model.Users.Client;

public class CheckingAccount extends BankAccount {
    private double overdraftLimit;
    private static final double MONTHLY_FEE = 5.0;

    public CheckingAccount(String accountNumber, double initialBalance, Client client, double overdraftLimit) {
        super(accountNumber, initialBalance, client);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public boolean performWithdrawalLogic(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            return false;
        }
        double newBalance = getBalance() - amount;

        if (newBalance < -this.overdraftLimit) {
            throw new InsufficientFundsException(
                    "Fondos insuficientes. El retiro excede su límite de sobregiro de: "
                            + this.overdraftLimit, amount, getBalance());
        }
        setBalance(newBalance);
        return true;
    }

    @Override
    protected void calculateInterestOrFees() {
        if (MONTHLY_FEE > 0) {
            setBalance(getBalance() - MONTHLY_FEE);
            addTransaction(MONTHLY_FEE, "COBRO_MANTENIMIENTO");
        }
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }
    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public String toString() {
        return "Corriente" + " (" + this.getAccountNumber() + ")";
    }
}
