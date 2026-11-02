package com.model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import com.model.HurricaneReliefSystem;
import com.model.data.DataBase;
import java.util.ArrayList;
import com.model.requests.AssistanceRequest;

import java.util.UUID;

import com.model.other.HurricaneEvent;
import com.model.other.HurricaneStatus;
import com.model.other.Location;
import com.model.requests.AssistanceRequest;
import com.model.requests.Message;

public class TestTesting {
    @Test
    public void testMessageToStringWithNullSender() {
       AssistanceRequest request = request();

       assertTrue(request.addMessage(null, "Anonymous message"));
       assertTrue(request.addMessage(user("sender"),null));
       assertTrue(request.addMessage(user("empty"),""));

       assertEquals(3, request.getMessages().size());
       assertNull(request.getMessages().get(1).getBody());
        
         
        
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
