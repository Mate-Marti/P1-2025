package proyect.bankproject.Model.Users;



import java.time.LocalDate;

public class Cashier extends Employee {
    public Cashier(String identification, String name, String lastName, LocalDate birthDate, String username, String passwordHash,
                   String employeeID, double salary) {
        super(identification, name, lastName, birthDate, username, passwordHash, employeeID, salary);
    }
    @Override
    public String getRole(){
        return "Cashier";
    }
}

