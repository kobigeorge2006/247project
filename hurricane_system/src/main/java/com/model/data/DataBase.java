package com.model.data;
<<<<<<< HEAD
=======

import java.util.ArrayList;
>>>>>>> 39fa23efed77584552c927b082dc1ffae3130f1a

public class DataBase {
    private DataBase instance;
    private ArrayList<User> users;
    private ArrayList<AssistanceRequest> assistanceRequests;
    private ArrayList<HurricaneEvent> hurricaneEvents;

    private DataBase() {
        users = new ArrayList<>();
        assistanceRequests = new ArrayList<>();
        hurricaneEvents = new ArrayList<>();
    }

    public static DataBase getInstance(){
        return instance;
    }

    public ArrayList<Users> getUser(){
        
    }

    public ArrayList<Shelters> getShelters(){

    }

    public ArrayList<AssistanceRequest> getAssistanceRequests(){

    }

}
