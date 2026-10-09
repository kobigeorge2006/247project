package com.model.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import com.model.user.*;
import com.model.other.*;
import com.model.requests.*;

/**
 * 
 * @author Kobi George
 */

public class DataBase {
    private static DataBase instance;

    private ArrayList<User> users;
    private HashMap<UUID, Shelter> shelters;
    private ArrayList<AssistanceRequest> assistanceRequests;
    private ArrayList<HurricaneEvent> hurricaneEvents;

    private DataBase() {
        users = new ArrayList<>();
        shelters = new HashMap<>();
        assistanceRequests = new ArrayList<>();
        hurricaneEvents = new ArrayList<>();
    }

    public static DataBase getInstance(){
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }

    public ArrayList<User> getUser(String username, String password){
    ArrayList<User> matchingUsers = new ArrayList<>();

    for (User user : users) {
        if (user.getUsername().equals(username)
                && user.getPassword().equals(password)) {
            matchingUsers.add(user);
        }
    }

    return matchingUsers;
} 

    public HashMap<UUID, Shelter> getShelters(){
        return shelters;
    }

    public ArrayList<AssistanceRequest> getAssistanceRequests(){
        return assistanceRequests;
    }
    public void addUser(User user) {
        users.add(user);
    }

}
