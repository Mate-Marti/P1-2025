package proyect.bankproject.Model.Users;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import proyect.bankproject.Model.Accounts.BankAccount;

public class Client extends User {
    private List <BankAccount> accounts;
    public Client(String identification, String name, String lastName , String username, LocalDate birthDate, String passwordHash) {
        super(identification, name, lastName, username, birthDate,  passwordHash);
        this.accounts = new ArrayList<>();
    }

    @Override
    public String getRole() {
        return "Client";
    }
    public List <BankAccount> getAccounts (){
        return accounts;
    }
    public void addAccount (BankAccount account) {
        this.accounts.add(account);
    }
    public boolean removeAccount(BankAccount account) {
        return this.accounts.remove(account);
    }
}
