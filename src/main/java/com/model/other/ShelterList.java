package com.model.other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import com.model.data.DataBase;
import com.model.data.DataWriter;

/**
 * Manages shelters in the Hurricane Relief System.
 */
public class ShelterList {

    private HashMap<UUID, Shelter> shelters;

    /**
     * Creates a manager backed by the shared database's shelter map.
     */
    public ShelterList() {
        shelters = DataBase.getInstance().getShelters();
    }

    /**
     * Finds shelters whose location has the given ZIP code.
     *
     * @param zipCode ZIP code to search for
     * @return matching shelters
     */
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

    /**
     * Adds a shelter unless it is {@code null} or its identifier is already
     * present.
     *
     * @param shelter shelter to add
     * @return {@code true} if the shelter was added
     */
    public boolean addShelter(Shelter shelter) {
        if (shelter == null || shelters.containsKey(shelter.getId())) {
            return false;
        }

        shelters.put(shelter.getId(), shelter);
        return true;
    }

    /**
     * Looks up a shelter by its identifier.
     *
     * @param id shelter identifier
     * @return the matching shelter, or {@code null} if none exists
     */
    public Shelter getShelter(UUID id) {
        return shelters.get(id);
    }

    /**
     * Returns a new list containing all shelters.
     *
     * @return all shelters currently managed
     */
    public ArrayList<Shelter> getAllShelters() {
        return new ArrayList<>(shelters.values());
    }


    /**
     * Persists the managed shelters.
     */
    public void saveShelters() {
        DataWriter.saveShelters(shelters);
    }
}