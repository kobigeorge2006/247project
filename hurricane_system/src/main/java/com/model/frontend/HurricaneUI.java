package com.model.frontend;

import com.model.HurricaneReliefSystem;
import com.model.data.DataBase;
import com.model.data.DataWriter;
import com.model.other.Shelter;
import com.model.user.Administrator;
import java.util.ArrayList;
import java.util.UUID;
import com.location.Address;

import com.model.requests.*;


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

    // Scenario 2: User signup
    public void scenario2() {
        System.out.println("\nScenario 2: Signup");

        if (system.signUp("Amy", "Smith", "asmith", "12345",
                "amy@email.com",true,"8035551234")) {
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

    // Scenario 7: Shelter manager checks in the Carter family and Buddy
    public void scenarioCheckInFamily() {
        System.out.println("\nScenario 7: Check the Carter Family into a Shelter");

        Shelter shelter = null;
        for (Shelter candidate : system.findShelters("29201")) {
            if ("Emergency Relief Shelter".equalsIgnoreCase(candidate.getName())) {
                shelter = candidate;
                break;
            }
        }
        if (shelter == null) {
            System.out.println("Shelter not found.");
            return;
        }

        Administrator felix = new Administrator(
                "Felix", "Manager", "felix", "demo",
                "felix@example.com", "8035550000", shelter);
        System.out.println("Felix logged in as the shelter manager.");
        ArrayList<String> occupants = new ArrayList<>();

        System.out.println("Shelter: " + shelter.getName());
        System.out.println("Location: " + shelter.getLocation().getStreetAddress()
                + ", " + shelter.getLocation().getZipCode());
        System.out.println("Total capacity: " + shelter.getCapacity());
        System.out.println("Current occupancy: " + occupants.size());
        System.out.println("Available spaces: " + shelter.getCapacity());
        System.out.println("Occupants: " + occupants);

        if (shelter.getCapacity() < 4) {
            System.out.println("Not enough capacity for the Carter family.");
            return;
        }

        occupants.add("John Carter");
        occupants.add("Sophie Carter");
        occupants.add("Carter family member");

        System.out.println("Felix checked in three family members and Buddy.");
        System.out.println("Current occupancy: " + occupants.size());
        System.out.println("Available spaces: " + (shelter.getCapacity() - occupants.size()));
        System.out.println("Occupants: " + occupants);
        System.out.println("Accompanying pet: Buddy");

        UUID requestId = UUID.fromString(
                "7e8a2c49-6f13-4b95-a0d7-3c826f914e52");
        for (AssistanceRequest request : DataBase.getInstance().getAssistanceRequests()) {
            if (requestId.equals(request.getId()) && felix.getShelter() == shelter) {
                request.updateStatus(RequestStatus.COMPLETED);
                DataWriter.saveRequests(DataBase.getInstance().getAssistanceRequests());
                System.out.println("Relief request marked Complete.");
                break;
            }
        }
    }

    public void scenarioScarlett(){
        HurricaneReliefSystem system = new HurricaneReliefSystem();
        DataBase db = DataBase.getInstance();
        if(system.signUp("John", "Carter", "JCarter", "SophieCarter", "JCarter@gmail.com", false, "301-123-4565")){
            System.out.println("Mr Carter signed up");
        }
        if(system.logIn("JCarter", "SophieCarter")){
            System.out.println("Log in successful");
        }
        
        db.addAssistanceRequest(UUID.fromString("7e8a2c49-6f13-4b95-a0d7-3c826f914e52"), "JCarter@gmail.com", new Address("247 Palmetto Harbor Lane", "Charleston", "SC", "29403"), AidCategory.MEDICAL,Severity.HIGH, RequestStatus.SUBMITTED, "My daughter sophie needs medical assistance my family of 4 and my dog need transportation to a shelter too", new Address("247 Palmetto Harbor Lane", "Charleston", "SC", "29403"), true );
    }

    public static void main(String[] args) {
        HurricaneUI ui = new HurricaneUI();
        ui.run();
        ui.scenarioScarlett();
        ui.scenarioCheckInFamily();
    }
}
