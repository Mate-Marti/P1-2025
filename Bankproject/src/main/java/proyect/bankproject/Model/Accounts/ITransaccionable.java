package proyect.bankproject.Model.Accounts;

import proyect.bankproject.Exceptions.InsufficientFundsException;

public interface ITransaccionable {

    boolean deposit(double amount);
    boolean withdraw(double amount) throws InsufficientFundsException;
    boolean transfer(BankAccount targetAccount, double amount) throws InsufficientFundsException;
}
