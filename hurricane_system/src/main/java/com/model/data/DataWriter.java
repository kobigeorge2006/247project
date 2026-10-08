package com.model.data;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

<<<<<<< HEAD
import com.model.user.User;
import com.model.other.Shelter;
import com.model.requests.AssistanceRequest;
=======
import com.model.user.*;
import com.model.other.*;
import com.model.requests.*;


>>>>>>> origin/main

public class DataWriter {
    /** 
     * @param users
     */
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
        try (FileWriter file = new FileWriter(DataConstants.USER_FILE)) {
            file.write(usersArray.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** 
     * @param shelters
     */
    public static void saveShelters(HashMap<UUID, Shelter> shelters) {
    JSONArray sheltersArray = new JSONArray();

    for (Shelter shelter : shelters.values()) {
        JSONObject shelterObject = new JSONObject();

        shelterObject.put("id", shelter.getId().toString());
        shelterObject.put("name", shelter.getName());
<<<<<<< HEAD
        shelterObject.put("address", shelter.getLocation());
=======
        shelterObject.put("address", shelter.getAddress());
>>>>>>> origin/main
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

    /** 
     * @param requests
     */
    public static void saveRequests(ArrayList<AssistanceRequest> requests) {
        JSONArray requestsArray = new JSONArray();

        for (AssistanceRequest request : requests) {
            JSONObject requestObject = new JSONObject();

            requestObject.put("id", request.getId().toString());
            requestObject.put("contactInfo", request.getContactInfo());
            requestObject.put("address", request.getAddress());
            requestObject.put("aidCategory", request.getAidCategory().toString());
            requestObject.put("severity", request.getSeverity().toString());
            requestObject.put("status", request.getStatus().toString());

            JSONArray messagesArray = new JSONArray();

            for (Message message : AssistanceRequest.getMessages()) {
                JSONObject messageObject = new JSONObject();

                messageObject.put("sender", message.getSender().getUsername());
                messageObject.put("message", message.getBody());
                messageObject.put("timestamp", message.getSender().toString());

                messagesArray.add(messageObject);
            }

            requestObject.put("messages", messagesArray);
            requestsArray.add(requestObject);
        }

        try (FileWriter file =
                new FileWriter(DataConstants.REQUESTS_FILE)) {
            file.write(requestsArray.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** 
     * @param hurricanes
     */
    public static void saveHurricanes(ArrayList<HurricaneEvent> hurricanes) {
        JSONArray hurricanesArray = new JSONArray();
    
        for (HurricaneEvent hurricane : hurricanes) {
            JSONObject hurricaneObject = new JSONObject();
    
            hurricaneObject.put("id", hurricane.getId().toString());
            hurricaneObject.put("status", hurricane.getStatus().toString());
            hurricaneObject.put("location",
                    hurricane.getAddress());
            hurricaneObject.put("maxWindSpeed", hurricane.getMaxWindSpeed());
            hurricaneObject.put("forecastPath", hurricane.getForcastPath());
    
            hurricanesArray.add(hurricaneObject);
        }
    
        try (FileWriter file = new FileWriter(DataConstants.HURRICANES_FILE)) {
            file.write(hurricanesArray.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
