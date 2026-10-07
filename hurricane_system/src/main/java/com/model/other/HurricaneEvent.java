package com.model.other;

import java.util.UUID;
import org.json.simple.JSONObject;

//Stores information about a hurricane event.
public class HurricaneEvent {

    private UUID id;
    private HurricaneStatus status;
    private Location location;
    private double maxWindSpeed;
    private String forcastPath;

    public HurricaneEvent(UUID id, HurricaneStatus status, Location location,
                          double maxWindSpeed, String forcastPath) {
        this.id = id;
        this.status = status;
        this.location = location;
        this.maxWindSpeed = maxWindSpeed;
        this.forcastPath = forcastPath;
    }
    public HurricaneEvent(JSONObject jsonHurricane) {
        this.id = UUID.fromString((String) jsonHurricane.get("id"));
        this.status = (HurricaneStatus) jsonHurricane.get("status");
        this.location = (Location) jsonHurricane.get("location");
        this.maxWindSpeed = (double) jsonHurricane.get("maxWindSpeed");
        this.forcastPath = (String) jsonHurricane.get("forcastPath");
    }

    public HurricaneStatus getStatus() {
        return status;
    }
    public UUID getId() {
        return id;
    }
    public Location getLocation() {
        return location;
    }
    public double getMaxWindSpeed() {
        return maxWindSpeed;
    }
    public String getForcastPath() {
        return forcastPath;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public void setStatus(HurricaneStatus status) {
        this.status = status;
    }
    public void setLocation(Location location) {
        this.location = location;
    }
    public void setMaxWindSpeed(double maxWindSpeed) {
        this.maxWindSpeed = maxWindSpeed;
    }
    public void setForcastPath(String forcastPath) {
        this.forcastPath = forcastPath;
    }
    
    
}