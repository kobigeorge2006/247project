package com.model;

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

    public 


}
