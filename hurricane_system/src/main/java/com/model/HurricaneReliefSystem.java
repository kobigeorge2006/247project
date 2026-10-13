package com.model;

import com.model.data.DataBase;
import com.model.data.DataLoader;
import com.model.other.Accommodation;
import com.model.other.Shelter;
import com.model.other.ShelterList;
import com.model.other.ShelterStatus;
import com.model.requests.AssistanceRequest;
import com.model.user.User;
import com.model.user.UserList;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Connects the user interface to the Hurricane Relief System.
 */
public class HurricaneReliefSystem {

    private DataBase database;
    private UserList userList;
    private ShelterList shelterList;
    private User currentUser;

    /**
     * Creates the system and loads saved data.
     */
    public HurricaneReliefSystem() {
        database = DataBase.getInstance();

        userList = new UserList();
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
        User user = userList.logIn(username, password);

        if(user == null){
            return false;
        }

            currentUser = user;
            return true;
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
    public boolean signUp(String firstName, String lastName,
                          String username, String password,
                          String email, String phoneNumber) {

        User newUser = userList.signUp(
                firstName, lastName, username,
                password, email, phoneNumber
        );

        if (newUser == null) {
            return false;
        }

        currentUser = newUser;
        return true;
    }

    /**
     * Saves users and shelters before logging out.
     */
    public void logOut() {
        if (currentUser != null) {
            userList.saveUsers();
            shelterList.saveShelters();
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
     * Checks whether the current user is an administrator.
     *
     * @return true if the administrator is logged in
     */
    public boolean isAdmin() {
        return currentUser != null
                && currentUser.getUsername().equalsIgnoreCase("admin");
    }

    /**
     * Finds shelters in a given ZIP code.
     *
     * @param zipCode the ZIP code to search
     * @return shelters matching the ZIP code
     */
    public ArrayList<Shelter> findShelters(String zipCode) {
        return shelterList.findShelters(zipCode);
    }

    /**
     * Creates a shelter with 30 units of water.
     *
     * @param name the shelter's name
     * @param address the shelter's address
     * @param zipCode the shelter's ZIP code
     * @param capacity the shelter's capacity
     * @return true if the shelter was created
     */
    public boolean createShelter(String name, String address,
                                 String zipCode, int capacity) {

        if (!isAdmin()) {
            return false;
        }

        if (name == null || name.isEmpty()
                || address == null || address.isEmpty()
                || zipCode == null || zipCode.isEmpty()
                || capacity <= 0) {
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
    ArrayList<AssistanceRequest> getAllAssistanceRequests() {
        return  DataBase.getInstance().getAssistanceRequests();
    }

}
