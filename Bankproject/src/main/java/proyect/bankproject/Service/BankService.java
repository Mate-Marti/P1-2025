package proyect.bankproject.Service;

import proyect.bankproject.Exceptions.AccountNotFoundException;
import proyect.bankproject.Exceptions.UserNotFoundException;
import proyect.bankproject.Model.Accounts.BankAccount;
import proyect.bankproject.Model.Users.Client;
import proyect.bankproject.Model.Users.User;
import proyect.bankproject.Utilities.FileUtility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BankService {

    private static BankService instance;

    private final Map<String, User> users;
    private final Map<String, BankAccount> accounts;

    private BankService() {
        this.users = new HashMap<>();
        this.accounts = new HashMap<>();
    }

    public static BankService getInstance() {
        if (instance == null) {
            instance = new BankService();
        }
        return instance;
    }

    public void initializeData() {
        FileUtility fileUtil = FileUtility.getInstance();
        users.clear();
        accounts.clear();
        List<User> loadedUserList = fileUtil.loadUsers();
        for (User user : loadedUserList) {
            users.put(user.getUsername(), user);
        }
        List<BankAccount> loadedAccountList = fileUtil.loadAccounts(new ArrayList<>(users.values()));
        for (BankAccount account : loadedAccountList) {
            accounts.put(account.getAccountNumber(), account);
        }
        fileUtil.loadTransactions(new ArrayList<>(accounts.values()));
        System.out.println("BankService: Datos inicializados.");
        System.out.println("Cargados " + users.size() + " usuarios.");
        System.out.println("Cargados " + accounts.size() + " cuentas.");
    }

    public void saveAllData() {
        FileUtility fileUtil = FileUtility.getInstance();
        List<User> allUsers = new ArrayList<>(users.values());
        List<BankAccount> allAccounts = new ArrayList<>(accounts.values());
        fileUtil.saveUsers(allUsers);
        fileUtil.saveAccounts(allAccounts);
        fileUtil.saveTransactions(allAccounts);
        System.out.println("BankService: Todos los datos han sido guardados en los .txt");
    }

    public User findUserByUsername(String username) throws UserNotFoundException {
        User user = users.get(username);
        if (user == null) {
            throw new UserNotFoundException("El usuario '" + username + "' no fue encontrado.");
        }
        return user;
    }

    public BankAccount findAccountByNumber(String accountNumber) throws AccountNotFoundException {
        BankAccount account = accounts.get(accountNumber);
        if (account == null) {
            throw new AccountNotFoundException("La cuenta con el número '" + accountNumber + "' no fue encontrada.");
        }
        return account;
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    public List<BankAccount> getAllAccounts() {
        return new ArrayList<>(accounts.values());
    }

    public void addUser(User user) {
        if (user != null) {
            users.put(user.getUsername(), user);
        }
    }

    public void addAccount(BankAccount account) {
        if (account != null) {
            accounts.put(account.getAccountNumber(), account);
        }
    }

    public void deleteUser(User user) {
        if (user != null) {
            users.remove(user.getUsername());
        }
    }

    public void deleteAccountsForClient(Client client) {
        List<String> accountsToRemove = accounts.values().stream()
                .filter(account -> account.getClient().equals(client))
                .map(BankAccount::getAccountNumber)
                .collect(Collectors.toList());
        for (String accountNumber : accountsToRemove) {
            accounts.remove(accountNumber);
        }
    }
    public User findUserByIdentification(String identification) {
        for (User user : users.values()) {
            if (user.getIdentification().equals(identification)) {
                return user;
            }
        }
        return null;
    }
}