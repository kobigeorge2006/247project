package com.model.user;
import java.util.ArrayList;
import java.util.UUID;


import org.json.simple.JSONObject;

import com.model.requests.AidCategory;
import com.model.requests.AssistanceRequest;
import com.model.requests.Message;
import com.model.requests.RequestStatus;
import com.model.data.DataBase;
import com.model.requests.Severity;
import com.model.other.Location;

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

    public User(String firstName, String lastName, String username, String password, String email, String phoneNumber, boolean volunteer, ArrayList<String> requests) {
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
    
    public User(JSONObject jsonUser) {
        this.firstName = (String) jsonUser.get("firstName");
        this.lastName = (String) jsonUser.get("lastName");
        this.username = (String) jsonUser.get("username");
        this.password = (String) jsonUser.get("password");
        this.email = (String) jsonUser.get("email");
        this.phoneNumber = (String) jsonUser.get("phoneNumber");
        this.requests = (ArrayList<String>) jsonUser.get("requests");
        this.id = UUID.fromString((String) jsonUser.get("id"));
    }

    public void addRequest(UUID id, String contactInfo, User user, String address, AidCategory aidCategory, Severity severity, RequestStatus status, ArrayList<Message> messages,Location location ){
        // Implement request addition logic here
        DataBase database = DataBase.getInstance();
        AssistanceRequest request =  new AssistanceRequest(id,contactInfo, address, aidCategory, severity,status, messages, location);
        database.addAssistanceRequest(request);
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public ArrayList<String> getRequests() {
        return requests;
    }
    public void setRequests(ArrayList<String> requests) {
        this.requests = requests;
    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public void setVolunteer(boolean volunteer) {
        this.volunteer = volunteer;
    }
    public boolean isVolunteer() {
        return volunteer;
    }
    
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
