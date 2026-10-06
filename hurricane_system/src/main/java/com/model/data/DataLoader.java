package com.model.data;
    
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.model.user.User;

public class DataLoader {
    public static ArrayList<User> loadUsers() {
        // Implement logic to load users from JSON file
    }
    
    public static HashMap<UUID, Shelter> loadShelters() {
        // Implement logic to load shelters from JSON file
    }

    public static ArrayList<AssistanceRequest> loadRequests() {
        // Implement logic to load assistance requests from JSON file
    }

    public static ArrayList<HurricaneEvent> loadHurricanes() {
        // Implement logic to load hurricane events from JSON file
    }

    public static ArrayList<VolunteerAccountRequest> loadVolunteerRequests() {
        // Implement logic to load assistance requests for a specific user from JSON file
    }
}
