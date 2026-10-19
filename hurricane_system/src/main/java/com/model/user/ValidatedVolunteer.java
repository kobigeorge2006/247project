package com.model.user;
import java.util.ArrayList;
import java.util.UUID;
import com.model.requests.AssistanceRequest;
import com.model.requests.RequestStatus;
public class ValidatedVolunteer extends User{

    private ArrayList<Skill> skills;

    public ValidatedVolunteer(String firstName, String lastName, String username, String password, String email, String phoneNumber, ArrayList<Skill> skills) {

        super(firstName, lastName, username, password, email, phoneNumber, true);

        this.skills = skills;
    }
    //TODOs
    public void updateRequestStatus(AssistanceRequest request, RequestStatus status) {
        // Implement request status update logic here
        request.setStatus(status);
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
    }
    
}
