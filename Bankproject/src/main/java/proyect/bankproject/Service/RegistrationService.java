package proyect.bankproject.Service;

import proyect.bankproject.Exceptions.DuplicateUserException;
import proyect.bankproject.Model.Accounts.BankAccount;
import proyect.bankproject.Model.Accounts.SavingsAccount;
import proyect.bankproject.Model.Users.Administrator;
import proyect.bankproject.Model.Users.Cashier;
import proyect.bankproject.Model.Users.Client;
import proyect.bankproject.Model.Users.Employee;
import proyect.bankproject.Model.Users.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class RegistrationService {

    private final BankService bankService;

    public RegistrationService() {
        this.bankService = BankService.getInstance();
    }

    public void registerNewClient(String identification, String name, String lastName,
                                  LocalDate birthDate, String username, String plainTextPassword)
            throws DuplicateUserException {

        validateNoDuplicates(identification, username);
        Client newClient = new Client(identification, name, lastName, username, birthDate, plainTextPassword);
        String newAccountNumber = generateRandomAccountNumber();
        BankAccount newAccount = new SavingsAccount(newAccountNumber, 0.0, newClient, 0.02);

        bankService.addUser(newClient);
        bankService.addAccount(newAccount);
        bankService.saveAllData();
        System.out.println("RegistrationService: Cliente " + username + " registrado.");
    }

    public void registerNewEmployee(String id, String name, String lastName, LocalDate birthDate,
                                    String username, String password, double salary, String role)
            throws DuplicateUserException, Exception {

        validateNoDuplicates(id, username);
        User newUser;
        String employeeID = id;

        if (role.equals("Cajero")) {
            newUser = new Cashier(id, name, lastName, birthDate, username, password, employeeID, salary);
        } else if (role.equals("Administrador")) {
            newUser = new Administrator(id, name, lastName, birthDate, username, password, employeeID, salary);
        } else {
            throw new Exception("Rol desconocido: " + role);
        }

        bankService.addUser(newUser);
        bankService.saveAllData();
        System.out.println("RegistrationService: Empleado " + username + " registrado.");
    }

    public void updateEmployee(Employee oldEmployee, String newName, String newLastName,
                               LocalDate newBirthDate, String newPassword, double newSalary, String newRole)
            throws Exception {

        String id = oldEmployee.getIdentification();
        String username = oldEmployee.getUsername();
        String employeeID = oldEmployee.getEmployeeID();

        String finalPassword = newPassword;
        if (newPassword == null || newPassword.isBlank()) {

            finalPassword = oldEmployee.getPasswordHash();
        }

        User updatedEmployee;
        if (newRole.equals("Cajero")) {
            updatedEmployee = new Cashier(id, newName, newLastName, newBirthDate, username, finalPassword, employeeID, newSalary);
        } else if (newRole.equals("Administrador")) {
            updatedEmployee = new Administrator(id, newName, newLastName, newBirthDate, username, finalPassword, employeeID, newSalary);
        } else {
            throw new Exception("Rol desconocido: " + newRole);
        }

        bankService.deleteUser(oldEmployee);
        bankService.addUser(updatedEmployee);
        bankService.saveAllData();
        System.out.println("RegistrationService: Empleado " + username + " actualizado.");
    }

    public void deleteEmployee(Employee employee) throws Exception {
        if (employee == null) {
            throw new Exception("El empleado seleccionado es nulo.");
        }
        if (employee.getUsername().equalsIgnoreCase("admin")) {
            throw new Exception("No se puede eliminar al administrador principal 'admin'.");
        }
        bankService.deleteUser(employee);
        bankService.saveAllData();
        System.out.println("RegistrationService: Empleado " + employee.getUsername() + " eliminado.");
    }

    private void validateNoDuplicates(String identification, String username) throws DuplicateUserException {
        List<User> allUsers = bankService.getAllUsers();
        for (User user : allUsers) {
            if (user != null && user.getIdentification() != null && user.getIdentification().equalsIgnoreCase(identification)) {
                throw new DuplicateUserException("Error: La cédula " + identification + " ya está registrada.");
            }
            if (user != null && user.getUsername() != null && user.getUsername().equalsIgnoreCase(username)) {
                throw new DuplicateUserException("Error: El nombre de usuario " + username + " ya existe.");
            }
        }
    }

    private String generateRandomAccountNumber() {
        Random rand = new Random();
        int part1 = 100 + rand.nextInt(900);
        int part2 = 100 + rand.nextInt(900);
        int part3 = 10 + rand.nextInt(90);
        String accountNumber = String.format("%d-%d-%02d", part1, part2, part3);

        try {
            bankService.findAccountByNumber(accountNumber);
            return generateRandomAccountNumber();
        } catch (Exception e) {
            return accountNumber;
        }
    }
}