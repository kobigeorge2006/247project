package com.model.user;
import java.util.HashMap;
public class Administrator {
    private String name;
    private String username;
    private String password;
    private Shelter shelter;
    private Severity severity;
    private HashMap<String, ValidatedVolunteer> requests;

    public Administrator(String name, String username, String password, Shelter shelter, Severity severity) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.shelter = shelter;
        this.severity = severity;
        this.requests = new HashMap<>();
    }
    public void addVolunteer(ValidatedVolunteer volunteer) {
        // Implement logic to add a volunteer
    }
    public void removeVolunteer(String name){
        // Implement logic to remove a volunteer
    }
    public ValidatedVolunteer getVolunteer(String name) {
        // Implement logic to retrieve a volunteer
    }
    public void updateCapacity(int capacity){
        // Implement logic to update shelter capacity
    }
    public void viewVolunteers(){
        // Implement logic to view all volunteers
    }
    public void addShelter(Shelter shelter) {
        // Implement logic to add a shelter
    }
    public void removeShelter(Shelter shelter) {
        // Implement logic to remove a shelter
    }
    public void manageAdministrators() {
        // Implement logic to manage administrators
    }
    public void setRequestSeverity(Severity severity){
        // Implement logic to set request severity
    }
}
