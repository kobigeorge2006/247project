package com.model.requests;

/**
 * Lifecycle states for an assistance request.
 */
public enum RequestStatus {
    /** The request has been submitted and is awaiting assignment. */
    SUBMITTED,
    /** The request has been assigned to a responder. */
    ASSIGNED,
    /** Work on the request is underway. */
    IN_PROGRESS,
    /** The request has been fulfilled. */
    COMPLETED
}
