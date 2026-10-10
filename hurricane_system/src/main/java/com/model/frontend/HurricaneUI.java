package com.model.frontend;

import java.util.ArrayList;
import com.model.data.DataBase;
import com.model.data.DataLoader;
import com.model.user.User;
import com.model.other.Shelter;
import com.model.other.HurricaneEvent;
import com.model.requests.AssistanceRequest;

//Runs test scenarios for the Hurricane Relief System

public class HurricaneUI {

    private DataBase database;
    private ArrayList<HurricaneEvent> hurricanes;

    public HurricaneUI() {
        database = DataBase.getInstance();

        database.getUser().addAll(DataLoader.loadUsers());
        database.getShelters().putAll(DataLoader.loadShelters());
        database.getAssistanceRequests().addAll(DataLoader.loadRequests());

        hurricanes = DataLoader.loadHurricanes();
    }

    public void run() {
        scenario1();
        scenario2();
        scenario3();
        scenario4();
    }

    // Tests a user logging into the system
     
    public void scenario1() {
        System.out.println("\nScenario 1: Login");

        String username = "testuser";
        String password = "12345";

        for (User user : database.getUser()) {
            if (username.equals(user.getUsername())
                    && password.equals(user.getPassword())) {
                System.out.println("Welcome, " + user.getFirstName());
                return;
            }
        }

        System.out.println("Login failed.");
    }

    //Shows assistance requests currently in the system
     
    public void scenario2() {
        System.out.println("\nScenario 2: Assistance Requests");

        if (database.getAssistanceRequests().isEmpty()) {
            System.out.println("No assistance requests found.");
            return;
        }

        for (AssistanceRequest request : database.getAssistanceRequests()) {
            System.out.println("Request ID: " + request.getId());
            System.out.println("Aid needed: " + request.getAidCategory());
            System.out.println("Status: " + request.getStatus());
        }
    }

    //Shows shelters and their current information

    public void scenario3() {
        System.out.println("\nScenario 3: Shelters");

        if (database.getShelters().isEmpty()) {
            System.out.println("No shelters found.");
            return;
        }

        for (Shelter shelter : database.getShelters().values()) {
            System.out.println(shelter.getName()
                    + " - Capacity: " + shelter.getCapacity()
                    + " - Status: " + shelter.getStatus());
        }
    }

    //Shows hurricane events in the system

    public void scenario4() {
        System.out.println("\nScenario 4: Hurricanes");

        if (hurricanes.isEmpty()) {
            System.out.println("No hurricane events found.");
            return;
        }

        for (HurricaneEvent hurricane : hurricanes) {
            System.out.println("Status: " + hurricane.getStatus());
            System.out.println("Wind speed: " + hurricane.getMaxWindSpeed());
            System.out.println("Forecast path: " + hurricane.getForcastPath());
        }
    }

    public static void main(String[] args) {
        HurricaneUI ui = new HurricaneUI();
        ui.run();
    }
}