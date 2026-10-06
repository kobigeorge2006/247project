package com.model;

public class VolunteerRequest 
{
    UUID id;
    String firstName;
    String lastName;
    ArrayList<Skill> skills;
    VolunteerRequestStatus status;

    public VolunteerAccountRequest(UUID id, String firstName, String lastName, ArrayList<Skill> skills)
    {
        return true;
    }
}
