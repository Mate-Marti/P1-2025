package proyect.parcial3.Model;

public class Doctor extends Person {
    private String licenseNumber;
    private String specialization;
   public Doctor (String fullName,String genre, String age, String identification, String licenseNumber, String specialization) {
       super(fullName, genre, age, identification);
       this.licenseNumber = licenseNumber;
       this.specialization = specialization;
   }
   public String getLicenseNumber() {
        return licenseNumber;
   }
   public void setLicenseNumber(String licenseNumber) {
       this.licenseNumber = licenseNumber;
   }
   public String getSpecialization() {
       return specialization;
   }
   public void setSpecialization(String specialization) {
       this.specialization = specialization;
   }
    @Override
    public String toString() {
        return "Dr. " + super.getFullName() + " " + specialization + " " + licenseNumber;
    }
}
