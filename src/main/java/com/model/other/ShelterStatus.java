package com.model.other;

/**
 * Operational states for a shelter.
 */
public enum ShelterStatus {
    /** The shelter is open to accept guests. */
    OPEN,
    /** The shelter is closed because it has reached capacity. */
    FULL_CLOSED,
    /** The shelter has been evacuated. */
    EVACUATED,
    /** The shelter is approaching its capacity. */
    NEAR_CAPACITY
}