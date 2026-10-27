package com.model;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import com.model.HurricaneReliefSystem;
import com.model.data.DataBase;
import java.util.ArrayList;
import com.model.requests.AssistanceRequest;

public class TestTesting {
    @Test
    public void testTrue() {
        assertTrue(true);
    }
    
    @Test
    public void testLoginValid(){
        HurricaneReliefSystem System = new HurricaneReliefSystem();
        System.logIn("asmith", "12345");
        String firstName = System.getCurrentUser().getFirstName();
        assertEquals("Amy", firstName);
    }

    @Test
    public void testSignUpValid(){
        HurricaneReliefSystem System = new HurricaneReliefSystem();
        System.signUp("John", "Doe", "JDoe", "JohnnyD03", "JohnD@gmail.com", false, "123-405-4444");
        System.logOut();
        boolean loggedIn = System.logIn("JDoe", "JohnnyD03");

        assertTrue(loggedIn);
    }

    @Test
    public void testRequest(){
        HurricaneReliefSystem System = new HurricaneReliefSystem();
        java.util.UUID requestId = java.util.UUID.fromString("c87d3e12-6b94-4f0a-a528-91de73b406fc");       
        System.createAssistanceRequest(java.util.UUID.fromString("c87d3e12-6b94-4f0a-a528-91de73b406fc"), "8035551234", new com.location.Address("123 Main St", "Columbia", "SC", "29201"), com.model.requests.AidCategory.MEDICAL, com.model.requests.Severity.HIGH, "Need medical assistance", new com.location.Address("123 Main St", "Columbia", "SC", "29201"), false);
        DataBase db = DataBase.getInstance();
        ArrayList<AssistanceRequest> requests = db.getAssistanceRequests();
        boolean doesRequestExist = false;
        for (AssistanceRequest request : requests) {
            if (request.getId().equals(requestId)) {
                doesRequestExist = true;
                break;
            }
        }
        assertTrue(doesRequestExist);
    }
}
