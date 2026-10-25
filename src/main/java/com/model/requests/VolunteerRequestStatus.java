package com.model.requests;

/**
 * Review states for a volunteer application.
 */
public enum VolunteerRequestStatus {
    /** The application is awaiting review. */
    PENDING,
    /** The application has been accepted. */
    APPROVED,
    /** The application has been declined. */
    REJECTED
}
