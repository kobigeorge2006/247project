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

    public static ArrayList<User> loadUsers() {
        ArrayList<User> users = new ArrayList<>();

        JSONArray jsonUsers = loadData(DataConstants.USER_FILE);

        for (int i = 0; i < jsonUsers.size(); i++) {
            JSONObject jsonUser = (JSONObject) jsonUsers.get(i);
            users.add(new User(jsonUser));
        }

        return users;
    }

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

    public static ArrayList<AssistanceRequest> loadRequests() {
        ArrayList<AssistanceRequest> requests = new ArrayList<>();

        JSONArray jsonRequests = loadData(DataConstants.REQUESTS_FILE);

        for (int i = 0; i < jsonRequests.size(); i++) {
            JSONObject jsonRequest = (JSONObject) jsonRequests.get(i);
            requests.add(new AssistanceRequest(jsonRequest));
        }

        return requests;
    }

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