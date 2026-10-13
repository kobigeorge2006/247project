package com.model.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import com.model.user.*;
import com.model.other.*;
import com.model.requests.*;

/**
 * Stores the data used by the Hurricane Relief System.
 *
 * @author Kobi George
 */
public class DataBase {

    private static DataBase instance;

    private ArrayList<User> users;
    private HashMap<UUID, Shelter> shelters;
    private ArrayList<AssistanceRequest> assistanceRequests;
    private ArrayList<HurricaneEvent> hurricaneEvents;

    /**
     * Creates an empty database.
     */
    private DataBase() {
        users = new ArrayList<>();
        shelters = new HashMap<>();
        assistanceRequests = new ArrayList<>();
        hurricaneEvents = new ArrayList<>();
    }

    /**
     * Returns the single database instance.
     *
     * @return the database instance
     */
    public static DataBase getInstance() {
        if (instance == null) {
            instance = new DataBase();
        }

        return instance;
    }

    /**
     * Finds a user with the given username and password.
     *
     * @param username the user's username
     * @param password the user's password
     * @return a list containing the matching user, or null if not found
     */
    public ArrayList<User> getUser(String username, String password) {
        ArrayList<User> matchingUsers = new ArrayList<>();

        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)
                    && user.getPassword().equals(password)) {
                matchingUsers.add(user);
            }
        }

        if (matchingUsers.isEmpty()) {
            return null;
        }

        return matchingUsers;
    }

    /**
     * Returns all registered users.
     *
     * @return the list of users
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * Loads users into the database.
     *
     * @param loadedUsers the users loaded from JSON
     */
    public void setUsers(ArrayList<User> loadedUsers) {
        users.clear();

        if (loadedUsers != null) {
            users.addAll(loadedUsers);
        }
    }

    /**
     * Adds a new user to the database.
     *
     * @param user the user to add
     */
    public void addUser(User user) {
        users.add(user);
    }

    /**
     * Returns all shelters.
     *
     * @return the shelters organized by their IDs
     */
    public HashMap<UUID, Shelter> getShelters() {
        return shelters;
    }

    /**
     * Loads shelters into the database.
     *
     * @param loadedShelters the shelters loaded from JSON
     */
    public void setShelters(HashMap<UUID, Shelter> loadedShelters) {
        shelters.clear();

        if (loadedShelters != null) {
            shelters.putAll(loadedShelters);
        }
    }

    public void addShelter(Shelter shelter) {
        shelters.put(shelter.getId(), shelter);
    }

    /**
     * Returns all assistance requests.
     *
     * @return the list of assistance requests
     */
    public ArrayList<AssistanceRequest> getAssistanceRequests() {
        return assistanceRequests;
    }

    /**
     * Returns all hurricane events.
     *
     * @return the list of hurricane events
     */
    public ArrayList<HurricaneEvent> getHurricaneEvents() {
        return hurricaneEvents;
    }
}