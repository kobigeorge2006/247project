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

    /**
     * Returns the shelter's unique identifier.
     *
     * @return the shelter identifier
     */
    public UUID getId() {
        return id;
    }

    /**
     * Returns the shelter's street address.
     *
     * @return the street address
     */
    public String getAddress() {
        return location.getStreetAddress();
    }

    /**
     * Updates the shelter's unique identifier.
     *
     * @param id the shelter identifier
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Returns the shelter's name.
     *
     * @return the shelter name
     */
    public String getName() {
        return name;
    }

    /**
     * Updates the shelter's name.
     *
     * @param name the shelter name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the shelter's location.
     *
     * @return the shelter location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Updates the shelter's location.
     *
     * @param location the shelter location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Returns the shelter's capacity.
     *
     * @return the shelter capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Updates the shelter's capacity.
     *
     * @param capacity the shelter capacity
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Returns the shelter's operational status.
     *
     * @return the shelter status
     */
    public ShelterStatus getStatus() {
        return status;
    }

    /**
     * Updates the shelter's operational status.
     *
     * @param status the shelter status
     */
    public void setStatus(ShelterStatus status) {
        this.status = status;
    }

    /**
     * Returns the accommodations provided by the shelter.
     *
     * @return the shelter accommodations
     */
    public ArrayList<Accommodation> getAccommodations() {
        return accommodations;
    }

    /**
     * Replaces the accommodations provided by the shelter.
     *
     * @param accommodations the shelter accommodations
     */
    public void setAccommodations(ArrayList<Accommodation> accommodations) {
        this.accommodations = accommodations;
    }

    /**
     * Returns inventory quantities keyed by item name.
     *
     * @return the shelter inventory
     */
    public HashMap<String, Integer> getInventory() {
        return inventory;
    }

    /**
     * Replaces the shelter inventory.
     *
     * @param inventory inventory quantities keyed by item name
     */
    public void setInventory(HashMap<String, Integer> inventory) {
        this.inventory = inventory;
    }

    /**
     * Returns a string describing this shelter's name, location, capacity,
     * status, and accommodations.
     *
     * @return the shelter description
     */
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