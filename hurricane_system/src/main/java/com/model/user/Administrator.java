package com.model.user;
import java.util.ArrayList;
import java.util.HashMap;
import com.model.requests.*;
import com.model.data.DataBase;
import com.model.other.*;
import com.model.user.*;

import javafx.scene.chart.PieChart.Data;


public class Administrator extends User {
    private final Shelter shelter;

    public Administrator(String firstName, String lastName, String username, String password, String email, String phoneNumber, Shelter shelter) {

        super(firstName, lastName, username, password, email, phoneNumber, false);

        this.shelter = shelter;
    }
    public void addVolunteer(ValidatedVolunteer volunteer) {
        DataBase.getInstance().addUser(volunteer);
    }
    
    public ValidatedVolunteer getVolunteer(String username, String password) {
        // Implement logic to retrieve a volunteer
        if(DataBase.getInstance().getUser(username, password) != null){
            return (ValidatedVolunteer) DataBase.getInstance().getUser(username, password).get(0);
        }
        return null;
    }
    public void updateCapacity(int capacity){
        // Implement logic to update shelter capacity
        shelter.setCapacity(capacity);
    }
    public void viewVolunteers(){
        // Implement logic to view all volunteers
        // Need to add toString() to ValidatedVolunteer class to display volunteer information
        for(User user : DataBase.getInstance().getUsers()){
            if(user.isVolunteer()){
                System.out.println(user);
            }
    }
    public void addShelter(Shelter shelter) {
        // Implement logic to add a shelter
        DataBase.getInstance().addShelter(shelter);
    }
    public Shelter getShelter() {
        return shelter;
    }
    public ArrayList<AssistanceRequest> getAllAssistanceRequests() {
        return DataBase.getInstance().getAssistanceRequests();
    }
}
