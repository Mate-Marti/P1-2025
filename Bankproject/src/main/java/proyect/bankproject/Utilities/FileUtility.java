package proyect.bankproject.Utilities;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import proyect.bankproject.Model.Accounts.*;
import proyect.bankproject.Model.Accounts.BankAccount;
import proyect.bankproject.Model.Accounts.CheckingAccount;
import proyect.bankproject.Model.Accounts.BusinessAccount;
import proyect.bankproject.Model.Accounts.SavingsAccount;

import proyect.bankproject.Model.Users.*;
import proyect.bankproject.Model.Users.User;
import proyect.bankproject.Model.Users.Client;
import proyect.bankproject.Model.Users.Employee;
import proyect.bankproject.Model.Users.Administrator;
import proyect.bankproject.Model.Users.Cashier;

import proyect.bankproject.Model.Transactions.Transaction;

public class FileUtility {
private static FileUtility instance;
private FileUtility() {
    try {
        Files.createDirectories(Paths.get("data"));
    }catch (IOException e) {
        System.err.println("Error creating data directory: " + e.getMessage());
    }
}
public static synchronized FileUtility getInstance() {
    if (instance == null) {
        instance = new FileUtility();
    }
    return instance;
}
    private static final String USERSFILEPATH = "data/users.txt";
    private static final String ACCOUNTSFILEPATH= "data/accounts.txt";
    private static final String TRANSACTIONSFILEPATH = "data/transactions.txt";

    private static final String SEPARATOR = ";";

    public void saveUsers(List<User> users) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERSFILEPATH))) {
            for (User user : users) {
                StringBuilder line = new StringBuilder();

                if (user instanceof Client) {
                    line.append("CLIENT").append(SEPARATOR);
                    line.append(user.getIdentification()).append(SEPARATOR);
                    line.append(user.getName()).append(SEPARATOR);
                    line.append(user.getLastName()).append(SEPARATOR);
                    line.append(user.getUsername()).append(SEPARATOR);
                    line.append(user.getBirthDate()).append(SEPARATOR); // We saved LocalDate
                    line.append(user.getPasswordHash());
                } else if (user instanceof Cashier) {
                    line.append("CASHIER").append(SEPARATOR);
                    line.append(user.getIdentification()).append(SEPARATOR);
                    line.append(user.getName()).append(SEPARATOR);
                    line.append(user.getLastName()).append(SEPARATOR);
                    line.append(user.getUsername()).append(SEPARATOR);
                    line.append(user.getBirthDate()).append(SEPARATOR);
                    line.append(user.getPasswordHash()).append(SEPARATOR);
                    Employee emp = (Employee) user;
                    line.append(emp.getEmployeeID()).append(SEPARATOR);
                    line.append(emp.getSalary());
                } else if (user instanceof Administrator) {
                    line.append("ADMIN").append(SEPARATOR);
                    line.append(user.getIdentification()).append(SEPARATOR);
                    line.append(user.getName()).append(SEPARATOR);
                    line.append(user.getLastName()).append(SEPARATOR);
                    line.append(user.getUsername()).append(SEPARATOR);
                    line.append(user.getBirthDate()).append(SEPARATOR);
                    line.append(user.getPasswordHash()).append(SEPARATOR);
                    Employee emp = (Employee) user;
                    line.append(emp.getEmployeeID()).append(SEPARATOR);
                    line.append(emp.getSalary());
                }
                writer.write(line.toString());
                writer.newLine();
            }
        }catch (IOException e){
            System.err.println("Error writing to users file: " + e.getMessage());
        }
    }
    public List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        Path path = Paths.get(USERSFILEPATH);

        if (!Files.exists(path)) {
            System.out.println("No se encontró 'users.txt'. Asumiendo primer inicio.");
            return users;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(USERSFILEPATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) {
                    continue;
                }
                try {
                    String[] parts = line.split(SEPARATOR);
                    String role = parts[0];
                    String identification = parts[1];
                    String name = parts[2];
                    String lastName = parts[3];
                    String username = parts[4];
                    LocalDate birthDate = LocalDate.parse(parts[5]);
                    String passwordHash = parts[6];
                    switch (role) {
                        case "CLIENT":
                            users.add(new Client(identification, name, lastName, username,birthDate, passwordHash));
                            break;

                        case "CASHIER":
                            String cashierEmployeeID = parts[7];
                            double cashierSalary = Double.parseDouble(parts[8]); // Conversión de String a double
                            users.add(new Cashier(identification, name, lastName, birthDate, username, passwordHash,
                                    cashierEmployeeID, cashierSalary));
                            break;

                        case "ADMIN":
                            String adminEmployeeID = parts[7];
                            double adminSalary = Double.parseDouble(parts[8]); // Conversión de String a double
                            users.add(new Administrator(identification, name, lastName, birthDate, username, passwordHash,
                                    adminEmployeeID, adminSalary));
                            break;

                        default:
                            System.err.println("Rol desconocido en users.txt: " + role);
                    }
                } catch (Exception e) {
                    System.err.println("Error parseando línea en users.txt: '" + line + "' - Error: " + e.getMessage());
                }
            }

        } catch (IOException e) {
            System.err.println("Error fatal leyendo users.txt: " + e.getMessage());
        }

        return users;
    }
    public void saveAccounts(List<BankAccount> accounts) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ACCOUNTSFILEPATH))) {

            for (BankAccount account : accounts) {
                StringBuilder line = new StringBuilder();
                String clientID = account.getClient().getIdentification();

                line.append(account.getAccountNumber()).append(SEPARATOR);
                line.append(account.getBalance()).append(SEPARATOR);
                line.append(clientID).append(SEPARATOR);

                if (account instanceof SavingsAccount) {
                    line.insert(0, "SAVINGS" + SEPARATOR);
                    SavingsAccount sa = (SavingsAccount) account;
                    line.append(sa.getInterestRate());

                } else if (account instanceof CheckingAccount) {
                    line.insert(0, "CHECKING" + SEPARATOR);
                    CheckingAccount ca = (CheckingAccount) account;
                    line.append(ca.getOverdraftLimit());

                } else if (account instanceof BusinessAccount) {
                    line.insert(0, "BUSINESS" + SEPARATOR);
                    BusinessAccount ba = (BusinessAccount) account;
                    line.append(ba.getMinimumBalanceRequirement()).append(SEPARATOR);
                    line.append(ba.getFeeBelowMinimum());
                }
                writer.write(line.toString());
                writer.newLine();
            }

        } catch (IOException e) {
            System.err.println("Error escribiendo en accounts.txt: " + e.getMessage());
        }
    }
    public List<BankAccount> loadAccounts(List<User> allUsers) {
        List<BankAccount> loadedAccounts = new ArrayList<>();
        Path path = Paths.get(ACCOUNTSFILEPATH);

        if (!Files.exists(path)) {
            System.out.println("No se encontró 'accounts.txt'. Asumiendo primer inicio.");
            return loadedAccounts;
        }
        Map<String, Client> clientMap = new HashMap<>();
        for (User user : allUsers) {
            if (user instanceof Client) {
                clientMap.put(user.getIdentification(), (Client) user);
            }
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(ACCOUNTSFILEPATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) continue;

                try {
                    String[] parts = line.split(SEPARATOR);

                    String type = parts[0];
                    String accountNumber = parts[1];
                    double balance = Double.parseDouble(parts[2]);
                    String clientID = parts[3];
                    Client owner = clientMap.get(clientID);
                    if (owner == null) {
                        System.err.println("Cuenta huérfana encontrada: " + accountNumber + ". No se pudo vincular al cliente ID: " + clientID);
                        continue;
                    }
                    BankAccount newAccount = null;
                    switch (type) {
                        case "SAVINGS":
                            double interestRate = Double.parseDouble(parts[4]);
                            newAccount = new SavingsAccount(accountNumber, balance, owner, interestRate);
                            break;

                        case "CHECKING":
                            double overdraftLimit = Double.parseDouble(parts[4]);
                            newAccount = new CheckingAccount(accountNumber, balance, owner, overdraftLimit);
                            break;

                        case "BUSINESS":
                            double minBalance = Double.parseDouble(parts[4]);
                            double fee = Double.parseDouble(parts[5]);
                            newAccount = new BusinessAccount(accountNumber, balance, owner, minBalance, fee);
                            break;

                        default:
                            System.err.println("Tipo de cuenta desconocido en accounts.txt: " + type);
                    }
                    if (newAccount != null) {
                        owner.addAccount(newAccount);
                        loadedAccounts.add(newAccount);
                    }

                } catch (Exception e) {
                    System.err.println("Error parseando línea en accounts.txt: '" + line + "' - Error: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error fatal leyendo accounts.txt: " + e.getMessage());
        }
        return loadedAccounts;
    }
    public void saveTransactions(List<BankAccount> allAccounts) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TRANSACTIONSFILEPATH))) {

            for (BankAccount account : allAccounts) {
                List<Transaction> transactions = account.getTransactions();

                for (Transaction tx : transactions) {
                    StringBuilder line = new StringBuilder();

                    line.append(account.getAccountNumber()).append(SEPARATOR);
                    line.append(tx.getTimestamp().toString()).append(SEPARATOR);
                    line.append(tx.getType()).append(SEPARATOR);
                    line.append(tx.getAmount());

                    writer.write(line.toString());
                    writer.newLine();
                }
            }

        } catch (IOException e) {
            System.err.println("Error escribiendo en transactions.txt: " + e.getMessage());
        }
    }
    public void loadTransactions(List<BankAccount> allAccounts) {
        Path path = Paths.get(TRANSACTIONSFILEPATH);

        if (!Files.exists(path)) {
            System.out.println("No se encontró 'transactions.txt'. Asumiendo primer inicio.");
            return;
        }

        Map<String, BankAccount> accountMap = new HashMap<>();
        for (BankAccount account : allAccounts) {
            accountMap.put(account.getAccountNumber(), account);
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(TRANSACTIONSFILEPATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) continue;

                try {
                    String[] parts = line.split(SEPARATOR);
                    String accountNumber = parts[0];
                    LocalDateTime timestamp = LocalDateTime.parse(parts[1]);
                    String type = parts[2];
                    double amount = Double.parseDouble(parts[3]);

                    BankAccount ownerAccount = accountMap.get(accountNumber);

                    if (ownerAccount != null) {
                        Transaction loadedTx = new Transaction(amount, type, accountNumber, timestamp);
                        ownerAccount.restoreTransaction(loadedTx);

                    } else {
                        System.err.println("Transacción huérfana encontrada. No se pudo vincular a la cuenta: " + accountNumber);
                    }

                } catch (Exception e) {
                    System.err.println("Error parseando línea en transactions.txt: '" + line + "' - Error: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error fatal leyendo transactions.txt: " + e.getMessage());
        }
    }
}
