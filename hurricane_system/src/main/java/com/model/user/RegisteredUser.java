package com.model.user;
import java.util.UUID;
import com.model.requests.*;
public class RegisteredUser {
    private MedicalProfile medicalprofile;

    public RegisteredUser(MedicalProfile medicalprofile) {
        this.medicalprofile = medicalprofile;
    }

    public RequestStatus trackRequest(UUID id){
        // Implement request tracking logic here
    }

    public MedicalProfile getMedicalprofile() {
        return medicalprofile;
    }

    public void setMedicalprofile(MedicalProfile medicalprofile) {
        this.medicalprofile = medicalprofile;
    }
    

}
