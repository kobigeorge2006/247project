package com.model.data;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
     * Adds new users to the user JSON file without discarding existing records.
     *
     * @param users the list of users to save
     */
    public static void saveUsers(ArrayList<User> users) {
        JSONArray usersArray = new JSONArray();

        if (users != null) {
            for (User user : users) {
                if (user == null) {
                    continue;
                }

                JSONObject userObject = new JSONObject();

                userObject.put("firstName", user.getFirstName());
                userObject.put("lastName", user.getLastName());
                userObject.put("username", user.getUsername());
                userObject.put("password", user.getPassword());
                userObject.put("email", user.getEmail());
                userObject.put("phoneNumber", user.getPhoneNumber());
                userObject.put("volunteer", user.isVolunteer());
                userObject.put("id", user.getId() == null
                        ? "" : user.getId().toString());

                JSONArray requestsArray = new JSONArray();

                if (user.getRequests() != null) {
                    requestsArray.addAll(user.getRequests());
                }

                userObject.put("requests", requestsArray);
                usersArray.add(userObject);
            }
        }

        mergeAndWrite(DataConstants.USER_FILE, "id", usersArray, false);
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
     * Saves requests without discarding existing records.
     *
     * @param requests the list of assistance requests to save
     */
    public static void saveRequests(ArrayList<AssistanceRequest> requests) {
        JSONArray requestsArray = new JSONArray();

        if (requests != null) {
            for (AssistanceRequest request : requests) {
                if (request == null) {
                    continue;
                }

                JSONObject requestObject = new JSONObject();

                requestObject.put("id", request.getId() == null ? "" : request.getId().toString());
                requestObject.put("contactInfo", request.getContactInfo());
                requestObject.put("address", request.getAddress() == null
                        ? null : request.getAddress().toString());
                requestObject.put("aidCategory", request.getAidCategory() == null
                        ? "" : request.getAidCategory().toString());
                requestObject.put("severity", request.getSeverity() == null
                        ? "" : request.getSeverity().toString());
                requestObject.put("status", request.getStatus() == null
                        ? "" : request.getStatus().toString());
                requestObject.put("message", request.getMessage());
                requestObject.put("location", request.getLocation() == null
                        ? null : request.getLocation().toString());
                requestObject.put("hasPet", request.getHasPet());

                JSONArray messagesArray = new JSONArray();

                if (request.getMessages() != null) {
                    for (Message message : request.getMessages()) {
                        if (message == null) {
                            continue;
                        }

                        JSONObject messageObject = new JSONObject();

                        messageObject.put("sender", message.getSender() == null
                                ? "" : message.getSender().getUsername());
                        messageObject.put("message", message.getBody());
                        messageObject.put("timestamp", message.getTimestamp() == null
                                ? null : message.getTimestamp().toString());
                        messagesArray.add(messageObject);
                    }
                }

                requestObject.put("messages", messagesArray);
                requestsArray.add(requestObject);
            }
        }

        mergeAndWrite(DataConstants.REQUESTS_FILE, "id", requestsArray, true);
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

    private static void mergeAndWrite(String fileName, String key,
                                      JSONArray newRecords, boolean updateExisting) {
        try {
            JSONArray allRecords = readExistingRecords(fileName);
            Map<String, Integer> recordIndexes = new HashMap<>();

            for (int i = 0; i < allRecords.size(); i++) {
                Object record = allRecords.get(i);
                if (record instanceof JSONObject) {
                    Object id = ((JSONObject) record).get(key);
                    if (id != null && !id.toString().isEmpty()) {
                        recordIndexes.putIfAbsent(id.toString(), i);
                    }
                }
            }

            for (Object record : newRecords) {
                JSONObject jsonRecord = (JSONObject) record;
                Object idValue = jsonRecord.get(key);
                String id = idValue == null ? "" : idValue.toString();
                Integer existingIndex = id.isEmpty() ? null : recordIndexes.get(id);

                if (existingIndex != null) {
                    if (updateExisting) {
                        JSONObject existingRecord =
                                (JSONObject) allRecords.get(existingIndex);
                        existingRecord.putAll(jsonRecord);
                    }
                } else {
                    allRecords.add(jsonRecord);
                    if (!id.isEmpty()) {
                        recordIndexes.put(id, allRecords.size() - 1);
                    }
                }
            }

            try (FileWriter file = new FileWriter(
                    DataConstants.JSON_FOLDER + fileName)) {
                file.write(allRecords.toJSONString());
            }
        } catch (IOException | ParseException e) {
            throw new IllegalStateException("Unable to save " + fileName, e);
        }
    }

    private static JSONArray readExistingRecords(String fileName)
            throws IOException, ParseException {
        try (FileReader file = new FileReader(
                DataConstants.JSON_FOLDER + fileName)) {
            Object records = new JSONParser().parse(file);
            if (records instanceof JSONArray) {
                return (JSONArray) records;
            }
            throw new IOException("Expected a JSON array in " + fileName);
        } catch (java.io.FileNotFoundException e) {
            return new JSONArray();
        }
    }
}