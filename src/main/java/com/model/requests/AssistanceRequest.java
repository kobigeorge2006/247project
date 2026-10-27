package com.model.requests;

import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONObject;

import com.location.Address;
import com.model.user.User;

/**
 * A request for help submitted to the Hurricane Relief System.
 */
public class AssistanceRequest {
    private UUID id;
    private String contactInfo;
    private Address address;
    private AidCategory aidCategory;
    private Severity severity;
    private RequestStatus status;
    private String message;
    private ArrayList<Message> messages;
    private Address location;
    private Boolean hasPet;
    private String assignedVolunteerUsername;

    /**
     * Creates an assistance request with the supplied details.
     *
     * @param id unique request identifier
     * @param contactInfo contact details for the requester
     * @param address the requester's address
     * @param aidCategory requested category of aid
     * @param severity urgency of the request
     * @param status current request status
     * @param message request description
     * @param location location where assistance is needed
     * @param hasPet whether the requester has a pet
     */
    public AssistanceRequest(UUID id, String contactInfo, Address address,
                            AidCategory aidCategory, Severity severity,
                            RequestStatus status, String message,
                            Address location, Boolean hasPet) {
        this.id = id;
        this.contactInfo = contactInfo;
        this.address = address;
        this.aidCategory = aidCategory;
        this.severity = severity;
        this.status = status;
        this.message = message;
        this.messages = new ArrayList<>();
        this.location = location;
        this.hasPet = hasPet;
        this.assignedVolunteerUsername = null;
    }

    /**
     * Creates an assistance request from its JSON representation.
     *
     * @param jsonAssistanceRequest JSON fields for the request
     */
    public AssistanceRequest(JSONObject jsonAssistanceRequest) {
        this.id = UUID.fromString((String) jsonAssistanceRequest.get("id"));
        this.contactInfo = (String) jsonAssistanceRequest.get("contactInfo");
        this.address = (Address) jsonAssistanceRequest.get("address");
        this.aidCategory = jsonAssistanceRequest.get("aidCategory") == null
                ? null
                : AidCategory.valueOf((String) jsonAssistanceRequest.get("aidCategory"));
        this.severity = jsonAssistanceRequest.get("severity") == null
                ? null
                : Severity.valueOf((String) jsonAssistanceRequest.get("severity"));
        this.status = jsonAssistanceRequest.get("status") == null
                ? null
                : RequestStatus.valueOf((String) jsonAssistanceRequest.get("status"));
        this.message = (String) jsonAssistanceRequest.get("message");
        this.messages = new ArrayList<>();
        this.location = (Address) jsonAssistanceRequest.get("location");
        this.hasPet = (Boolean) jsonAssistanceRequest.get("hasPet");
        this.assignedVolunteerUsername =
                (String) jsonAssistanceRequest.get("assignedVolunteer");
    }

    /**
     * Changes the request's status.
     *
     * @param status the new status
     */
    public void updateStatus(RequestStatus status) {
        this.status = status;
    }

    /**
     * Adds a message from a user to this request's conversation.
     *
     * @param user the message sender
     * @param message the message text
     * @return {@code true} after the message is added
     */
    public boolean addMessage(User user, String message) {
        if (this.messages == null) {
            this.messages = new ArrayList<>();
        }
        this.messages.add(new Message(user, message));
        return true;
    }

    /**
     * Returns the request identifier.
     *
     * @return the unique request identifier
     */
    public UUID getId() {
        return id;
    }

    /**
     * Updates the request identifier.
     *
     * @param id the unique request identifier
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Returns the requester's contact information.
     *
     * @return the contact information
     */
    public String getContactInfo() {
        return contactInfo;
    }

    /**
     * Updates the requester's contact information.
     *
     * @param contactInfo the contact information
     */
    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    /**
     * Returns the requester's address.
     *
     * @return the requester's address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Updates the requester's address.
     *
     * @param address the requester's address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Returns the requested category of aid.
     *
     * @return the aid category
     */
    public AidCategory getAidCategory() {
        return aidCategory;
    }

    /**
     * Updates the requested category of aid.
     *
     * @param aidCategory the aid category
     */
    public void setAidCategory(AidCategory aidCategory) {
        this.aidCategory = aidCategory;
    }

    /**
     * Returns the request's urgency.
     *
     * @return the request severity
     */
    public Severity getSeverity() {
        return severity;
    }

    /**
     * Updates the request's urgency.
     *
     * @param severity the request severity
     */
    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    /**
     * Returns the current request status.
     *
     * @return the request status
     */
    public RequestStatus getStatus() {
        return status;
    }

    /**
     * Updates the request status.
     *
     * @param status the new request status
     */
    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    /**
     * Returns the request description.
     *
     * @return the request message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Updates the request description.
     *
     * @param message the request message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Returns the conversation messages associated with the request.
     *
     * @return the request messages
     */
    public ArrayList<Message> getMessages() {
        return messages;
    }

    /**
     * Sets the conversation messages, replacing {@code null} with an empty list.
     *
     * @param messages the replacement messages
     */
    public void setMessages(ArrayList<Message> messages) {
        this.messages = (messages != null) ? messages : new ArrayList<>();
    }

    /**
     * Returns the location where assistance is needed.
     *
     * @return the assistance location
     */
    public Address getLocation() {
        return location;
    }

    /**
     * Updates the location where assistance is needed.
     *
     * @param location the assistance location
     */
    public void setLocation(Address location) {
        this.location = location;
    }

    /**
     * Indicates whether the requester has a pet.
     *
     * @return {@code true} if the requester has a pet, {@code false} if not,
     *         or {@code null} if unspecified
     */
    public Boolean getHasPet() {
        return hasPet;
    }

    /**
     * Sets whether the requester has a pet.
     *
     * @param hasPet whether the requester has a pet
     */
    public void setHasPet(Boolean hasPet) {
        this.hasPet = hasPet;
    }

    /**
     * Returns the username of the volunteer assigned to this request.
     *
     * @return the assigned volunteer username, or {@code null} if unassigned
     */
    public String getAssignedVolunteerUsername() {
        return assignedVolunteerUsername;
    }

    /**
     * Assigns this request to a volunteer username.
     *
     * @param username the assigned volunteer username
     */
    public void setAssignedVolunteerUsername(String username) {
        this.assignedVolunteerUsername = username;
    }
}
