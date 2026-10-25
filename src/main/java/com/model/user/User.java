package com.model.user;

import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.model.requests.AidCategory;
import com.model.requests.AssistanceRequest;
import com.model.requests.Message;
import com.model.requests.RequestStatus;
import com.location.Address;
import com.model.data.DataBase;
import com.model.requests.Severity;
import com.model.other.Location;




/**
 * Stores information about a user in the Hurricane Relief System.
 */
public class User {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private boolean volunteer;
    private ArrayList<String> requests;
    private UUID id;
    private DataBase database;

    /**
     * Creates a new user.
     *
     * @param firstName the user's first name
     * @param lastName the user's last name
     * @param username the user's username
     * @param password the user's password
     * @param email the user's email
     * @param phoneNumber the user's phone number
     * @param volunteer whether the user is registered as a volunteer
     * @param requests the user's requests
     */
    public User(String firstName, String lastName, String username,
                String password, String email, String phoneNumber, boolean volunteer,
                ArrayList<String> requests) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.requests = requests;
        this.volunteer = volunteer;
        this.id = UUID.randomUUID();
        this.database = DataBase.getInstance();
    }
    /**
     * Creates a user with an empty request list.
     *
     * @param firstName the user's first name
     * @param lastName the user's last name
     * @param username the user's username
     * @param password the user's password
     * @param email the user's email address
     * @param phoneNumber the user's phone number
     * @param volunteer whether the user is registered as a volunteer
     */
    public User(String firstName, String lastName, String username, String password, String email, String phoneNumber, boolean volunteer) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.requests = new ArrayList<>();
        this.volunteer = volunteer;
        this.id = UUID.randomUUID();
    }

    /**
     * Creates a user from its JSON representation.
     *
     * @param jsonUser JSON fields for the user
     */
    public User(JSONObject jsonUser) {
        this.firstName = (String) jsonUser.get("firstName");
        this.lastName = (String) jsonUser.get("lastName");
        this.username = (String) jsonUser.get("username");
        this.password = (String) jsonUser.get("password");
        this.email = (String) jsonUser.get("email");
        this.phoneNumber = (String) jsonUser.get("phoneNumber");

        this.requests = new ArrayList<>();

        JSONArray jsonRequests = (JSONArray) jsonUser.get("requests");

        if (jsonRequests != null) {
            for (Object request : jsonRequests) {
                this.requests.add((String) request);
            }
        }

        String savedId = (String) jsonUser.get("id");
        this.id = savedId == null
                ? UUID.randomUUID()
                : UUID.fromString(savedId);
    }

    /**
     * Creates and stores an assistance request in the shared database.
     *
     * @param id unique request identifier
     * @param contactInfo requester's contact information
     * @param user user associated with the request (currently unused)
     * @param address requester's address
     * @param aidCategory requested category of aid
     * @param severity urgency of the request
     * @param status initial request status
     * @param message request description
     * @param location location where assistance is needed
     * @param hasPet whether the requester has a pet
     */
    public void addRequest(UUID id, String contactInfo, User user, Address address, AidCategory aidCategory, Severity severity, RequestStatus status, String message,Address location, boolean hasPet ){
        // Implement request addition logic here
        DataBase database = DataBase.getInstance();
        AssistanceRequest request =  new AssistanceRequest(id,contactInfo, address, aidCategory, severity,status, message, location, hasPet);
        database.addAssistanceRequest(request);
    }

    /**
     * Returns the user's first name.
     *
     * @return the user's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Updates the user's first name.
     *
     * @param firstName the user's first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the user's last name.
     *
     * @return the user's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Updates the user's last name.
     *
     * @param lastName the user's last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the user's username.
     *
     * @return the user's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Updates the user's username.
     *
     * @param username the user's username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the user's password.
     *
     * @return the user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Updates the user's password.
     *
     * @param password the user's password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the user's email address.
     *
     * @return the user's email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Updates the user's email address.
     *
     * @param email the user's email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the user's phone number.
     *
     * @return the user's phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Updates the user's phone number.
     *
     * @param phoneNumber the user's phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns the identifiers or descriptions of this user's requests.
     *
     * @return the user's requests
     */
    public ArrayList<String> getRequests() {
        return requests;
    }

    /**
     * Replaces this user's request list.
     *
     * @param requests the user's requests
     */
    public void setRequests(ArrayList<String> requests) {
        this.requests = requests;
    }

    /**
     * Returns the user's unique identifier.
     *
     * @return the user's identifier
     */
    public UUID getId() {
        return id;
    }

    /**
     * Updates the user's unique identifier.
     *
     * @param id the user's unique identifier
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Sets whether this user is registered as a volunteer.
     *
     * @param volunteer {@code true} if the user is a volunteer
     */
    public void setVolunteer(boolean volunteer) {
        this.volunteer = volunteer;
    }

    /**
     * Indicates whether this user is registered as a volunteer.
     *
     * @return {@code true} if the user is a volunteer
     */
    public boolean isVolunteer() {
        return volunteer;
    }
    
    /**
     * Returns a string containing the user's non-password account details.
     *
     * @return the user description
     */
    @Override 
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", requests=" + requests +
                ", id=" + id +
                '}';
    }
}