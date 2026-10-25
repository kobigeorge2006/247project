package com.model.requests;

import java.util.UUID;

/**
 * Represents an emergency message intended for a specified target area.
 */
public class EmergencyAlert {
    /** Unique identifier for this alert. */
    UUID id;
    /** Text of the emergency message. */
    String message;
    /** Area to which the alert applies. */
    String targetArea;

    /**
     * Creates an empty emergency alert.
     */
    public EmergencyAlert() {
    }
}
