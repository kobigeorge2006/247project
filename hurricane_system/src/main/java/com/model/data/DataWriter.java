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
import com.model.other.Shelter;
import com.model.other.HurricaneEvent;
import com.model.requests.AssistanceRequest;
import com.model.requests.Message;

/**
 * Saves hurricane relief system data to JSON files.
 */
public class DataWriter {

    /**
     * Saves the users to the user JSON file.
     *
     * @param users the list of users to save
     */
    public static void saveUsers(ArrayList<User> users) {
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

            JSONArray requestsArray = new JSONArray();

            if (user.getRequests() != null) {
                requestsArray.addAll(user.getRequests());
            }

            userObject.put("requests", requestsArray);
            usersArray.add(userObject);
        }

        try (FileWriter file = new FileWriter(
                DataConstants.JSON_FOLDER + DataConstants.USER_FILE)) {
            file.write(usersArray.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves the shelters and their inventory to the shelter JSON file.
     *
     * @param shelters the shelters to save, organized by their IDs
     */
    public static void saveShelters(HashMap<UUID, Shelter> shelters) {
        JSONArray sheltersArray = new JSONArray();

        for (Shelter shelter : shelters.values()) {
            JSONObject shelterObject = new JSONObject();

            shelterObject.put("id", shelter.getId().toString());
            shelterObject.put("name", shelter.getName());
            shelterObject.put("address", shelter.getAddress());
            shelterObject.put("zipCode", shelter.getLocation().getZipCode());
            shelterObject.put("capacity", shelter.getCapacity());
            shelterObject.put("status", shelter.getStatus().toString());

            JSONObject inventoryObject = new JSONObject();

            if (shelter.getInventory() != null) {
                for (Map.Entry<String, Integer> item :
                        shelter.getInventory().entrySet()) {
                    inventoryObject.put(item.getKey(), item.getValue());
                }
            }

            shelterObject.put("inventory", inventoryObject);
            sheltersArray.add(shelterObject);
        }

        try (FileWriter file = new FileWriter(
                DataConstants.JSON_FOLDER + DataConstants.SHELTERS_FILE)) {
            file.write(sheltersArray.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves assistance requests and their messages to the request JSON file.
     *
     * @param requests the list of assistance requests to save
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

            if (request.getMessages() != null) {
                for (Message message : request.getMessages()) {
                    JSONObject messageObject = new JSONObject();

                    messageObject.put("sender",
                            message.getSender().getUsername());
                    messageObject.put("message", message.getBody());

                    messagesArray.add(messageObject);
                }
            }

            requestObject.put("messages", messagesArray);
            requestsArray.add(requestObject);
        }

        try (FileWriter file = new FileWriter(
                DataConstants.JSON_FOLDER + DataConstants.REQUESTS_FILE)) {
            file.write(requestsArray.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves hurricane events to the hurricane JSON file.
     *
     * @param hurricanes the list of hurricane events to save
     */
    public static void saveHurricanes(ArrayList<HurricaneEvent> hurricanes) {
        JSONArray hurricanesArray = new JSONArray();

        for (HurricaneEvent hurricane : hurricanes) {
            JSONObject hurricaneObject = new JSONObject();

            hurricaneObject.put("id", hurricane.getId().toString());
            hurricaneObject.put("status", hurricane.getStatus().toString());
            hurricaneObject.put("location", hurricane.getAddress());
            hurricaneObject.put("maxWindSpeed", hurricane.getMaxWindSpeed());
            hurricaneObject.put("forecastPath", hurricane.getForcastPath());

            hurricanesArray.add(hurricaneObject);
        }

        try (FileWriter file = new FileWriter(
                DataConstants.JSON_FOLDER + DataConstants.HURRICANES_FILE)) {
            file.write(hurricanesArray.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}