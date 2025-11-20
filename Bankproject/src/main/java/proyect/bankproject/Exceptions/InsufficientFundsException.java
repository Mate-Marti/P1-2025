package proyect.bankproject.Exceptions;

public class InsufficientFundsException extends Exception {
    private final double amountAttempted;
    private final double currentBalance;

    public InsufficientFundsException(String message, double amountAttempted, double currentBalance) {
        super(message + " - Intento de retiro: " + amountAttempted + " | Saldo disponible: " + currentBalance);
        this.amountAttempted = amountAttempted;
        this.currentBalance = currentBalance;
    }
    public double getAmountAttempted() {
        return amountAttempted;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

}
