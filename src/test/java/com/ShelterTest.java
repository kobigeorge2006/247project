package com;

import static org.junit.jupiter.api.Assertions.*;

import com.model.other.Shelter;
import com.model.other.ShelterStatus;
import com.model.other.Accommodation;

import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Shelter class.
 *
 * AI assistance was used to create these tests.
 *
 * Test reasoning table:
 * Add your own reasoning for the selected test cases here
 * before submitting, as required by the instructor.
 */
public class ShelterTest {

    private Shelter shelter;

    @BeforeEach
    public void setUp() {
        shelter = new Shelter(
                UUID.randomUUID(),
                "Columbia Relief Shelter",
                "123 Main Street",
                100,
                ShelterStatus.OPEN,
                new ArrayList<Accommodation>()
        );
    }

    @Test
    public void newShelterStartsWithEmptyInventory() {
        assertTrue(shelter.getInventory().isEmpty());
    }

    @Test
    public void addingWaterUpdatesInventory() {
        assertTrue(shelter.updateInventory("Water", 30));
        assertEquals(30, shelter.getInventory().get("Water"));
    }

    @Test
    public void addingMoreWaterIncreasesExistingQuantity() {
        shelter.updateInventory("Water", 20);

        assertTrue(shelter.updateInventory("Water", 10));
        assertEquals(30, shelter.getInventory().get("Water"));
    }

    @Test
    public void addingDifferentItemsKeepsSeparateQuantities() {
        shelter.updateInventory("Water", 30);
        shelter.updateInventory("Food", 15);

        assertEquals(30, shelter.getInventory().get("Water"));
        assertEquals(15, shelter.getInventory().get("Food"));
    }

    @Test
    public void negativeInventoryQuantityIsRejected() {
        assertFalse(shelter.updateInventory("Water", -5));
        assertFalse(shelter.getInventory().containsKey("Water"));
    }

    @Test
    public void emptyInventoryItemNameIsRejected() {
        assertFalse(shelter.updateInventory("", 10));
    }

    @Test
    public void nullInventoryItemNameIsRejected() {
        assertFalse(shelter.updateInventory(null, 10));
    }

    @Test
    public void zeroInventoryQuantityIsAccepted() {
        assertTrue(shelter.updateInventory("Water", 0));
        assertEquals(0, shelter.getInventory().get("Water"));
    }

    @Test
    public void openShelterWithAvailableCapacityReturnsTrue() {
        assertTrue(shelter.checkAvailability());
    }

    @Test
    public void fullShelterIsNotAvailable() {
        shelter.setStatus(ShelterStatus.FULL_CLOSED);

        assertFalse(shelter.checkAvailability());
    }

    @Test
    public void evacuatedShelterIsNotAvailable() {
        shelter.setStatus(ShelterStatus.EVACUATED);

        assertFalse(shelter.checkAvailability());
    }

    @Test
    public void openShelterWithZeroCapacityIsNotAvailable() {
        shelter.setCapacity(0);

        assertFalse(shelter.checkAvailability());
    }

    @Test
    public void openShelterWithNegativeCapacityIsNotAvailable() {
        shelter.setCapacity(-5);

        assertFalse(shelter.checkAvailability());
    }

    @Test
    public void nearCapacityShelterIsNotConsideredOpen() {
        shelter.setStatus(ShelterStatus.NEAR_CAPACITY);

        assertFalse(shelter.checkAvailability());
    }

    @Test
    public void reservationCurrentlyReturnsFalse() {
        assertFalse(shelter.reserveCapacity(null));
    }

    @SuppressWarnings("unchecked")
    private JSONObject createShelterJson() {
        JSONObject json = new JSONObject();

        json.put("id", UUID.randomUUID().toString());
        json.put("name", "North Columbia Shelter");
        json.put("address", "789 North Main Street");
        json.put("zipCode", "29203");
        json.put("capacity", 120L);
        json.put("status", "OPEN");

        JSONObject inventory = new JSONObject();
        inventory.put("Water", 30L);

        json.put("inventory", inventory);

        return json;
    }

    @Test
    public void jsonShelterLoadsNameCorrectly() {
        Shelter loaded = new Shelter(createShelterJson());

        assertEquals("North Columbia Shelter", loaded.getName());
    }

    @Test
    public void jsonShelterLoadsAddressCorrectly() {
        Shelter loaded = new Shelter(createShelterJson());

        assertEquals("789 North Main Street", loaded.getAddress());
    }

    @Test
    public void jsonShelterLoadsZipCodeCorrectly() {
        Shelter loaded = new Shelter(createShelterJson());

        assertEquals("29203", loaded.getLocation().getZipCode());
    }

    @Test
    public void jsonShelterLoadsCapacityCorrectly() {
        Shelter loaded = new Shelter(createShelterJson());

        assertEquals(120, loaded.getCapacity());
    }

    @Test
    public void jsonShelterLoadsStatusCorrectly() {
        Shelter loaded = new Shelter(createShelterJson());

        assertEquals(ShelterStatus.OPEN, loaded.getStatus());
    }

    @Test
    public void jsonShelterLoadsInventoryCorrectly() {
        Shelter loaded = new Shelter(createShelterJson());

        assertEquals(30, loaded.getInventory().get("Water"));
    }

    @Test
    public void jsonShelterWithoutInventoryStartsEmpty() {
        JSONObject json = createShelterJson();
        json.remove("inventory");

        Shelter loaded = new Shelter(json);

        assertTrue(loaded.getInventory().isEmpty());
    }

    @Test
    public void jsonShelterWithoutZipCodeCanBeCreated() {
        JSONObject json = createShelterJson();
        json.remove("zipCode");

        Shelter loaded = new Shelter(json);

        assertNotNull(loaded.getLocation());
    }

    @Test
    public void jsonShelterWithInvalidIdThrowsException() {
        JSONObject json = createShelterJson();
        json.put("id", "invalid-id");

        assertThrows(
                IllegalArgumentException.class,
                () -> new Shelter(json)
        );
    }

    @Test
    public void jsonShelterWithInvalidStatusThrowsException() {
        JSONObject json = createShelterJson();
        json.put("status", "INVALID");

        assertThrows(
                IllegalArgumentException.class,
                () -> new Shelter(json)
        );
    }

    @Test
    public void shelterDescriptionContainsName() {
        assertTrue(
                shelter.toString().contains("Columbia Relief Shelter")
        );
    }

    @Test
    public void shelterDescriptionContainsCapacity() {
        assertTrue(
                shelter.toString().contains("capacity=100")
        );
    }

    @Test
    public void shelterDescriptionContainsStatus() {
        assertTrue(
                shelter.toString().contains("status=OPEN")
        );
    }
}