package proyect.bankproject.Model.Transactions;

import java.time.LocalDateTime;

public final class Transaction {

    private final double amount;
    private final String type;
    private final String relatedAccount;
    private final LocalDateTime timestamp;

    public Transaction(double amount, String type, String relatedAccount) {
        this(amount, type, relatedAccount, LocalDateTime.now());
    }

    public Transaction(double amount, String type, String relatedAccount, LocalDateTime timestamp) {
        this.amount = amount;
        this.type = type;
        this.relatedAccount = relatedAccount;
        this.timestamp = timestamp;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public String getRelatedAccount() {
        return relatedAccount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    @Override
    public String toString() {
        String formattedDate = this.timestamp.toLocalDate().toString();

        return String.format("%s (%s): $%.2f", formattedDate, this.type, this.amount);
    }
}
