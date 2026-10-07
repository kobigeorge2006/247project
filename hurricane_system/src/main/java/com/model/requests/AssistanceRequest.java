package com.model.requests;
import java.util.ArrayList;
import java.util.UUID;
import com.model.user.*;
import org.json.simple.JSONObject;

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

    public AssistanceRequest(UUID id, String contactInfo, String address, AidCategory aidCategory, Severity severity, RequestStatus status, ArrayList<Message> messages)
    {
        this.id = id;
        this.contactInfo = contactInfo;
        this.address = address;
        this.AidCategory = AidCategory;
        this.status = status;
        this.messages = messages;
    }
    public AssistanceRequest(JSONObject jsonAssistanceRequest) {
        this.id = UUID.fromString((String) jsonAssistanceRequest.get("id"));
        this.contactInfo = (String) jsonAssistanceRequest.get("contactInfo");
        this.address = (String) jsonAssistanceRequest.get("address");
        this.AidCategory = (AidCategory) jsonAssistanceRequest.get("aidCategory");
        this.severity = (Severity) jsonAssistanceRequest.get("severity");
        this.status = (RequestStatus) jsonAssistanceRequest.get("status");
        this.messages = (ArrayList<Message>) jsonAssistanceRequest.get("messages");
    }
        public boolean AssistanceRequest(UUID id, String contactInfo, String address, AidCategory aidCategory, ArrayList<Message> messages)

        public void updateStatus(Status staus)
        {
            this.status = status;
        }

        public boolean addMessage(User user, String message)
        {
           return true;
        }

    }


