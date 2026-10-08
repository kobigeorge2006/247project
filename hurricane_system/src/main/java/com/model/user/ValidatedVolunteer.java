package com.model.user;
import java.util.ArrayList;
import java.util.UUID;
import com.model.requests.AssistanceRequest;
public class ValidatedVolunteer {
    private ArrayList<Skill> skills;

    public ValidatedVolunteer(ArrayList<Skill> skills) {
        this.skills = skills;
    }

    public boolean updateRequestStatus() {
        // Implement request status update logic here
        
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
    }
    
}
