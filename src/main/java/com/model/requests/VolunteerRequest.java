package com.model.requests;
import java.util.ArrayList;
import java.util.UUID;
import com.model.user.*;

/**
 * An application from a person seeking to volunteer.
 */
public class VolunteerRequest 
{
    UUID id;
    String firstName;
    String lastName;
    ArrayList<Skill> skills;
    VolunteerRequestStatus status;

    /**
     * Creates a volunteer request in the pending state.
     *
     * @param id unique request identifier
     * @param firstName applicant's first name
     * @param lastName applicant's last name
     * @param skills skills offered by the applicant
     */
    public VolunteerRequest(UUID id, String firstName, String lastName, ArrayList<Skill> skills)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.skills = skills;
        this.status = VolunteerRequestStatus.PENDING;
    }

    /**
     * Returns the volunteer request identifier.
     *
     * @return the unique request identifier
     */
    public UUID getId() {
        return id;
    }

    /**
     * Updates the volunteer request identifier.
     *
     * @param id the unique request identifier
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Returns the applicant's first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Updates the applicant's first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the applicant's last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Updates the applicant's last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the skills offered by the applicant.
     *
     * @return the applicant's skills
     */
    public ArrayList<Skill> getSkills() {
        return skills;
    }

    /**
     * Updates the skills offered by the applicant.
     *
     * @param skills the applicant's skills
     */
    public void setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
    }

    /**
     * Returns the current volunteer request status.
     *
     * @return the review status
     */
    public VolunteerRequestStatus getStatus() {
        return status;
    }

    /**
     * Updates the volunteer request status.
     *
     * @param status the new review status
     */
    public void setStatus(VolunteerRequestStatus status) {
        this.status = status;
    }
    
}
