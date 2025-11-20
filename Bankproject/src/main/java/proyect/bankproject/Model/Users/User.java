package proyect.bankproject.Model.Users;

import java.time.LocalDate;
import java.time.Period;

public abstract class User {
    private String identification;
    private String name;
    private String lastName;
    private String username;
    private LocalDate birthDate;
    private String passwordHash;

    protected User(String identification, String name, String lastName, String username, LocalDate birthDate, String passwordHash) {
        this.identification = identification;
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.birthDate = birthDate;
        this.passwordHash = passwordHash;
    }


    public String getIdentification() {

        return identification;
    }
    public void setIdentification(String identification) {

        this.identification = identification;
    }
    public String getName() {

        return name;
    }
    public void setName(String name) {

        this.name = name;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    public int getAge() {
        if (this.birthDate == null) {
            return 0;
        }
        return Period.between(this.birthDate, LocalDate.now()).getYears();
    }
    public boolean checkPassword(String password) {
        return this.passwordHash.equals(password);
    }
    public String getPasswordHash() {
        return passwordHash;
    }
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
    public abstract String getRole();
}
