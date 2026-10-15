package com.model.user;
import java.util.ArrayList;
import java.util.UUID;
import com.model.requests.*;
public class RegisteredUser{
    private MedicalProfile medicalprofile;

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
