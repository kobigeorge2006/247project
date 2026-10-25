package com.model.user;
import java.util.Date;

/**
 * Stores medical details associated with a user.
 */
public class MedicalProfile {
    private String bloodtype;
    private Date dateofbirth;
    private String medicalHistory;

    /**
     * Creates a medical profile.
     *
     * @param bloodtype the person's blood type
     * @param dateofbirth the person's date of birth
     * @param medicalHistory a description of relevant medical history
     */
    public MedicalProfile(String bloodtype, Date dateofbirth, String medicalHistory) {
        this.bloodtype = bloodtype;
        this.dateofbirth = dateofbirth;
        this.medicalHistory = medicalHistory;
    }

    /**
     * Returns the blood type.
     *
     * @return the blood type
     */
    public String getBloodtype() {
        return bloodtype;
    }

    /**
     * Sets the blood type.
     *
     * @param bloodtype the blood type
     */
    public void setBloodtype(String bloodtype) {
        this.bloodtype = bloodtype;
    }

    /**
     * Returns the date of birth.
     *
     * @return the date of birth
     */
    public Date getDateofbirth() {
        return dateofbirth;
    }

    /**
     * Sets the date of birth.
     *
     * @param dateofbirth the date of birth
     */
    public void setDateofbirth(Date dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    /**
     * Returns the medical history.
     *
     * @return the medical history
     */
    public String getMedicalHistory() {
        return medicalHistory;
    }

    /**
     * Sets the medical history.
     *
     * @param medicalHistory the medical history
     */
    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }
    
    
}
