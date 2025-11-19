package proyect.parcial3.Model;

public abstract class Person {
    private String fullName;
    private String genre;
    private String age;
    private String identification;
    public Person(String fullName, String genre, String age, String identification) {
        this.fullName = fullName;
        this.genre = genre;
        this.age = age;
        this.identification = identification;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getIdentification() {
        return identification;
    }
    public void setIdentification(String identification) {
        this.identification = identification;
    }

    @Override
    public String toString() {
        return fullName + " " +  genre + " " + age + " " + identification;
    }
}
