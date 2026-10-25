package com.model.other;

import com.model.requests.AssistanceRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import org.json.simple.JSONObject;

/**
 * Stores information about a shelter.
 */
public class Shelter {

    private UUID id;
    private String name;
    private Location location;
    private int capacity;
    private ShelterStatus status;
    private ArrayList<Accommodation> accommodations;
    private HashMap<String, Integer> inventory;

    /**
     * Creates a new shelter.
     *
     * @param id the shelter's unique ID
     * @param name the shelter's name
     * @param address the shelter's street address
     * @param capacity the shelter's maximum capacity
     * @param status the shelter's current status
     * @param accommodations the shelter's accommodations
     */
    public Shelter(UUID id, String name, String address, int capacity,
                   ShelterStatus status,
                   ArrayList<Accommodation> accommodations) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.status = status;
        this.accommodations = accommodations;
        this.location = new Location(address);
        this.inventory = new HashMap<>();
    }

    /**
     * Creates a shelter using information from a JSON file.
     *
     * @param jsonShelter the JSON object containing shelter information
     */
    public Shelter(JSONObject jsonShelter) {
        this.id = UUID.fromString((String) jsonShelter.get("id"));
        this.name = (String) jsonShelter.get("name");

        String address = (String) jsonShelter.get("address");
        this.location = new Location(address);

        String zipCode = (String) jsonShelter.get("zipCode");
        if (zipCode != null) {
            this.location.setZipCode(zipCode);
        }

        this.capacity = ((Number) jsonShelter.get("capacity")).intValue();

        String shelterStatus = (String) jsonShelter.get("status");
        this.status = ShelterStatus.valueOf(shelterStatus);

        this.accommodations = new ArrayList<>();
        this.inventory = new HashMap<>();

        JSONObject jsonInventory = (JSONObject) jsonShelter.get("inventory");

        if (jsonInventory != null) {
            for (Object key : jsonInventory.keySet()) {
                String item = (String) key;
                Number quantity = (Number) jsonInventory.get(item);
                this.inventory.put(item, quantity.intValue());
            }
        }
    }

    /**
     * Updates the quantity of an item in the shelter's inventory.
     *
     * @param item the name of the inventory item
     * @param quantity the quantity to add
     * @return true if the inventory was updated
     */
    public boolean updateInventory(String item, int quantity) {
        if (item == null || item.isEmpty() || quantity < 0) {
            return false;
        }

        inventory.put(item, inventory.getOrDefault(item, 0) + quantity);
        return true;
    }

    /**
     * Checks whether the shelter is open and has available capacity.
     *
     * @return true if the shelter is open and has capacity
     */
    public boolean checkAvailability() {
        return status == ShelterStatus.OPEN && capacity > 0;
    }

    /**
     * Reserves capacity for an assistance request.
     *
     * @param request the assistance request
     * @return false because reservation handling is not implemented yet
     */
    public boolean reserveCapacity(AssistanceRequest request) {
        return false;
    }

    public UUID getId() {
        return id;
    }

    public String getAddress() {
        return location.getStreetAddress();
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public ShelterStatus getStatus() {
        return status;
    }

    public void setStatus(ShelterStatus status) {
        this.status = status;
    }

    public ArrayList<Accommodation> getAccommodations() {
        return accommodations;
    }

    public void setAccommodations(ArrayList<Accommodation> accommodations) {
        this.accommodations = accommodations;
    }

    public HashMap<String, Integer> getInventory() {
        return inventory;
    }

    public void setInventory(HashMap<String, Integer> inventory) {
        this.inventory = inventory;
    }

    @Override 
    public String toString(){
        return "Shelter{" +
                ", name='" + name + '\'' +
                ", location=" + location +
                ", capacity=" + capacity +
                ", status=" + status +
                ", accommodations=" + accommodations +
                '}';
    }
}