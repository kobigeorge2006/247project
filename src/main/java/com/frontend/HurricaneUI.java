package com.frontend;

import com.model.HurricaneReliefSystem;
import com.model.data.DataBase;
import com.model.other.Shelter;
import com.model.user.Administrator;
import java.util.ArrayList;
import java.util.UUID;
import com.location.Address;
import com.model.requests.*;
import com.model.user.User;

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
        scenarioRosa();
        scenarioExportandRole();
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

        boolean requestExists = false;
        for (AssistanceRequest request : db.getAssistanceRequests()) {
            if (CARTER_REQUEST_ID.equals(request.getId())) {
                requestExists = true;
                break;
            }
        }
        if (!requestExists) {
            db.addAssistanceRequest(CARTER_REQUEST_ID, "JCarter@gmail.com",
                    new Address("247 Palmetto Harbor Lane", "Charleston",
                            "SC", "29403"),
                    AidCategory.MEDICAL, Severity.HIGH,
                    RequestStatus.SUBMITTED,
                    "My daughter sophie needs medical assistance my family "
                            + "of 4 and my dog need transportation to a shelter too",
                    new Address("247 Palmetto Harbor Lane", "Charleston",
                            "SC", "29403"),
                    true);
        }
    }

    // Scenario 2: Rosa Delgado signs up as a volunteer and views her dashboard
    public void scenarioRosa() {
        System.out.println("\nScenario 2: Signup");

        if (system.signUp("Rosa", "Delgado","rdelgado", "password123",
                "RDelgado@email.com", true, "8035551234")) {
            System.out.println("Account created successfully.");
        } else {
            System.out.println("Using Rosa's existing account.");
        }

        if (system.logIn("rdelgado", "password123")) {
            User currentUser = system.getCurrentUser();

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

    // Scenario 3: Rosa exports the Carter trip and attempts restricted actions
    public void scenarioExportandRole() {
        System.out.println("\nScenario 3: Carter Family Trip and Role Permissions");

        if (system.isLoggedIn() && system.getCurrentUser().isVolunteer()) {
            Shelter shelter = null;
            for (Shelter candidate : system.getAllShelters()) {
                if ("Emergency Relief Shelter".equalsIgnoreCase(
                        candidate.getName())) {
                    shelter = candidate;
                    break;
                }
            }

            if (shelter == null) {
                System.out.println("No selected shelter is available.");
                return;
            }

            Address carterAddress = new Address(
                    "247 Palmetto Harbor Lane", "Charleston", "SC", "29403");
            AssistanceRequest request = system.createAssistanceRequest(
                    CARTER_REQUEST_ID,
                    "John Carter | 301-123-4565 | JCarter@gmail.com",
                    carterAddress,
                    AidCategory.MEDICAL,
                    Severity.HIGH,
                    "Sophie needs medical assistance.",
                    carterAddress,
                    true);

            if (!system.assignRequestToCurrentVolunteer(request)) {
                System.out.println("The Carter request could not be assigned.");
                return;
            }

            System.out.println("Carter relief request assigned to "
                    + request.getAssignedVolunteerUsername() + ".");

            boolean shelterCreated = system.createShelter(
                    "Rosa's Volunteer Shelter", "123 Relief Way",
                    "29401", 25);
            if (!shelterCreated) {
                System.out.println(
                        "Shelter creation denied: Rosa is not an administrator.");
            }

            Address rosaLocation = new Address(
                    "1 Vendue Range", "Charleston", "SC", "29401");
            try {
                Path exportedTrip = system.exportTripInformation(
                        Paths.get("Carter_Family_Trip.txt"),
                        request, shelter, rosaLocation);
                System.out.println("Printable trip exported to "
                        + exportedTrip + ".");
            } catch (IOException e) {
                throw new IllegalStateException(
                        "Could not export the Carter family trip.", e);
            }

            String completionResult = system.completeRequest(
                    CARTER_REQUEST_ID);
            System.out.println(completionResult + " Request status: "
                    + request.getStatus() + ", assigned to "
                    + request.getAssignedVolunteerUsername() + ".");
        } else {
            System.out.println("Error: Volunteer Rosa is not currently logged in.");
        }
    }


    // Scenario 7: Shelter manager checks in the Carter family and Buddy
    public void scenarioCheckInFamily() {

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
                addJsonValues(shelterOccupants, people);
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
        addJsonValue(occupants, "John Carter");
        addJsonValue(occupants, "Sophie Carter");
        addJsonValue(occupants, "Mary Carter");

        JSONObject record = new JSONObject();
        putJsonValue(record, "requestId", CARTER_REQUEST_ID.toString());
        putJsonValue(record, "shelterId", shelter.getId().toString());
        putJsonValue(record, "shelterName", shelter.getName());
        putJsonValue(record, "shelterAddress", shelter.getAddress());
        putJsonValue(record, "manager", "felix");
        putJsonValue(record, "occupants", occupants);
        putJsonValue(record, "humanCount", 3);
        putJsonValue(record, "petName", "Buddy");
        putJsonValue(record, "petCount", 1);
        putJsonValue(record, "checkedIn", true);

        addJsonValue(checkIns, record);
        saveCheckIns(checkIns);

        System.out.println("\nFamily Check-In");
        System.out.println("Checked in: " + occupants);
        System.out.println("Human occupancy: " + (occupancy + 3));
        System.out.println("Available spaces: "
                + (shelter.getCapacity() - occupancy - 3));
        System.out.println("Accompanying pet: Buddy");
        System.out.println("Assignment: " + shelter.getName());

        System.out.println("\nRelief Request");
        System.out.println("Request ID: " + carterRequest.getId());
        System.out.println("Status: " + carterRequest.getStatus());
        System.out.println("Assigned to: "
                + carterRequest.getAssignedVolunteerUsername());
        System.out.println("Check-in saved to " + CHECKIN_FILE);
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

    @SuppressWarnings("unchecked")
    private static void addJsonValue(JSONArray array, Object value) {
        array.add(value);
    }

    @SuppressWarnings("unchecked")
    private static void addJsonValues(JSONArray destination,
            JSONArray source) {
        destination.addAll(source);
    }

    @SuppressWarnings("unchecked")
    private static void putJsonValue(JSONObject object, String key,
            Object value) {
        object.put(key, value);
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
