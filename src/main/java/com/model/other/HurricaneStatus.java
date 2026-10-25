package com.model.other;

/**
 * Monitoring and lifecycle states for a hurricane event.
 */
public enum HurricaneStatus {
    /** The hurricane is currently active. */
    ACTIVE,
    /** Conditions are being monitored for a possible threat. */
    WATCH,
    /** A hurricane threat has been confirmed for an area. */
    WARNING,
    /** The hurricane event has ended. */
    ENDED
}