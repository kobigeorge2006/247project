package com.model.user;
import java.util.ArrayList;
import java.util.UUID;

public class User {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private ArrayList<String> requests;
    private UUID id;
    private User Instance;

    public User(String firstName, String lastName, String username, String password, String email, String phoneNumber, ArrayList<String> requests) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.requests = requests;
        this.id = UUID.randomUUID();
    }

    public void signIn(String userName, String password) {
        // Implement sign-in logic here
    }
    public void signOut() {
        // Implement sign-out logic here
    }
    public boolean addRequest(UUID id, String contactInfo, user user, String address, AidCategory aidCategry){
        // Implement request addition logic here
    }
    public boolean updateProfile(String firstName, String lastName, String email, String phoneNumber) {
        // Implement profile update logic here
    }
    public boolean submitRequest(String contactInfo, String address, AidCategory aidCategory) {
        // Implement request submission logic here
    }
    public ArrayList<AssistanceRequest> viewRequests() {
        // Implement request viewing logic here
    }
    public boolean cancelRequest(UUID requestId) {
        // Implement request cancellation logic here
    }
    public RequestStatus getRequestStatus(UUID requestId) {
        // Implement request status checking logic here
    }
    public boolean updateContactInfo(String email, String phoneNumber) {
        // Implement contact information update logic here
    }
    public User getInstance(){
        //Singleton logic
    }
}
