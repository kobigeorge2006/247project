package com.model.requests;
import java.util.ArrayList;
import java.util.UUID;
import com.model.user.*;
public class VolunteerRequest 
{
    UUID id;
    String firstName;
    String lastName;
    ArrayList<Skill> skills;
    VolunteerRequestStatus status;

    public VolunteerRequest(UUID id, String firstName, String lastName, ArrayList<Skill> skills)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.skills = skills;
        this.status = VolunteerRequestStatus.PENDING;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
    }

    public VolunteerRequestStatus getStatus() {
        return status;
    }

    public void setStatus(VolunteerRequestStatus status) {
        this.status = status;
    }
    
}
