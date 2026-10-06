package com.model.data;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.model.user.User;

public class DataWriter {
    
    public static void saveUsers(ArrayList<User> users) {
        // Implement logic to save users to JSON file
        JSONArray usersArray = new JSONArray();

        for (User user : users) {
            JSONObject userObject = new JSONObject();

            userObject.put("firstName", user.getFirstName());
            userObject.put("lastName", user.getLastName());
            userObject.put("username", user.getUsername());
            userObject.put("password", user.getPassword());
            userObject.put("email", user.getEmail());
            userObject.put("phoneNumber", user.getPhoneNumber());
            userObject.put("id", user.getId().toString());

            // This becomes the "requests": [ ... ] part
            JSONArray requestsArray = new JSONArray();

            for (String request : user.getRequests()) {
                requestsArray.add(request);
            }

            userObject.put("requests", requestsArray);

            // Add this completed user to the main JSON array
            usersArray.add(userObject);
        }

        // Write the completed array into users.json
        try (FileWriter file = new FileWriter(DataConstants.USERS_FILE)) {
            file.write(usersArray.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveShelters(HashMap<UUID, Shelter> shelters) {
    JSONArray sheltersArray = new JSONArray();

    for (Shelter shelter : shelters.values()) {
        JSONObject shelterObject = new JSONObject();

        shelterObject.put("id", shelter.getId().toString());
        shelterObject.put("name", shelter.getName());
        shelterObject.put("address", shelter.getLocation().getAddress());
        shelterObject.put("capacity", shelter.getCapacity());
        shelterObject.put("status", shelter.getStatus().toString());

        // Makes: "inventory": { "Canned foods": 100, ... }
        JSONObject inventoryObject = new JSONObject();

        for (Map.Entry<String, Integer> item : shelter.getInventory().entrySet()) {
            inventoryObject.put(item.getKey(), item.getValue());
        }

        shelterObject.put("inventory", inventoryObject);

        sheltersArray.add(shelterObject);
    }

    try (FileWriter file = new FileWriter(DataConstants.SHELTERS_FILE)) {
        file.write(sheltersArray.toJSONString());
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    public static void saveRequests(ArrayList<AssistanceRequest> requests) {
        // Implement logic to save assistance requests to JSON file
    }

    public static void saveHurricanes(ArrayList<HurricaneEvent> hurricanes) {
        // Implement logic to save hurricane events to JSON file
    }

    public static void saveVolunteerRequests(ArrayList<VolunteerAccountRequest> volunteerRequests) {
        // Implement logic to save assistance requests for a specific user to JSON file
    }
}
