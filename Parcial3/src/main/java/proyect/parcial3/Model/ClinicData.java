package proyect.parcial3.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.time.LocalTime;


public class ClinicData {

    private static ClinicData instance;
    private ArrayList<Doctor> doctors;
    private ArrayList<Patient> patients;
    private ArrayList<Appointment> appointments;

    private ClinicData() {
        doctors = new ArrayList<>();
        patients = new ArrayList<>();
        appointments = new ArrayList<>();

        initTestData();
    }
    public static ClinicData getInstance() {
        if (instance == null) {
            instance = new ClinicData();
        }
        return instance;
    }
    public ArrayList<Doctor> getDoctors() {
        return doctors;
    }
    public ArrayList<Patient> getPatients() {
        return patients;
    }
    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    private void initTestData() {
        Doctor d1 = new Doctor("Gregory House", "M", "55", "1001", "LIC-999", "Diagnóstico");
        Doctor d2 = new Doctor("Meredith Grey", "F", "35", "1002", "LIC-888", "Cirugía General");
        Doctor d3 = new Doctor("Strange", "M", "45", "1003", "LIC-777", "Neurología");

        doctors.add(d1);
        doctors.add(d2);
        doctors.add(d3);

        Patient p1 = new Patient("Pepito Pérez", "M", "25", "2001", "O+", "Sanitas");
        Patient p2 = new Patient("Maria Lopez", "F", "30", "2002", "A-", "Sura");
        Patient p3 = new Patient("Juan Valdes", "M", "60", "2003", "AB+", "Nueva EPS");

        patients.add(p1);
        patients.add(p2);
        patients.add(p3);

        appointments.add(new Appointment(p1, d1, LocalDate.now(), LocalTime.of(10, 0), 50000));
        appointments.add(new Appointment(p2, d2, LocalDate.now().plusDays(1), LocalTime.of(14, 30), 80000));
        appointments.add(new Appointment(p3, d3, LocalDate.of(2023, 12, 25), LocalTime.of(9, 0), 120000));
    }
}
