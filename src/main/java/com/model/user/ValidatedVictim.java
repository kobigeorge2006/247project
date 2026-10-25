package com.model.user;

import java.util.ArrayList;
import java.util.UUID;
import com.model.requests.AssistanceRequest;

/**
 * Represents a victim whose identity has been validated for assistance.
 */
public class ValidatedVictim {
    private MedicalProfile medicalprofile;
    private ArrayList<AssistanceRequest> assistanceRequests;
    private String address;
    private String emergencyContact;

    /**
     * Creates a validated victim with a medical profile and contact details.
     *
     * @param medicalprofile the victim's medical profile
     * @param address the victim's address
     * @param emergencyContact the victim's emergency contact information
     */
    public ValidatedVictim(MedicalProfile medicalprofile, String address, String emergencyContact) {
        this.medicalprofile = medicalprofile;
        this.address = address;
        this.emergencyContact = emergencyContact;
        this.assistanceRequests = new ArrayList<>();
    }

    /**
     * Submits an assistance request.
     *
     * @param request the request to submit
     * @return {@code true} (request submission is not yet implemented)
     */
    public boolean submitRequest(AssistanceRequest request) {
        // Implement request submission logic here
        return true;
    }

    /**
     * Cancels an assistance request.
     *
     * @param requestId the identifier of the request to cancel
     * @return {@code true} (request cancellation is not yet implemented)
     */
    public boolean cancelRequest(UUID requestId) {
        // Implement request cancellation logic here
        return true;
    }

    /**
     * Returns this victim's assistance requests.
     *
     * @return the assistance requests
     */
    public ArrayList<AssistanceRequest> viewRequests() {
        // Implement request viewing logic here
        return assistanceRequests;
    }

    /**
     * Updates this victim's medical profile.
     *
     * @param profile the profile to apply
     */
    public void updateMedicalProfile(MedicalProfile profile){
            // Implement medical profile update logic here
    }

    /**
     * Returns this victim's medical profile.
     *
     * @return the medical profile
     */
    public MedicalProfile getMedicalprofile() {
        return medicalprofile;
    }

    /**
     * Sets this victim's medical profile.
     *
     * @param medicalprofile the medical profile
     */
    public void setMedicalprofile(MedicalProfile medicalprofile) {
        this.medicalprofile = medicalprofile;
    }

    /**
     * Returns this victim's assistance requests.
     *
     * @return the assistance requests
     */
    public ArrayList<AssistanceRequest> getAssistanceRequests() {
        return assistanceRequests;
    }

    /**
     * Replaces this victim's assistance requests.
     *
     * @param assistanceRequests the replacement request list
     */
    public void setAssistanceRequests(ArrayList<AssistanceRequest> assistanceRequests) {
        this.assistanceRequests = assistanceRequests;
    }

    /**
     * Returns this victim's address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets this victim's address.
     *
     * @param address the address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns the victim's emergency contact information.
     *
     * @return the emergency contact
     */
    public String getEmergencyContact() {
        return emergencyContact;
    }

    /**
     * Sets the victim's emergency contact information.
     *
     * @param emergencyContact the emergency contact
     */
    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }
    
    
}
