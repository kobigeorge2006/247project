package com.model.user;

public class MedicalProfile {
    private String bloodtype;
    private Date dateofbirth;
    private String medicalHistory;

    public MedicalProfile(String bloodtype, Date dateofbirth, String medicalHistory) {
        this.bloodtype = bloodtype;
        this.dateofbirth = dateofbirth;
        this.medicalHistory = medicalHistory;
    }
    
}
