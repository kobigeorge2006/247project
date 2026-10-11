package com.model.frontend;

import com.model.HurricaneReliefSystem;
import com.model.other.Shelter;
import java.util.ArrayList;

//Runs scenarios for the Hurricane Relief System

public class HurricaneUI {

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
    }

    //Scenario 1: User login

    public void scenario1() {
        System.out.println("\nScenario 1: Login");

        if (system.logIn("testuser", "12345")) {
            System.out.println("Login successful.");
        } else {
            System.out.println("Login failed.");
        }
    }

    //Scenario 2: User signup

    public void scenario2() {
        System.out.println("\nScenario 2: Signup");

        if (system.signUp("Amy", "Smith", "asmith", "12345",
                "amy@email.com", "8035551234")) {
            System.out.println("Account created successfully.");
        } else {
            System.out.println("Signup failed.");
        }
    }

    //Scenario 3: User logout

    public void scenario3() {
        System.out.println("\nScenario 3: Logout");

        if (system.isLoggedIn()) {
            system.logOut();
            System.out.println("User logged out.");
        } else {
            System.out.println("No user is logged in.");
        }
    }

    //Scenario 4: Find shelters by ZIP code

    public void scenario4() {
        System.out.println("\nScenario 4: Find Shelters");

        ArrayList<Shelter> shelters = system.findShelters("29730");

        if (shelters.isEmpty()) {
            System.out.println("No shelters found in this ZIP code.");
            return;
        }

        for (Shelter shelter : shelters) {
            System.out.println("Shelter: " + shelter.getName());
            System.out.println("Capacity: " + shelter.getCapacity());
            System.out.println("Status: " + shelter.getStatus());
        }
    }

    // Scenario 5: Create a shelter
    
    public void scenario5() {
        System.out.println("\nScenario 5: Create Shelter");

        if (!system.logIn("admin", "12345")) {
            System.out.println("Admin login failed.");
            return;
        }

        boolean created = system.createShelter(
                "Emergency Relief Shelter",
                "123 Main Street",
                "29730",
                100
        );

        if (created) {
            System.out.println("Shelter created successfully.");
        } else {
            System.out.println("Shelter could not be created.");
        }

        system.logOut();
        System.out.println("Admin logged out.");
    }

    public static void main(String[] args) {
        HurricaneUI ui = new HurricaneUI();
        ui.run();
    }
}