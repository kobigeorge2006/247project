package com.model.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.model.user.*;
import com.model.other.*;
import com.model.requests.*;

/**
 * Loads information from the JSON files.
 *
 * @author Kobi George
 */
public class DataLoader {

    /**
     * Creates a loader for JSON-backed model data.
     */
    public DataLoader() {
    }

    /**
     * Reads a JSON array from the configured data directory.
     *
     * @param fileName the name of the JSON file to read
     * @return the parsed array, or an empty array if reading or parsing fails
     */
    private static JSONArray loadData(String fileName) {
        JSONArray jsonArray = new JSONArray();

        try (FileReader reader = new FileReader(DataConstants.JSON_FOLDER + fileName)) {
            JSONParser parser = new JSONParser();
            jsonArray = (JSONArray) parser.parse(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonArray;
    }

    /**
     * Loads user records from the configured user file.
     *
     * @return the loaded users
     */
    public static ArrayList<User> loadUsers() {
        ArrayList<User> users = new ArrayList<>();

        JSONArray jsonUsers = loadData(DataConstants.USER_FILE);

        for (int i = 0; i < jsonUsers.size(); i++) {
            JSONObject jsonUser = (JSONObject) jsonUsers.get(i);
            users.add(new User(jsonUser));
        }

        return users;
    }

    /**
     * Loads shelter records from the configured shelter file.
     *
     * @return shelters indexed by their unique identifiers
     */
    public static HashMap<UUID, Shelter> loadShelters() {
        HashMap<UUID, Shelter> shelters = new HashMap<>();

        JSONArray jsonShelters = loadData(DataConstants.SHELTERS_FILE);

        for (int i = 0; i < jsonShelters.size(); i++) {
            JSONObject jsonShelter = (JSONObject) jsonShelters.get(i);
            Shelter shelter = new Shelter(jsonShelter);
            shelters.put(shelter.getId(), shelter);
        }

        return shelters;
    }

    /**
     * Loads assistance requests from the configured requests file.
     *
     * @return the loaded assistance requests
     */
    public static ArrayList<AssistanceRequest> loadRequests() {
        ArrayList<AssistanceRequest> requests = new ArrayList<>();

        JSONArray jsonRequests = loadData(DataConstants.REQUESTS_FILE);

        for (int i = 0; i < jsonRequests.size(); i++) {
            JSONObject jsonRequest = (JSONObject) jsonRequests.get(i);
            requests.add(new AssistanceRequest(jsonRequest));
        }

        return requests;
    }

    /**
     * Loads hurricane events from the configured hurricanes file.
     *
     * @return the loaded hurricane events
     */
    public static ArrayList<HurricaneEvent> loadHurricanes() {
        ArrayList<HurricaneEvent> hurricanes = new ArrayList<>();

        JSONArray jsonHurricanes = loadData(DataConstants.HURRICANES_FILE);

        for (int i = 0; i < jsonHurricanes.size(); i++) {
            JSONObject jsonHurricane = (JSONObject) jsonHurricanes.get(i);
            hurricanes.add(new HurricaneEvent(jsonHurricane));
        }

        return hurricanes;
    }
}