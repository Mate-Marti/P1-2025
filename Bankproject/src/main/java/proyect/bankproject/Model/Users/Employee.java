package proyect.bankproject.Model.Users;

import java.time.LocalDate;

public abstract class Employee extends User {
    private String employeeID;
    private double salary;

    protected Employee(String identification, String name, String lastName, LocalDate birthDate, String username, String passwordHash,
                       String employeeID, double salary) {

        super(identification, name, lastName, username, birthDate, passwordHash);
        this.employeeID = employeeID;
        this.salary = salary;
    }
    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
