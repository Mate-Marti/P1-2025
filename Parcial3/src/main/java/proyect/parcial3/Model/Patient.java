package proyect.parcial3.Model;

public class Patient extends Person {
    private String bloodType;
    private String eps;

    public Patient(String fullName, String genre, String age, String identification, String bloodType, String eps) {
        super(fullName, genre, age, identification);
        this.bloodType = bloodType;
        this.eps = eps;
    }
    public String getBloodType() {
        return bloodType;
    }
    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }
    public String getEps() {
        return eps;
    }
    public void setEps(String eps) {
        this.eps = eps;
    }

    @Override
    public String toString() {
        return getFullName() + " " + eps ;
    }
}
