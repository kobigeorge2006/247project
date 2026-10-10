package com.model;
import com.model.user.*;
import com.model.data.*;
import com.model.other.*;
import java.util.ArrayList;
import java.util.HashMap;
import com.model.requests.*;
import java.util.UUID;

public class HurricaneReliefSystem {
    private HurricaneReliefSystem instance;
    private ArrayList<User> users;
    private HashMap<UUID, Shelter> shelters;
    private ArrayList<AssistanceRequest> assistanceRequests;
    private ArrayList<HurricaneEvent> hurricanes;
    private DataBase database;
    private User currentUser;

    public HurricaneReliefSystem() {
        this.users = new ArrayList<>();
        this.shelters = new HashMap<>();
        this.assistanceRequests = new ArrayList<>();
        this.hurricanes = new ArrayList<>();
        database = DataBase.getInstance();
        currentUser = null;
    }

    public HurricaneReliefSystem getInstance() {
        if (instance == null) {
            instance = new HurricaneReliefSystem();
        }
        return instance;
    }

    public boolean logIn(String username, String password) {
        for (User user : database.getUser(username, password)) {
            if (user.getUsername().equalsIgnoreCase(username)
                    && user.getPassword().equals(password)) {

                currentUser = user;
                return true;
            }
        }
        return false;
    }
    public boolean signUp(String firstName, String lastName, String username,
                          String password, String email, String phoneNumber) {

        // Make sure the username is not already taken
        for (User user : database.getUser(username, password)) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return false; // Username already exists
            }    
        }       

        User newUser = new User(
            firstName,
            lastName,
            username,
            password,
            email,
            phoneNumber,
            new ArrayList<String>()
        );

        database.addUser(newUser);

        // Automatically sign in the new account
        currentUser = newUser;

        return true;
    }


    public void logOut() {
        currentUser = null;
        System.out.println("User logged out successfully.");
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }
}


  


    

