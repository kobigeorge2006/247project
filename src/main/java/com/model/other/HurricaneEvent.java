package com.model.other;

import java.util.UUID;
import org.json.simple.JSONObject;

/**
 * Stores the status, location, wind speed, and forecast for a hurricane.
 */
public class HurricaneEvent {

    private UUID id;
    private HurricaneStatus status;
    private Location location;
    private double maxWindSpeed;
    private String forcastPath;

    /**
     * Creates a hurricane event.
     *
     * @param id unique event identifier
     * @param status current event status
     * @param location event location
     * @param maxWindSpeed maximum recorded wind speed
     * @param forcastPath forecast path description
     */
    public HurricaneEvent(UUID id, HurricaneStatus status, Location location,
                          double maxWindSpeed, String forcastPath) {
        this.id = id;
        this.status = status;
        this.location = location;
        this.maxWindSpeed = maxWindSpeed;
        this.forcastPath = forcastPath;
    }

    /**
     * Creates a hurricane event from a JSON object.
     *
     * @param jsonHurricane JSON fields for the event
     */
    public HurricaneEvent(JSONObject jsonHurricane) {
        this.id = UUID.fromString((String) jsonHurricane.get("id"));
        this.status = (HurricaneStatus) jsonHurricane.get("status");
        this.location = (Location) jsonHurricane.get("location");
        this.maxWindSpeed = (double) jsonHurricane.get("maxWindSpeed");
        this.forcastPath = (String) jsonHurricane.get("forcastPath");
    }

    /**
     * Returns the current hurricane status.
     *
     * @return the event status
     */
    public HurricaneStatus getStatus() {
        return status;
    }

    /**
     * Returns the unique hurricane event identifier.
     *
     * @return the event identifier
     */
    public UUID getId() {
        return id;
    }

    /**
     * Returns the hurricane event location.
     *
     * @return the event location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Returns the maximum wind speed recorded for the event.
     *
     * @return the maximum wind speed
     */
    public double getMaxWindSpeed() {
        return maxWindSpeed;
    }

    /**
     * Returns the forecast path description.
     *
     * @return the forecast path
     */
    public String getForcastPath() {
        return forcastPath;
    }

    /**
     * Updates the unique hurricane event identifier.
     *
     * @param id the event identifier
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Updates the hurricane status.
     *
     * @param status the event status
     */
    public void setStatus(HurricaneStatus status) {
        this.status = status;
    }

    /**
     * Updates the hurricane event location.
     *
     * @param location the event location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Updates the maximum wind speed for the event.
     *
     * @param maxWindSpeed the maximum wind speed
     */
    public void setMaxWindSpeed(double maxWindSpeed) {
        this.maxWindSpeed = maxWindSpeed;
    }

    /**
     * Updates the forecast path description.
     *
     * @param forcastPath the forecast path
     */
    public void setForcastPath(String forcastPath) {
        this.forcastPath = forcastPath;
    }

    /**
     * Returns the event location as a formatted address.
     *
     * @return the formatted event address
     */
    public String getAddress() {
        return location.formateFullAddress();
    }
}