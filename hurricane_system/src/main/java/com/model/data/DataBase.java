package com.model.data;

import java.util.ArrayList;
import com.model.user.User;
import com.model.shelter.Shelters;
import com.model.requests.AssistanceRequest;


public class DataBase {
    private static DataBase instance;

    private ArrayList<User> users;
    private HashMap<UUID, Shelters> shelters;
    private ArrayList<AssistanceRequest> assistanceRequests;
    private ArrayList<HurricaneEvent> hurricaneEvents;

    private DataBase() {
        users = new ArrayList<>();
        shelters = new HashMap<>();
        assistanceRequests = new ArrayList<>();
        hurricaneEvents = new ArrayList<>();
    }

    public static DataBase getInstance(){
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }

    public ArrayList<User> getUser(){
        return users;

    }

    public HashMap<UUID, Shelters> getShelters(){
        return shelters;
    }

    public ArrayList<AssistanceRequest> getAssistanceRequests(){
        return assistanceRequests;
    }

}
