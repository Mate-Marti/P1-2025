package proyect.parcial3.Model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {
    private Patient patient;
    private Doctor doctor;
    private LocalDate date;
    private LocalTime time;
    private double price;

    public Appointment(Patient patient, Doctor doctor, LocalDate date, LocalTime time, double price) {
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.time = time;
        this.price = price;
    }


    public Patient getPatient() {
        return patient;
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    public Doctor getDoctor() {
        return doctor;
    }
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public LocalTime getTime() {
        return time;
    }
    public void setTime(LocalTime time) {
        this.time = time;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Date: " + date + " | Dr. " + doctor.getFullName() + " | Pat: " + patient.getFullName();
    }
}
