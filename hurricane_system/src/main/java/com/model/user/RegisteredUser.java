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

    //TODO
    public RequestStatus trackRequest(AssistanceRequest request){
        // Implement request tracking logic here
        return request.getStatus();
    }

    public MedicalProfile getMedicalprofile() {
        return medicalprofile;
    }

    public void setMedicalprofile(MedicalProfile medicalprofile) {
        this.medicalprofile = medicalprofile;
    }
}