package com.model.data;

    
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;

import com.model.user.User;

public class DataLoader {
        
    private static JSONArray loadData(String fileName) {
            JSONArray jsonArray = new JSONArray();

        try {
            java.io.FileReader reader =
                new java.io.FileReader(DataConstants.JSON_FOLDER + fileName);

            JSONParser parser = new JSONParser();
            jsonArray = (JSONArray) parser.parse(reader);

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonArray;
    }
        
    public static ArrayList<User> loadUsers() {
            // Implement logic to load users from JSON file
            ArrayList<User> users = new ArrayList<User>();
            JSONArray jsonUsers = loadData(DataConstants.USER_FILE);

            for(int i = 0; i < jsonUsers.size(); i++){
                JSONObject jsonUser = (JSONObject) jsonUsers.get(i);
                users.add(new User(jsonUser));
            }
            return users;


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
