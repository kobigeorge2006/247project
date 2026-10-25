package com.model.user;

import java.util.ArrayList;
import java.util.UUID;
import com.model.requests.AssistanceRequest;
import com.model.requests.RequestStatus;

/**
 * Represents a volunteer whose identity and qualifications have been
 * validated.
 */
public class ValidatedVolunteer extends User{

    private ArrayList<Skill> skills;

    /**
     * Creates a validated volunteer with the supplied skills.
     *
     * @param firstName the volunteer's first name
     * @param lastName the volunteer's last name
     * @param username the volunteer's username
     * @param password the volunteer's password
     * @param email the volunteer's email address
     * @param phoneNumber the volunteer's phone number
     * @param skills the volunteer's skills
     */
    public ValidatedVolunteer(String firstName, String lastName, String username, String password, String email, String phoneNumber, ArrayList<Skill> skills) {

        super(firstName, lastName, username, password, email, phoneNumber, true);

        this.skills = skills;
    }

    /**
     * Updates the status of an assistance request.
     *
     * @param request the request to update
     * @param status the new request status
     */
    public void updateRequestStatus(AssistanceRequest request, RequestStatus status) {
        // Implement request status update logic here
        request.setStatus(status);
    }

    /**
     * Returns this volunteer's skills.
     *
     * @return the volunteer's skills
     */
    public ArrayList<Skill> getSkills() {
        return skills;
    }

    /**
     * Replaces this volunteer's skills.
     *
     * @param skills the replacement skills
     */
    public void setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
    }
}