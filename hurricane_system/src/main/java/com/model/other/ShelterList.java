package com.model.other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import com.model.data.DataBase;

//Manages shelters in the Hurricane Relief System
 
public class ShelterList {

    private HashMap<UUID, Shelter> shelters;

    public ShelterList() {
        shelters = DataBase.getInstance().getShelters();
    }

    // Finds shelters by ZIP code
    public ArrayList<Shelter> findShelters(String zipCode) {
        ArrayList<Shelter> results = new ArrayList<>();

        for (Shelter shelter : shelters.values()) {
            Location location = shelter.getLocation();

            if (location != null && zipCode.equals(location.getZipCode())) {
                results.add(shelter);
            }
        }

        return results;
    }

    // Adds a new shelter
    public boolean addShelter(Shelter shelter) {
        if (shelter == null || shelters.containsKey(shelter.getId())) {
            return false;
        }

        shelters.put(shelter.getId(), shelter);
        return true;
    }

    // Finds a shelter using its ID
    public Shelter getShelter(UUID id) {
        return shelters.get(id);
    }
}