package com.model.user;
import java.util.Date;

public class MedicalProfile {
    private String bloodtype;
    private Date dateofbirth;
    private String medicalHistory;

    public MedicalProfile(String bloodtype, Date dateofbirth, String medicalHistory) {
        this.bloodtype = bloodtype;
        this.dateofbirth = dateofbirth;
        this.medicalHistory = medicalHistory;
    }

    public String getBloodtype() {
        return bloodtype;
    }

    public void setBloodtype(String bloodtype) {
        this.bloodtype = bloodtype;
    }

    public Date getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(Date dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }
    
    
}
