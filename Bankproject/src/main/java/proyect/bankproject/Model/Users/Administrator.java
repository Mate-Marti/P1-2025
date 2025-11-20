package proyect.bankproject.Model.Users;

import java.time.LocalDate;

public class Administrator extends Employee {
    public Administrator(String identification, String name, String lastName, LocalDate birthDate, String username, String passwordHash,
                         String employeeID, double salary) {
        super(identification, name, lastName, birthDate, username, passwordHash, employeeID, salary);
    }
    @Override
    public String getRole() {
        return "Administrator";
    }
}
