
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

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Runs scenarios for the Hurricane Relief System.
 */
public class HurricaneUI {

    private HurricaneReliefSystem system;

    private static final UUID CARTER_REQUEST_ID = UUID.fromString(
            "7e8a2c49-6f13-4b95-a0d7-3c826f914e52");

    private static final String CHECKIN_FILE = "shelterCheckIns.json";

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
            if ("Emergency Relief Shelter".equalsIgnoreCase(
                    candidate.getName())) {
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

        System.out.println("Shelter manager: felix");
        System.out.println("Manager account authentication not implemented.");

        JSONArray checkIns = loadCheckIns();
        JSONObject existing = findCheckIn(checkIns, CARTER_REQUEST_ID);

        int occupancy = 0;
        JSONArray shelterOccupants = new JSONArray();

        for (Object item : checkIns) {
            if (!(item instanceof JSONObject)) {
                continue;
            }

            JSONObject record = (JSONObject) item;

            if (!shelter.getId().toString().equals(
                    record.get("shelterId"))) {
                continue;
            }

            JSONArray people = (JSONArray) record.get("occupants");

            if (people != null) {
                occupancy += people.size();
                shelterOccupants.addAll(people);
            }
        }

        System.out.println("\nShelter Information");
        System.out.println("Name: " + shelter.getName());
        System.out.println("Location: " + shelter.getAddress());
        System.out.println("Total capacity: " + shelter.getCapacity());
        System.out.println("Current occupancy: " + occupancy);
        System.out.println("Available spaces: "
                + (shelter.getCapacity() - occupancy));
        System.out.println("Occupants: " + shelterOccupants);

        if (existing != null) {
            System.out.println("\nCarter family is already checked in.");
            System.out.println("Saved occupants: "
                    + existing.get("occupants"));
            System.out.println("Saved pet: " + existing.get("petName"));
            System.out.println("Saved shelter assignment: "
                    + existing.get("shelterName"));
            System.out.println("No duplicate check-in created.");

            // Repair a previously reset request status.
            completeSavedCarterRequest();
            return;
        }

        if (shelter.getCapacity() - occupancy < 3) {
            System.out.println("Not enough available spaces.");
            return;
        }

        AssistanceRequest carterRequest = null;

        for (AssistanceRequest request :
                DataBase.getInstance().getAssistanceRequests()) {

            if (CARTER_REQUEST_ID.equals(request.getId())) {
                carterRequest = request;
                break;
            }
        }

        if (carterRequest == null) {
            System.out.println("Carter relief request not found.");
            return;
        }

        JSONArray occupants = new JSONArray();
        occupants.add("John Carter");
        occupants.add("Sophie Carter");
        occupants.add("Carter family member");

        JSONObject record = new JSONObject();
        record.put("requestId", CARTER_REQUEST_ID.toString());
        record.put("shelterId", shelter.getId().toString());
        record.put("shelterName", shelter.getName());
        record.put("shelterAddress", shelter.getAddress());
        record.put("manager", "felix");
        record.put("occupants", occupants);
        record.put("humanCount", 3);
        record.put("petName", "Buddy");
        record.put("petCount", 1);
        record.put("checkedIn", true);

        checkIns.add(record);
        saveCheckIns(checkIns);

        System.out.println("\nFamily Check-In");
        System.out.println("Checked in: " + occupants);
        System.out.println("Human occupancy: " + (occupancy + 3));
        System.out.println("Available spaces: "
                + (shelter.getCapacity() - occupancy - 3));
        System.out.println("Accompanying pet: Buddy");
        System.out.println("Assignment: " + shelter.getName());

        carterRequest.updateStatus(RequestStatus.COMPLETED);

        DataWriter.saveRequests(
                DataBase.getInstance().getAssistanceRequests());

        System.out.println("\nRelief Request");
        System.out.println("Request ID: " + carterRequest.getId());
        System.out.println("Status: " + carterRequest.getStatus());
        System.out.println("Check-in saved to " + CHECKIN_FILE);
        System.out.println("Request status saved to assistanceRequests.json");
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

    private static Path jsonDirectory() {
        Path local = Paths.get("json");

        if (Files.isDirectory(local)) {
            return local;
        }

        Path parent = Paths.get("../json");

        if (Files.isDirectory(parent)) {
            return parent;
        }

        throw new IllegalStateException(
                "JSON directory not found.");
    }

    private static JSONArray loadCheckIns() {
        Path file = jsonDirectory().resolve(CHECKIN_FILE);

        if (!Files.exists(file)) {
            return new JSONArray();
        }

        try (Reader reader = Files.newBufferedReader(
                file, StandardCharsets.UTF_8)) {

            Object data = new JSONParser().parse(reader);

            if (data instanceof JSONArray) {
                return (JSONArray) data;
            }

            throw new IllegalStateException(
                    "Invalid check-in JSON format.");

        } catch (Exception e) {
            throw new IllegalStateException(
                    "Could not load shelter check-ins.", e);
        }
    }

    private static void saveCheckIns(JSONArray checkIns) {
        Path file = jsonDirectory().resolve(CHECKIN_FILE);

        try (Writer writer = Files.newBufferedWriter(
                file, StandardCharsets.UTF_8)) {

            writer.write(checkIns.toJSONString());

        } catch (IOException e) {
            throw new IllegalStateException(
                    "Could not save shelter check-ins.", e);
        }
    }

    private static JSONObject findCheckIn(
            JSONArray checkIns, UUID requestId) {

        for (Object item : checkIns) {
            if (item instanceof JSONObject) {
                JSONObject record = (JSONObject) item;

                if (requestId.toString().equals(
                        record.get("requestId"))) {
                    return record;
                }
            }
        }

        return null;
    }

    // Updates the existing Carter request directly in its saved JSON.
    // This avoids depending on the request being reloaded into DataBase.
    private static void completeSavedCarterRequest() {
        Path file = jsonDirectory().resolve("assistanceRequests.json");

        try (Reader reader = Files.newBufferedReader(
                file, StandardCharsets.UTF_8)) {

            Object parsed = new JSONParser().parse(reader);

            if (!(parsed instanceof JSONArray)) {
                System.out.println("Invalid assistance request JSON.");
                return;
            }

            JSONArray requests = (JSONArray) parsed;
            boolean found = false;
            boolean changed = false;

            for (Object item : requests) {
                if (!(item instanceof JSONObject)) {
                    continue;
                }

                JSONObject request = (JSONObject) item;

                if (CARTER_REQUEST_ID.toString().equals(
                        request.get("id"))) {

                    found = true;

                    if (!"COMPLETED".equals(request.get("status"))) {
                        request.put("status", "COMPLETED");
                        changed = true;
                    }

                    break;
                }
            }

            if (!found) {
                System.out.println("Carter request not found in JSON.");
                return;
            }

            if (changed) {
                try (Writer writer = Files.newBufferedWriter(
                        file, StandardCharsets.UTF_8)) {
                    writer.write(requests.toJSONString());
                }
                System.out.println(
                        "Carter request corrected to COMPLETED.");
            } else {
                System.out.println(
                        "Carter request is already COMPLETED.");
            }

        } catch (Exception e) {
            throw new IllegalStateException(
                    "Could not update Carter request status.", e);
        }
    }

    private static void showJson(String fileName) {
        Path file = jsonDirectory().resolve(fileName);

        System.out.println("\n--- " + fileName + " ---");

        try {
            if (!Files.exists(file)) {
                System.out.println("No file yet.");
                return;
            }

            System.out.println(new String(
                    Files.readAllBytes(file),
                    StandardCharsets.UTF_8));

        } catch (IOException e) {
            System.out.println("Could not read " + file);
        }
    }

    private static void showSavedFiles() {
        showJson("users.json");
        showJson("assistanceRequests.json");
        showJson("shelters.json");
        showJson(CHECKIN_FILE);
    }

    private static void verifySavedCheckIn() {
        System.out.println("\n===== SAVED DATA VERIFICATION =====");

        JSONArray saved = loadCheckIns();
        JSONObject record = findCheckIn(saved, CARTER_REQUEST_ID);

        if (record == null) {
            System.out.println("No saved Carter check-in found.");
            return;
        }

        System.out.println("Shelter: " + record.get("shelterName"));
        System.out.println("Occupants: " + record.get("occupants"));
        System.out.println("Pet: " + record.get("petName"));
        System.out.println("Assignment saved: "
                + record.get("shelterId"));
        System.out.println("Check-in persisted in JSON.");
    }

    public static void main(String[] args) {
        HurricaneUI ui = new HurricaneUI();

        ui.run();

        JSONArray savedCheckIns = loadCheckIns();
        JSONObject carterCheckIn = findCheckIn(
                savedCheckIns, CARTER_REQUEST_ID);

        if (carterCheckIn == null) {
            ui.scenarioScarlett();
        } else {
            System.out.println(
                    "\nCarter family already checked in. "
                    + "Skipping request creation.");
        }

        System.out.println("\n===== JSON BEFORE CHECK-IN =====");
        showSavedFiles();

        ui.scenarioCheckInFamily();

        System.out.println("\n===== JSON AFTER CHECK-IN =====");
        showSavedFiles();

        verifySavedCheckIn();
    }
}
