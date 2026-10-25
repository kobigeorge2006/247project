package com.model.user;
import java.util.ArrayList;
import java.util.HashMap;
import com.model.requests.*;
import com.model.data.DataBase;
import com.model.other.*;

import javafx.scene.chart.PieChart.Data;


/**
 * A system user who can manage volunteers, assistance requests, and a shelter.
 */
public class Administrator extends User {
    private final Shelter shelter;

    /**
     * Creates an administrator associated with a shelter.
     *
     * @param firstName the administrator's first name
     * @param lastName the administrator's last name
     * @param username the administrator's username
     * @param password the administrator's password
     * @param email the administrator's email address
     * @param phoneNumber the administrator's phone number
     * @param shelter the shelter managed by this administrator
     */
    public Administrator(String firstName, String lastName, String username, String password, String email, String phoneNumber, Shelter shelter) {

        super(firstName, lastName, username, password, email, phoneNumber, false);

        this.shelter = shelter;
    
    }

    /**
     * Adds a validated volunteer to the shared user database.
     *
     * @param volunteer the volunteer to add
     */
    public void addVolunteer(ValidatedVolunteer volunteer) {
        DataBase.getInstance().addUser(volunteer);
    }
    
    /**
     * Finds a volunteer by username and password.
     *
     * @param username the volunteer's username
     * @param password the volunteer's password
     * @return the matching volunteer, or {@code null} if no user matches
     */
    public ValidatedVolunteer getVolunteer(String username, String password) {
        // Implement logic to retrieve a volunteer
        if(DataBase.getInstance().getUser(username, password) != null){
            return (ValidatedVolunteer) DataBase.getInstance().getUser(username, password).get(0);
        }
        return null;
    }

    /**
     * Changes the capacity of this administrator's shelter.
     *
     * @param capacity the new shelter capacity
     */
    public void updateCapacity(int capacity){
        // Implement logic to update shelter capacity
        shelter.setCapacity(capacity);
    }

    /**
     * Prints each volunteer currently stored in the user database.
     */
    public void viewVolunteers(){
        // Implement logic to view all volunteers
        // Need to add toString() to ValidatedVolunteer class to display volunteer information
        ArrayList<User> allUsers = DataBase.getInstance().getUsers();
        for(User user : allUsers){
            if(user.isVolunteer()){
                System.out.println(user);
            }
    }
}

    /**
     * Adds a shelter to the shared database.
     *
     * @param shelter the shelter to add
     */
    public void addShelter(Shelter shelter){
        DataBase database = DataBase.getInstance();
        database.addShelter(shelter);
    }

    /**
     * Returns the shelter managed by this administrator.
     *
     * @return the managed shelter
     */
    public Shelter getShelter() {
        return shelter;
    }

    /**
     * Returns all assistance requests in the shared database.
     *
     * @return the assistance requests
     */
    public ArrayList<AssistanceRequest> getAllAssistanceRequests() {
        return DataBase.getInstance().getAssistanceRequests();
    }
}