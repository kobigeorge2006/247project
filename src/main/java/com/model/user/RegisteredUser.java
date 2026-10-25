package com.model.user;
import java.util.ArrayList;
import java.util.UUID;
import com.model.requests.RequestStatus;
import com.model.requests.AssistanceRequest;
/**
 * Stores information about a registered user.
 */
public class RegisteredUser {

    private MedicalProfile medicalprofile;

    /**
     * Creates a registered user with a medical profile.
     *
     * @param medicalprofile the user's medical profile
     */
    public RegisteredUser(MedicalProfile medicalprofile) {
        this.medicalprofile = medicalprofile;
  
    }

    /**
     * Returns the current status of an assistance request.
     *
     * @param request the request to track
     * @return the request's current status
     */
    public RequestStatus trackRequest(AssistanceRequest request){
        return request.getStatus();
    }

    /**
     * Returns this user's medical profile.
     *
     * @return the medical profile
     */
    public MedicalProfile getMedicalprofile() {
        return medicalprofile;
    }

    /**
     * Replaces this user's medical profile.
     *
     * @param medicalprofile the new medical profile
     */
    public void setMedicalprofile(MedicalProfile medicalprofile) {
        this.medicalprofile = medicalprofile;
    }
}