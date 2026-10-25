package com.model.requests;

import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONObject;

import com.location.Address;
import com.model.user.User;

/**
 * @author Avery Jones
 * 
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
    }

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
    }

    public void updateStatus(RequestStatus status) {
        this.status = status;
    }

    public boolean addMessage(User user, String message) {
        if (this.messages == null) {
            this.messages = new ArrayList<>();
        }
        this.messages.add(new Message(user, message));
        return true;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public AidCategory getAidCategory() {
        return aidCategory;
    }

    public void setAidCategory(AidCategory aidCategory) {
        this.aidCategory = aidCategory;
    }

    public Severity getSeverity() {
        return severity;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = (messages != null) ? messages : new ArrayList<>();
    }

    public Address getLocation() {
        return location;
    }

    public void setLocation(Address location) {
        this.location = location;
    }

    public Boolean getHasPet() {
        return hasPet;
    }

    public void setHasPet(Boolean hasPet) {
        this.hasPet = hasPet;
    }
}


