package com.model.user;

import java.util.ArrayList;
import java.util.UUID;
import com.model.requests.AssistanceRequest;

public class ValidatedVictim {
    private MedicalProfile medicalprofile;
    private ArrayList<AssistanceRequest> assistanceRequests;
    private String address;
    private String emergencyContact;

    public ValidatedVictim(MedicalProfile medicalprofile, String address, String emergencyContact) {
        this.medicalprofile = medicalprofile;
        this.address = address;
        this.emergencyContact = emergencyContact;
        this.assistanceRequests = new ArrayList<>();
    }

    public boolean submitRequest(AssistanceRequest request) {
        // Implement request submission logic here
    }

    public boolean cancelRequest(UUID requestId) {
        // Implement request cancellation logic here
    }

    public ArrayList<AssistanceRequest> viewRequests() {
        // Implement request viewing logic here
    }

    public void updateMedicalProfile(MedicalProfile profile){
            // Implement medical profile update logic here
    }

    public MedicalProfile getMedicalprofile() {
        return medicalprofile;
    }

    public void setMedicalprofile(MedicalProfile medicalprofile) {
        this.medicalprofile = medicalprofile;
    }

    public ArrayList<AssistanceRequest> getAssistanceRequests() {
        return assistanceRequests;
    }

    public void setAssistanceRequests(ArrayList<AssistanceRequest> assistanceRequests) {
        this.assistanceRequests = assistanceRequests;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }
    
    
}
