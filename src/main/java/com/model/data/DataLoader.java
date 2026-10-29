package com.model.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

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

        try (BufferedReader reader = getReaderFromFile(
                DataConstants.JSON_FOLDER + fileName,
                "/data/json/" + fileName)) {
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

    /**
     * Opens a data file, using its classpath resource while running tests.
     *
     * @param fileName filesystem path to the data file
     * @param jsonFileName absolute classpath path to its test resource
     * @return a buffered reader for the data
     * @throws IOException if the file or test resource cannot be opened
     */
    public static BufferedReader getReaderFromFile(
            String fileName, String jsonFileName) throws IOException {
        if (DataConstants.isJUnitTest()) {
            InputStream inputStream =
                    DataBase.class.getResourceAsStream(jsonFileName);
            if (inputStream == null) {
                throw new FileNotFoundException(
                        "Missing classpath resource: " + jsonFileName);
            }
            return new BufferedReader(new InputStreamReader(inputStream,
                    StandardCharsets.UTF_8));
        }

        return new BufferedReader(new FileReader(fileName));
    }
}