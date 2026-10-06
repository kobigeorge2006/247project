package com.model.user;

import java.util.ArrayList;

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
    
}
