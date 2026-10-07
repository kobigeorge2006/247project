package com.model.other;

import java.util.UUID;

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

    public HurricaneStatus getStatus() {
        return status;
    }
}