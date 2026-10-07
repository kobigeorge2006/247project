package com.model.data;

    
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;

import com.model.user.*;
import com.model.other.*;
import com.model.requests.*;

/**
 * 
 * @author Kobi George
 */

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
            HashMap<UUID, Shelter> shelters = new HashMap<UUID, Shelter>();
            JSONArray jsonShelters = loadData(DataConstants.SHELTERS_FILE);

            for(int i = 0; i < jsonShelters.size(); i++){
                JSONObject jsonShelter = (JSONObject) jsonShelters.get(i);
                Shelter shelter = new Shelter(jsonShelter);
                shelters.put(shelter.getId(), shelter);
            }
            return shelters;
        }

    public static ArrayList<AssistanceRequest> loadRequests() {
            // Implement logic to load assistance requests from JSON file
            ArrayList<AssistanceRequest> requests = new ArrayList<AssistanceRequest>();
            JSONArray jsonRequests = loadData(DataConstants.REQUESTS_FILE);

            for(int i = 0; i < jsonRequests.size(); i++){
                JSONObject jsonRequest = (JSONObject) jsonRequests.get(i);
                requests.add(new AssistanceRequest(jsonRequest));
            }
            return requests;
        }

    public static ArrayList<HurricaneEvent> loadHurricanes() {
            // Implement logic to load hurricane events from JSON file
            ArrayList<HurricaneEvent> hurricanes = new ArrayList<HurricaneEvent>();
            JSONArray jsonHurricanes = loadData(DataConstants.HURRICANES_FILE);

            for(int i = 0; i < jsonHurricanes.size(); i++){
                JSONObject jsonHurricane = (JSONObject) jsonHurricanes.get(i);
                hurricanes.add(new HurricaneEvent(jsonHurricane));
            }
            return hurricanes;
        }

    public static ArrayList<AssistanceRequest> loadVolunteerRequests() {
        // Implement logic to load assistance requests for a specific user from JSON file
        ArrayList<AssistanceRequest> volunteerRequests = new ArrayList<AssistanceRequest>();
        JSONArray jsonVolunteerRequests = loadData(DataConstants.REQUESTS_FILE);

        for(int i = 0; i < jsonVolunteerRequests.size(); i++){
            JSONObject jsonVolunteerRequest = (JSONObject) jsonVolunteerRequests.get(i);
            volunteerRequests.add(new AssistanceRequest(jsonVolunteerRequest));
        }
        return volunteerRequests;
    }
}
