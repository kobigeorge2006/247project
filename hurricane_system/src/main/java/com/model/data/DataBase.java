package com.model.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import com.location.Address;
import com.model.other.HurricaneEvent;
import com.model.other.Shelter;
import com.model.requests.AidCategory;
import com.model.requests.AssistanceRequest;
import com.model.requests.RequestStatus;
import com.model.requests.Severity;
import com.model.user.User;

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

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<User> getUser(String username, String password) {
        ArrayList<User> matchingUsers = new ArrayList<>();

        for (User user : users) {
            if (user != null
                    && user.getUsername() != null
                    && user.getPassword() != null
                    && user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                matchingUsers.add(user);
            }
        }

        return matchingUsers;
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

    public void addAssistanceRequest(AssistanceRequest request) {
        if (request == null) {
            return;
        }

        for (int i = 0; i < assistanceRequests.size(); i++) {
            AssistanceRequest existing = assistanceRequests.get(i);
            if (existing != null
                    && request.getId() != null
                    && request.getId().equals(existing.getId())) {
                assistanceRequests.set(i, request);
                DataWriter.saveRequests(assistanceRequests);
                return;
            }
        }

        assistanceRequests.add(request);
        DataWriter.saveRequests(assistanceRequests);
    }

    public void addAssistanceRequest(UUID id, String contactInfo, Address address,
                                   AidCategory aidCategory, Severity severity,
                                   RequestStatus status, String message,
                                   Address location, boolean hasPet) {
        addAssistanceRequest(new AssistanceRequest(id, contactInfo, address,
                aidCategory, severity, status, message, location, hasPet));
    }

    public void addHurricane(HurricaneEvent hurricane) {
        if (hurricane != null) {
            hurricaneEvents.add(hurricane);
        }
    }

    public void addShelter(Shelter shelter) {
        if (shelter != null && shelter.getId() != null) {
            shelters.put(shelter.getId(), shelter);
        }
    }
}