package com.model.requests;

import java.util.UUID;

/**
 * Represents an emergency message intended for a specified target area.
 */
public class EmergencyAlert {
    /** Unique identifier for this alert. */
    public UUID id;
    /** Text of the emergency message. */
    public String message;
    /** Area to which the alert applies. */
    public String targetArea;

    /**
     * Creates an empty emergency alert.
     */
    public EmergencyAlert() {
    }
}
