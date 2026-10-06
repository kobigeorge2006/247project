package com.model;

import java.util.UUID;
import java.util.ArrayList;
import java.util.HashMap;

public class Driver {
    public static void main(String[] args) {
        // Load data from JSON files
        ArrayList<User> users = DataLoader.loadUsers();


        // Create a new instance of the database and populate it with the loaded data
        DataBase db = DataBase.getInstance();
        db.setUsers(users);

        // Start the application (e.g., launch GUI or command-line interface)
    }
    
}
