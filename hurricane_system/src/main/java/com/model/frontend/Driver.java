package com.model.frontend;

import java.util.UUID;
import java.util.ArrayList;
import java.util.HashMap;
import com.model.user.*;
import com.model.data.*;
import com.model.other.*;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        // Load data from JSON files
        ArrayList<User> users = DataLoader.loadUsers();
        ArrayList<hurricaneEvent> hurricanes = DataLoader.loadHurricanes();
        HashMap<UUID, Shelter> shelters = DataLoader.loadShelters();
        ArrayList<AssistanceRequest> assistanceRequests = DataLoader.loadAssistanceRequests();


        // Create a new instance of the database and populate it with the loaded data
        DataBase db = DataBase.getInstance();
        db.setUsers(users);
        db.setHurricanes(hurricanes);
        db.setShelters(shelters);
        db.setAssistanceRequests(assistanceRequests);

        // Start the application (e.g., launch GUI or command-line interface)
        System.out.println("Data loaded successfully. Application is ready to use.");
        System.out.println("Number of users loaded: " + users.size());
        System.out.println("Number of hurricanes loaded: " + hurricanes.size());
        System.out.println("Number of shelters loaded: " + shelters.size());
        System.out.println("Number of assistance requests loaded: " + assistanceRequests.size());
        System.out.println("=======Hurricane System Application=======");
        System.out.println("Would you like to log in or sign up?");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();

    }      
    
}