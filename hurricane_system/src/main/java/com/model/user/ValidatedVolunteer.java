package com.model.user;
import java.util.ArrayList;
import java.util.UUID;
import com.model.requests.AssistanceRequest;
public class ValidatedVolunteer extends User{

    private ArrayList<Skill> skills;

    public ValidatedVolunteer(String firstName, String lastName, String username, String password, String email, String phoneNumber, ArrayList<Skill> skills) {

        super(firstName, lastName, username, password, email, phoneNumber, true);

        this.skills = skills;
    }
    //TODO
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
