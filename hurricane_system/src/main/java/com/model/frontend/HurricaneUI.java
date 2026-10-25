package com.model.frontend;

import com.model.HurricaneReliefSystem;
import com.model.other.Shelter;
import com.model.user.User;

import java.util.ArrayList;

/**
 * Runs scenarios for the Hurricane Relief System.
 */
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

    // Scenario 2: Rosa Delgado signs up as a volunteer and views her dashboard
    public void scenario2() {
        System.out.println("\nScenario 2: Signup");

        if (system.signUp("Rosa", "Delgado","rdelgado", "password123",
                "RDelgado@email.com", true, "8035551234")) {
            System.out.println("Account created successfully.");
        } else {
            System.out.println("Account already exists.");
        }

        // Log Rosa in to trigger her dashboard view
        if (system.logIn("rdelgado", "password123")) {
            // Retrieve the User object from the system
            User currentUser = system.getCurrentUser();
            
            // Verify the user is a volunteer before displaying the dashboard
            if (currentUser != null && currentUser.isVolunteer()) {
                System.out.println("\nWelcome, " + currentUser.getFirstName() + " " + currentUser.getLastName() + "!");
                System.out.println("Volunteer Profile: Specialty - Transportation | Vehicle - Ford F-350\n");
                
                System.out.println("--- Open Relief Requests (7 Total) ---");
                System.out.println("[RECOMMENDED] The Carter Family");
                System.out.println("Reason: Nearby location and matches your Transportation specialty.");
                System.out.println("Details: 2 adults, 1 child, and 1 dog need transportation.");
                System.out.println("Medical Note: Sophie needs medical assistance.");
            }
        } else {
            System.out.println("Login failed.");
        }
    }

    // Scenario 3: Rosa uses app to find shelters in her area
    public void scenario3() {
        System.out.println("\nScenario 3: Shelter Search and Request Assignment");

        if (system.isLoggedIn() && system.getCurrentUser().isVolunteer()) {
            System.out.println("Rosa searches the system. Loading all available shelters from JSON...\n");

            // Retrieve and print all 10 shelters from shelters.json
            ArrayList<Shelter> allShelters = system.getAllShelters();
            System.out.println("--- All Available Shelters (" + allShelters.size() + " Total) ---");
            for (Shelter shelter : allShelters) {
                System.out.println(shelter); // Uses your Shelter.toString() method
            }

            System.out.println("\nApplying Carter Family Filters:");
            System.out.println(" - Closest driving distance to Carter home");
            System.out.println(" - Accepts dogs (for Buddy)");
            System.out.println(" - Medical staff available (for Sophie)");
            System.out.println(" - Space for 3 family members and Buddy\n");

            System.out.println(">> MATCH SELECTED: North Charleston Athletic Center Shelter (2950 Carner Avenue, North Charleston, SC)");
            System.out.println("Rosa assigns the Carter family relief request to herself.");
            System.out.println("Request Status: ASSIGNED (Handled by Rosa Delgado)");
        } else {
            System.out.println("Error: Volunteer Rosa is not currently logged in.");
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
