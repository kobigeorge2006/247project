package com.model.user;
import java.util.UUID;
import com.model.requests.*;

public class RegisteredUser extends User{
    private MedicalProfile medicalprofile;

    public RegisteredUser(MedicalProfile medicalprofile) {
        this.medicalprofile = medicalprofile;
    }

    public UUID trackRequest(UUID id){
        // Implement request tracking logic here
        return id;
    }

    public MedicalProfile getMedicalprofile() {
        return medicalprofile;
    }

    public void setMedicalprofile(MedicalProfile medicalprofile) {
        this.medicalprofile = medicalprofile;
    }
    

}
