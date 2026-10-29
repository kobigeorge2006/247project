package com.model.data;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
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
     * Creates a writer for JSON-backed model data.
     */
    public DataWriter() {
    }

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

                putJsonValue(userObject, "firstName", user.getFirstName());
                putJsonValue(userObject, "lastName", user.getLastName());
                putJsonValue(userObject, "username", user.getUsername());
                putJsonValue(userObject, "password", user.getPassword());
                putJsonValue(userObject, "email", user.getEmail());
                putJsonValue(userObject, "phoneNumber", user.getPhoneNumber());
                putJsonValue(userObject, "volunteer", user.isVolunteer());
                putJsonValue(userObject, "id", user.getId() == null
                        ? "" : user.getId().toString());

                JSONArray requestsArray = new JSONArray();

                if (user.getRequests() != null) {
                    addJsonValues(requestsArray, user.getRequests());
                }

                putJsonValue(userObject, "requests", requestsArray);
                addJsonValue(usersArray, userObject);
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

            putJsonValue(shelterObject, "id", shelter.getId().toString());
            putJsonValue(shelterObject, "name", shelter.getName());
            putJsonValue(shelterObject, "address", shelter.getAddress());
            putJsonValue(shelterObject, "zipCode",
                    shelter.getLocation().getZipCode());
            putJsonValue(shelterObject, "capacity", shelter.getCapacity());
            putJsonValue(shelterObject, "status",
                    shelter.getStatus().toString());

            JSONObject inventoryObject = new JSONObject();

            if (shelter.getInventory() != null) {
                for (Map.Entry<String, Integer> item :
                        shelter.getInventory().entrySet()) {
                    putJsonValue(inventoryObject, item.getKey(),
                            item.getValue());
                }
            }

            putJsonValue(shelterObject, "inventory", inventoryObject);
            addJsonValue(sheltersArray, shelterObject);
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

                putJsonValue(requestObject, "id", request.getId() == null
                        ? "" : request.getId().toString());
                putJsonValue(requestObject, "contactInfo",
                        request.getContactInfo());
                putJsonValue(requestObject, "address",
                        request.getAddress() == null
                        ? null : request.getAddress().toString());
                putJsonValue(requestObject, "aidCategory",
                        request.getAidCategory() == null
                        ? "" : request.getAidCategory().toString());
                putJsonValue(requestObject, "severity",
                        request.getSeverity() == null
                        ? "" : request.getSeverity().toString());
                putJsonValue(requestObject, "status",
                        request.getStatus() == null
                        ? "" : request.getStatus().toString());
                putJsonValue(requestObject, "message", request.getMessage());
                putJsonValue(requestObject, "location",
                        request.getLocation() == null
                        ? null : request.getLocation().toString());
                putJsonValue(requestObject, "hasPet", request.getHasPet());
                putJsonValue(requestObject, "assignedVolunteer",
                        request.getAssignedVolunteerUsername());

                JSONArray messagesArray = new JSONArray();

                if (request.getMessages() != null) {
                    for (Message message : request.getMessages()) {
                        if (message == null) {
                            continue;
                        }

                        JSONObject messageObject = new JSONObject();

                        putJsonValue(messageObject, "sender",
                                message.getSender() == null
                                ? "" : message.getSender().getUsername());
                        putJsonValue(messageObject, "message",
                                message.getBody());
                        putJsonValue(messageObject, "timestamp",
                                message.getTimestamp() == null
                                ? null : message.getTimestamp().toString());
                        addJsonValue(messagesArray, messageObject);
                    }
                }

                putJsonValue(requestObject, "messages", messagesArray);
                addJsonValue(requestsArray, requestObject);
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

            putJsonValue(hurricaneObject, "id", hurricane.getId().toString());
            putJsonValue(hurricaneObject, "status",
                    hurricane.getStatus().toString());
            putJsonValue(hurricaneObject, "location", hurricane.getAddress());
            putJsonValue(hurricaneObject, "maxWindSpeed",
                    hurricane.getMaxWindSpeed());
            putJsonValue(hurricaneObject, "forecastPath",
                    hurricane.getForcastPath());

            addJsonValue(hurricanesArray, hurricaneObject);
        }

        try (FileWriter file = new FileWriter(
                DataConstants.JSON_FOLDER + DataConstants.HURRICANES_FILE)) {
            file.write(hurricanesArray.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Merges the supplied JSON records into an existing file using a record key,
     * optionally replacing existing records with matching keys.
     *
     * @param fileName target file name
     * @param key JSON property used to identify records
     * @param newRecords records to merge into the file
     * @param updateExisting whether matching existing records should be updated
     * @throws IllegalStateException if existing data cannot be parsed or the
     *         merged data cannot be written
     */
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
                        putJsonValues(existingRecord, jsonRecord);
                    }
                } else {
                    addJsonValue(allRecords, jsonRecord);
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

    @SuppressWarnings("unchecked")
    private static void addJsonValue(JSONArray array, Object value) {
        array.add(value);
    }

    @SuppressWarnings("unchecked")
    private static void addJsonValues(JSONArray array,
            Collection<?> values) {
        array.addAll(values);
    }

    @SuppressWarnings("unchecked")
    private static void putJsonValue(JSONObject object, String key,
            Object value) {
        object.put(key, value);
    }

    @SuppressWarnings("unchecked")
    private static void putJsonValues(JSONObject destination,
            JSONObject source) {
        destination.putAll(source);
    }

    /**
     * Reads existing JSON records from a file.
     *
     * @param fileName the file name to read
     * @return the existing records, or an empty array when the file is absent
     * @throws IOException if the file contains something other than a JSON array
     * @throws ParseException if the file is not valid JSON
     */
    private static JSONArray readExistingRecords(String fileName)
            throws IOException, ParseException {
        try (java.io.BufferedReader file = DataLoader.getReaderFromFile(
                DataConstants.JSON_FOLDER + fileName,
                "/data/json/" + fileName)) {
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