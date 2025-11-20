package proyect.bankproject.Model.Accounts;
import proyect.bankproject.Exceptions.InsufficientFundsException;
import proyect.bankproject.Model.Users.Client;

public class BusinessAccount extends BankAccount {
    private double minimumBalanceRequirement;
    private double feeBelowMinimum;

    public BusinessAccount(String accountNumber, double initialBalance, Client client, double minimumBalanceRequirement, double feeBelowMinimum) {
        super(accountNumber, initialBalance, client);
        this.minimumBalanceRequirement = minimumBalanceRequirement;
        this.feeBelowMinimum = feeBelowMinimum;
    }

    @Override
    public boolean performWithdrawalLogic(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            return false;
        }
        if (getBalance() < amount) {
            throw new InsufficientFundsException(
                    "Fondos insuficientes. Las cuentas empresariales no permiten sobregiros.",
                    amount, getBalance());
        }
        setBalance(getBalance() - amount);
        return true;
    }

    @Override
    protected void calculateInterestOrFees() {
        if (getBalance() < this.minimumBalanceRequirement && this.feeBelowMinimum > 0) {
            setBalance(getBalance() - this.feeBelowMinimum);
            addTransaction(this.feeBelowMinimum, "Cobro Saldo Minimo");
        }
    }

    public double getMinimumBalanceRequirement() {
        return minimumBalanceRequirement;
    }
    public void setMinimumBalanceRequirement(double minimumBalanceRequirement) {
        this.minimumBalanceRequirement = minimumBalanceRequirement;
    }
    public double getFeeBelowMinimum() {
        return feeBelowMinimum;
    }
    public void setFeeBelowMinimum(double feeBelowMinimum) {
        this.feeBelowMinimum = feeBelowMinimum;
    }

    @Override
    public String toString() {
        return "Negocios" + " (" + this.getAccountNumber() + ")";
    }
}
