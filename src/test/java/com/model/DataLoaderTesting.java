package com.model;

import org.junit.Test;

import com.model.user.*;
import com.model.other.*;
import com.model.requests.*;
import com.model.data.*;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Tests the DataLoader class to ensure all core application data models 
 * parse and load correctly from persistent JSON storage.
 */
public class DataLoaderTesting {

    // Written by Jake Sheahan

    /**
     * Tests loading user profiles from JSON storage, verifying expected 
     * user counts, usernames, and identifier attributes.
     */
    @Test
    public void testLoadUsers() {
        ArrayList<User> users = DataLoader.loadUsers();

        assertNotNull("Loaded users list should not be null", users);
        assertFalse("Loaded users list should not be empty", users.isEmpty());
        assertEquals(5, users.size());

        User firstUser = users.get(0);
        assertEquals("asmith", firstUser.getUsername());
        assertEquals("f8056b11-a458-498b-b348-5be5a6ff3aa6", firstUser.getId().toString());
    }

    /**
     * Tests loading emergency shelters into the map structure, validating 
     * specific target UUIDs, shelter names, and maximum capacities.
     */
    @Test
    public void testLoadShelters() {
        HashMap<UUID, Shelter> shelters = DataLoader.loadShelters();

        assertNotNull("Loaded shelters map should not be null", shelters);
        assertFalse("Loaded shelters map should not be empty", shelters.isEmpty());
        assertEquals(4, shelters.size());

        
        UUID targetId = UUID.fromString("c6d1474e-5c9f-4c33-8e7a-2b1e740a1231");
        Shelter shelter = shelters.get(targetId);

        assertNotNull("Shelter with target UUID should exist in map", shelter);
        assertEquals("Columbia Emergency Relief Shelter", shelter.getName());
        assertEquals(100, shelter.getCapacity());
    }

    /**
     * Tests loading assistance requests from storage to verify proper 
     * list population and unique identifier validity.
     */
    @Test
    public void testLoadRequests() {
        ArrayList<AssistanceRequest> requests = DataLoader.loadRequests();

        assertNotNull("Loaded requests list should not be null", requests);
        assertFalse("Loaded requests list should not be empty", requests.isEmpty());
        
        assertEquals(3, requests.size());

        AssistanceRequest firstRequest = requests.get(0);
        assertNotNull("Request ID should not be null", firstRequest.getId());
    }

    /**
     * Tests that message discussion threads tied to assistance requests 
     * are successfully populated and hydrated from the source.
     */
    @Test
    public void testLoadRequestsMessages() {
        ArrayList<AssistanceRequest> requests = DataLoader.loadRequests();

        assertNotNull("Loaded requests list should not be null", requests);
        assertFalse("Loaded requests list should not be empty", requests.isEmpty());

        AssistanceRequest firstRequest = requests.get(0);
        assertNotNull("Messages list should not be null", firstRequest.getMessages());
        
        assertFalse("Messages list should be populated from JSON", firstRequest.getMessages().isEmpty());
    }

    /**
     * Tests loading hurricane event tracking logs to verify proper parsing, 
     * total event counts, and matching unique identifiers.
     */
    @Test
    public void testLoadHurricanes() {
        ArrayList<HurricaneEvent> hurricanes = DataLoader.loadHurricanes();

        assertNotNull("Loaded hurricanes list should not be null", hurricanes);
        assertFalse("Loaded hurricanes list should not be empty", hurricanes.isEmpty());
        assertEquals(2, hurricanes.size());

        HurricaneEvent firstHurricane = hurricanes.get(0);
        assertEquals("9c858901-8a57-4791-81fe-4c455b099bc9", firstHurricane.getId().toString());
    }
}
