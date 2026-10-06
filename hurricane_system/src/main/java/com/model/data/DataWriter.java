package com.model.data;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
        // Implement logic to save shelters to JSON file
        JSONArray sheltersArray = new JSONArray();

        for (User shelter : sheltersArray) {
            JSONObject shelterObject = new JSONObject();

            shelterObject.put("firstName", shelter.getFirstName());
            shelterObject.put("lastName", shelter.getLastName());
            shelterObject.put("username", shelter.getUsername());
            shelterObject.put("password", shelter.getPassword());
            shelterObject.put("email", shelter.getEmail());
            shelterObject.put("phoneNumber", shelter.getPhoneNumber());
            shelterObject.put("id", shelter.getId().toString());

            // This becomes the "requests": [ ... ] part
            JSONArray requestsArray = new JSONArray();

            for (String request : shelter.getRequests()) {
                requestsArray.add(request);
            }

            shelterObject.put("requests", requestsArray);

            // Add this completed user to the main JSON array
            sheltersArray.add(userObject);
        }

        // Write the completed array into users.json
        try (FileWriter file = new FileWriter(DataConstants.SHELTERS_FILE)) {
            file.write(usersArray.toJSONString());
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
