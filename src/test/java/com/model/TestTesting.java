package com.model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

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
}
