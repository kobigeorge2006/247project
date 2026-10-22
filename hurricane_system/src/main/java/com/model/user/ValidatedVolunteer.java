package com.model.user;
import java.util.ArrayList;
import java.util.UUID;
import com.model.requests.AssistanceRequest;
import com.model.requests.RequestStatus;

public class ValidatedVolunteer extends User {
    private ArrayList<Skill> skills;

    public ValidatedVolunteer(ArrayList<Skill> skills, String firstName, String lastName, String username, String password, String email, String phoneNumber, ArrayList<String> requests) {

        super(firstName, lastName, username, password, email, phoneNumber, requests);

        this.skills = skills;

    }
      
    

    public boolean updateRequestStatus(RequestStatus request) {
        if (request == null)
        {
            return false;
        }
        
        return true;
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
    }
    

}