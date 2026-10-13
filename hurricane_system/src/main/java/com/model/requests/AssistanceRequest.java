package com.model.requests;
import java.util.ArrayList;
import java.util.UUID;
import com.model.user.*;
import org.json.simple.JSONObject;
import com.model.other.Location;

/**
 * @author Avery Jones
 * 
 */
public class AssistanceRequest 
{
    private UUID id;
    private String contactInfo;
    private String address;
    private AidCategory AidCategory;
    private Severity severity;
    private RequestStatus status;
    private ArrayList<Message> messages;
    private Location location;

    public AssistanceRequest(UUID id, String contactInfo, String address, AidCategory aidCategory, Severity severity, RequestStatus status, ArrayList<Message> messages, Location location)
    {
        this.id = id;
        this.contactInfo = contactInfo;
        this.address = address;
        this.AidCategory = aidCategory;
        this.status = status;
        this.messages = messages;
        this.location = location;
    }
    public AssistanceRequest(JSONObject jsonAssistanceRequest) {
        this.id = UUID.fromString((String) jsonAssistanceRequest.get("id"));
        this.contactInfo = (String) jsonAssistanceRequest.get("contactInfo");
        this.address = (String) jsonAssistanceRequest.get("address");
        this.AidCategory = (AidCategory) jsonAssistanceRequest.get("aidCategory");
        this.severity = (Severity) jsonAssistanceRequest.get("severity");
        this.status = (RequestStatus) jsonAssistanceRequest.get("status");
        this.messages = (ArrayList<Message>) jsonAssistanceRequest.get("messages");
        this.location = (Location) jsonAssistanceRequest.get("location");
    }


        public void updateStatus(RequestStatus status)
        {
            this.status = status;
        }

        public boolean addMessage(User user, String message)
        {
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
        public String getAddress() {
            return address;
        }
        public void setAddress(String address) {
            this.address = address;
        }
        public AidCategory getAidCategory() {
            return AidCategory;
        }
        public void setAidCategory(AidCategory aidCategory) {
            AidCategory = aidCategory;
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
        public ArrayList<Message> getMessages() {
            return messages;
        }
        public void setMessages(ArrayList<Message> messages) {
            this.messages = messages;
        }
        public Location getLocation() {
            return location;
        }
        public void setLocation(Location location) {
            this.location = location;
        }

    }


