package com.model.frontend;

import com.model.HurricaneReliefSystem;
import com.model.other.Shelter;
import java.util.ArrayList;
<<<<<<< HEAD
import java.util.HashMap;
import com.model.user.*;
import com.model.data.*;
import com.model.other.*;
import java.util.Scanner;
import com.model.HurricaneReliefSystem;
=======
>>>>>>> 01f7c747a2101cbba62b22154c2be06c89d607c1

/**
 * Runs scenarios for the Hurricane Relief System.
 */
public class HurricaneUI {
<<<<<<< HEAD
    
public void scenario1() {
    // Create a new instance of the HurricaneReliefSystem
    HurricaneReliefSystem system = new HurricaneReliefSystem();

    // Sign up a new user
    boolean signUpSuccess = system.signUp("John", "Doe", "johndoe", "password123", "john.doe@example.com", "123-456-7890");
    if(signUpSuccess) {
        System.out.println("User signed up successfully.");
    } else {
        System.out.println("Sign up failed. Username may already be taken.");
    }
}
public void scenario2() {
    HurricaneReliefSystem system = new HurricaneReliefSystem();

    boolean loginSuccess = system.logIn("johndoe", "password123");
    if(loginSuccess) {
        System.out.println("User logged in successfully.");
    } else {
        System.out.println("Login failed. Please check your username and password.");
    }
}

public void scenario3(){
    HurricaneReliefSystem system = new HurricaneReliefSystem();
    system.logOut();
}
public static void main(String[] args) {
    HurricaneUI ui = new HurricaneUI();
    ui.scenario1();
    ui.scenario2();
    ui.scenario3();
}
}

=======

    private HurricaneReliefSystem system;

    public HurricaneUI() {
        system = new HurricaneReliefSystem();
    }

    public void run() {
        scenario1();
        scenario2();
        scenario3();
        scenario4();
        scenario5();
        scenario6();
    }

    // Scenario 1: User login
    public void scenario1() {
        System.out.println("\nScenario 1: Login");

        if (system.logIn("asmith", "12345")) {
            System.out.println("Login successful.");
        } else {
            System.out.println("Login failed.");
        }
    }

    // Scenario 2: User signup
    public void scenario2() {
        System.out.println("\nScenario 2: Signup");

        if (system.signUp("Amy", "Smith", "asmith", "12345",
                "amy@email.com", "8035551234")) {
            System.out.println("Account created successfully.");
        } else {
            System.out.println("Account already exists.");
        }
    }

    // Scenario 3: User logout
    public void scenario3() {
        System.out.println("\nScenario 3: Logout");

        if (system.isLoggedIn()) {
            system.logOut();
            System.out.println("User logged out.");
        } else {
            System.out.println("No user is logged in.");
        }
    }

    // Scenario 4: Find shelters in a given ZIP code
    public void scenario4() {
        String zipCode = "29203";

        System.out.println("\nScenario 4: Find Shelters");
        System.out.println("Searching ZIP code: " + zipCode);

        ArrayList<Shelter> shelters = system.findShelters(zipCode);

        System.out.println("Shelters found: " + shelters.size());

        for (Shelter shelter : shelters) {
            System.out.println("------------------------");
            System.out.println("Shelter: " + shelter.getName());
            System.out.println("Address: " + shelter.getAddress());
            System.out.println("Capacity: " + shelter.getCapacity());
            System.out.println("Status: " + shelter.getStatus());
            System.out.println("Water: "
                    + shelter.getInventory().getOrDefault("Water", 0));
        }
    }

    // Scenario 5: Administrator creates a shelter
    public void scenario5() {
        System.out.println("\nScenario 5: Create Shelter");

        if (!system.logIn("admin", "12345")) {
            System.out.println("Admin login failed.");
            return;
        }

        boolean created = system.createShelter(
                "Emergency Relief Shelter",
                "123 Main Street",
                "29201",
                100
        );

        if (created) {
            System.out.println("Shelter created successfully.");
        } else {
            System.out.println("Shelter already exists or could not be created.");
        }

        system.logOut();
        System.out.println("Admin logged out.");
    }

    // Scenario 6: Regular user cannot create a shelter
    public void scenario6() {
        System.out.println("\nScenario 6: Admin Permissions");

        if (!system.logIn("asmith", "12345")) {
            System.out.println("Regular user login failed.");
            return;
        }

        boolean created = system.createShelter(
                "Unauthorized Test Shelter",
                "100 Test Street",
                "29201",
                50
        );

        if (!created) {
            System.out.println("Regular user correctly denied permission.");
        } else {
            System.out.println("ERROR: Regular user created a shelter.");
        }

        system.logOut();
    }

    public static void main(String[] args) {
        HurricaneUI ui = new HurricaneUI();
        ui.run();
    }
}
>>>>>>> 01f7c747a2101cbba62b22154c2be06c89d607c1
