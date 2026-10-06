package com.model;

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
        this.aidCategory = aidCategory;
        this.status = status;
        this.messages = messages;

        public boolean AssistanceRequest(UUID id, String contactInfo, String address, AidCategory aidCategory, ArrayList<Message> messages)

        public void updateStatus(Status staus)
        {
            this.status = status;
        }

        public void addMessage(User user, String message)
        {
           return true;
        }

    }

}
