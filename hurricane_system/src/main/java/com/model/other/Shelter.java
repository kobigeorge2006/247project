package com.model.other;
import com.model.requests.AssistanceRequest;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.json.simple.JSONObject;

// Stores information about a shelter.
public class Shelter {

    private UUID id;
    private String name;
    private Location location;
    private int capacity;
    private ShelterStatus status;
    private ArrayList<Accommodation> accommodations;
    private HashMap<String, Integer> inventory;

    public Shelter(UUID id, String name, String address, int capacity,
                   ShelterStatus status,
                   ArrayList<Accommodation> accommodations) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.status = status;
        this.accommodations = accommodations;
        this.inventory = new HashMap<>();
    }

    public Shelter(JSONObject jsonShelter) {
        this.id = UUID.fromString((String) jsonShelter.get("id"));
        this.name = (String) jsonShelter.get("name");
        this.location = (Location) jsonShelter.get("location");
        this.capacity = (int) jsonShelter.get("capacity");
        this.status = (ShelterStatus) jsonShelter.get("status");
        this.accommodations = (ArrayList<Accommodation>) jsonShelter.get("accommodations");
        this.inventory = (HashMap<String, Integer>) jsonShelter.get("inventory");
    }

    public boolean updateInventory(String item, int quantity) {
        return false;
    }

    public boolean checkAvailability() {
        return false;
    }

    public boolean reserveCapacity(AssistanceRequest request) {
        return false;
    }
}