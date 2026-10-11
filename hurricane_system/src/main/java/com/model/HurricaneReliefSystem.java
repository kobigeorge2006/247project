
package com.model;

import com.model.user.*;
import com.model.data.*;
import com.model.other.*;
import com.model.requests.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class HurricaneReliefSystem {
    private HurricaneReliefSystem instance;
    private ArrayList<User> users;
    private HashMap<UUID, Shelter> shelters;
    private ArrayList<AssistanceRequest> assistanceRequests;
    private ArrayList<HurricaneEvent> hurricanes;
    private DataBase database;
    private User currentUser;
    private ShelterList shelterList;

    public HurricaneReliefSystem() {
        this.users = new ArrayList<>();
        this.shelters = new HashMap<>();
        this.assistanceRequests = new ArrayList<>();
        this.hurricanes = new ArrayList<>();
        database = DataBase.getInstance();
        currentUser = null;
        shelterList = new ShelterList();
    }

    public HurricaneReliefSystem getInstance() {
        if (instance == null) {
            instance = new HurricaneReliefSystem();
        }
        return instance;
    }

    public boolean logIn(String username, String password) {
        ArrayList<User> matchingUsers = database.getUser(username, password);

        if (matchingUsers == null) {
            return false;
        }

        for (User user : matchingUsers) {
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

        ArrayList<User> matchingUsers = database.getUser(username, password);

        if (matchingUsers != null) {
            for (User user : matchingUsers) {
                if (user.getUsername().equalsIgnoreCase(username)) {
                    return false;
                }
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
        currentUser = newUser;

        return true;
    }

    public void logOut() {
        currentUser = null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }

    // Finds shelters in a given ZIP code
    public ArrayList<Shelter> findShelters(String zipCode) {
        return shelterList.findShelters(zipCode);
    }

    // Creates a shelter and adds 30 units of water
    public boolean createShelter(String name, String address,
                                 String zipCode, int capacity) {
        if (!isLoggedIn()) {
            return false;
        }

        Shelter shelter = new Shelter(
            UUID.randomUUID(),
            name,
            address,
            capacity,
            ShelterStatus.OPEN,
            new ArrayList<Accommodation>()
        );

        shelter.getLocation().setZipCode(zipCode);
        shelter.getInventory().put("Water", 30);

        return shelterList.addShelter(shelter);
    }
}