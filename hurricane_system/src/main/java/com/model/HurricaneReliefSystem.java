package com.model;

import com.model.data.DataBase;
import com.model.data.DataLoader;
import com.model.data.DataWriter;
import com.model.other.Accommodation;
import com.model.other.Shelter;
import com.model.other.ShelterList;
import com.model.other.ShelterStatus;
import com.model.user.User;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Connects the user interface to the Hurricane Relief System.
 */
public class HurricaneReliefSystem {

    private DataBase database;
    private User currentUser;
    private ShelterList shelterList;

    /**
     * Creates the system and loads saved users and shelters.
     */
    public HurricaneReliefSystem() {
        database = DataBase.getInstance();

        database.setUsers(DataLoader.loadUsers());
        database.setShelters(DataLoader.loadShelters());

        shelterList = new ShelterList();
        currentUser = null;
    }

    /**
     * Logs in a registered user.
     *
     * @param username the user's username
     * @param password the user's password
     * @return true if login is successful
     */
    public boolean logIn(String username, String password) {
        for (User user : database.getUsers()) {
            if (user.getUsername().equalsIgnoreCase(username)
                    && user.getPassword().equals(password)) {
                currentUser = user;
                return true;
            }
        }

        return false;
    }

    /**
     * Registers a new user.
     *
     * @param firstName the user's first name
     * @param lastName the user's last name
     * @param username the user's username
     * @param password the user's password
     * @param email the user's email address
     * @param phoneNumber the user's phone number
     * @return true if the account is created
     */
    public boolean signUp(String firstName, String lastName, String username,
                          String password, String email, String phoneNumber) {

        for (User user : database.getUsers()) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return false;
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

    /**
     * Saves users and shelters before logging out.
     */
    public void logOut() {
        if (currentUser != null) {
            DataWriter.saveUsers(database.getUsers());
            DataWriter.saveShelters(database.getShelters());
            currentUser = null;
        }
    }

    /**
     * Returns the currently logged-in user.
     *
     * @return the current user
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Checks whether someone is logged in.
     *
     * @return true if a user is logged in
     */
    public boolean isLoggedIn() {
        return currentUser != null;
    }

    /**
     * Finds shelters in a given ZIP code.
     *
     * @param zipCode the ZIP code to search
     * @return the shelters matching the ZIP code
     */
    public ArrayList<Shelter> findShelters(String zipCode) {
        return shelterList.findShelters(zipCode);
    }

    /**
     * Creates a shelter with 30 units of water.
     * Prevents creating another shelter with the same name,
     * address, and ZIP code.
     *
     * @param name the shelter's name
     * @param address the shelter's street address
     * @param zipCode the shelter's ZIP code
     * @param capacity the shelter's capacity
     * @return true if the shelter is created
     */
    public boolean createShelter(String name, String address,
                                 String zipCode, int capacity) {

        if (!isLoggedIn()) {
            return false;
        }

        for (Shelter existing : shelterList.findShelters(zipCode)) {
            if (existing.getName().equalsIgnoreCase(name)
                    && existing.getAddress().equalsIgnoreCase(address)) {
                return false;
            }
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
        shelter.updateInventory("Water", 30);

        return shelterList.addShelter(shelter);
    }
}