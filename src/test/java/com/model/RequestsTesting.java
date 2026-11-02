package com.model.requests;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import org.json.simple.JSONObject;
import org.junit.Test;

import com.location.Address;
import com.model.requests.AssistanceRequest;
import com.model.requests.AssistanceRequest;
import com.model.requests.AssistanceRequest;
import com.model.user.Skill;
import com.model.user.User;

import static org.junit.Assert.*;

@SuppressWarnings("unchecked")
public class RequestsTesting {
    private static final UUID REQUEST_ID = UUID.fromString(
            "11111111-1111-1111-1111-111111111111");

    private Address address() {
        return new Address("123 Main St", "Charleston", "SC", "29401");
    }

    private User user(String username) {
        return new User("Test", "User", username, "password",
                username + "@example.com", "555-0100", false);
    }

    private AssistanceRequest request() {
        return new AssistanceRequest(REQUEST_ID, "555-0100", address(),
                AidCategory.MEDICAL, Severity.HIGH, RequestStatus.SUBMITTED,
                "Need assistance", address(), false);
    }

    private JSONObject jsonRequest(UUID id) {
        JSONObject json = new JSONObject();
        json.put("id", id.toString());
        json.put("contactInfo", "555-0100");
        json.put("address", address());
        json.put("aidCategory", AidCategory.MEDICAL.name());
        json.put("severity", Severity.HIGH.name());
        json.put("status", RequestStatus.SUBMITTED.name());
        json.put("message", "Need assistance");
        json.put("location", address());
        json.put("hasPet", Boolean.TRUE);
        json.put("assignedVolunteer", "responder1");
        return json;
    }

    // AssistanceRequest tests
    @Test
    public void testAssistanceRequestStandardConstructor() {
        UUID id = UUID.randomUUID();
        Address requesterAddress = address();
        Address location = address();
        AssistanceRequest request = new AssistanceRequest(id, "contact",
                requesterAddress, AidCategory.PHYSICAL, Severity.CRITICAL,
                RequestStatus.ASSIGNED, "Please help", location, true);

        assertEquals(id, request.getId());
        assertEquals("contact", request.getContactInfo());
        assertSame(requesterAddress, request.getAddress());
        assertEquals(AidCategory.PHYSICAL, request.getAidCategory());
        assertEquals(Severity.CRITICAL, request.getSeverity());
        assertEquals(RequestStatus.ASSIGNED, request.getStatus());
        assertEquals("Please help", request.getMessage());
        assertSame(location, request.getLocation());
        assertEquals(Boolean.TRUE, request.getHasPet());
        assertNotNull(request.getMessages());
        assertTrue(request.getMessages().isEmpty());
        assertNull(request.getAssignedVolunteerUsername());
    }

    @Test
    public void testAssistanceRequestJsonConstructorAllFieldsPopulated() {
        UUID id = UUID.randomUUID();
        JSONObject json = jsonRequest(id);

        AssistanceRequest request = new AssistanceRequest(json);

        assertEquals(id, request.getId());
        assertEquals("555-0100", request.getContactInfo());
        assertSame(json.get("address"), request.getAddress());
        assertEquals(AidCategory.MEDICAL, request.getAidCategory());
        assertEquals(Severity.HIGH, request.getSeverity());
        assertEquals(RequestStatus.SUBMITTED, request.getStatus());
        assertEquals("Need assistance", request.getMessage());
        assertSame(json.get("location"), request.getLocation());
        assertEquals(Boolean.TRUE, request.getHasPet());
        assertEquals("responder1", request.getAssignedVolunteerUsername());
        assertNotNull(request.getMessages());
        assertTrue(request.getMessages().isEmpty());
    }

    @Test
    public void testAssistanceRequestJsonConstructorNullEnums() {
        JSONObject json = jsonRequest(UUID.randomUUID());
        json.put("aidCategory", null);
        json.put("severity", null);
        json.put("status", null);

        AssistanceRequest request = new AssistanceRequest(json);

        assertNull(request.getAidCategory());
        assertNull(request.getSeverity());
        assertNull(request.getStatus());
    }

    @Test
    public void testAssistanceRequestJsonConstructorInvalidId() {
        JSONObject malformedId = jsonRequest(UUID.randomUUID());
        malformedId.put("id", "not-a-valid-uuid");
        assertThrows(IllegalArgumentException.class,
                () -> new AssistanceRequest(malformedId));

        JSONObject nullId = jsonRequest(UUID.randomUUID());
        nullId.put("id", null);
        assertThrows(NullPointerException.class,
                () -> new AssistanceRequest(nullId));
    }

    @Test
    public void testAssistanceRequestJsonConstructorInvalidEnumString() {
        JSONObject invalidCategory = jsonRequest(UUID.randomUUID());
        invalidCategory.put("aidCategory", "UNKNOWN");
        assertThrows(IllegalArgumentException.class,
                () -> new AssistanceRequest(invalidCategory));

        JSONObject invalidSeverity = jsonRequest(UUID.randomUUID());
        invalidSeverity.put("severity", "UNKNOWN");
        assertThrows(IllegalArgumentException.class,
                () -> new AssistanceRequest(invalidSeverity));

        JSONObject invalidStatus = jsonRequest(UUID.randomUUID());
        invalidStatus.put("status", "UNKNOWN");
        assertThrows(IllegalArgumentException.class,
                () -> new AssistanceRequest(invalidStatus));
    }

    @Test
    public void testAddMessageSingle() {
        AssistanceRequest request = request();
        User sender = user("rosa");

        assertTrue(request.addMessage(sender, "I need a ride."));
        assertEquals(1, request.getMessages().size());
        assertSame(sender, request.getMessages().get(0).getSender());
        assertEquals("I need a ride.", request.getMessages().get(0).getBody());
    }

    @Test
    public void testAddMessageMultiplePreservesOrder() {
        AssistanceRequest request = request();
        User firstSender = user("first");
        User secondSender = user("second");

        request.addMessage(firstSender, "First message");
        request.addMessage(secondSender, "Second message");

        assertEquals(2, request.getMessages().size());
        assertEquals("First message", request.getMessages().get(0).getBody());
        assertSame(firstSender, request.getMessages().get(0).getSender());
        assertEquals("Second message", request.getMessages().get(1).getBody());
        assertSame(secondSender, request.getMessages().get(1).getSender());
        assertFalse(request.getMessages().get(0).getTimestamp()
                .isAfter(request.getMessages().get(1).getTimestamp()));
    }

    @Test
    public void testAddMessageWithNullUserOrText() {
        AssistanceRequest request = request();

        assertTrue(request.addMessage(null, "Anonymous message"));
        assertTrue(request.addMessage(user("sender"), null));
        assertTrue(request.addMessage(user("empty"), ""));

        assertEquals(3, request.getMessages().size());
        assertNull(request.getMessages().get(0).getSender());
        assertEquals("Anonymous message", request.getMessages().get(0).getBody());
        assertNull(request.getMessages().get(1).getBody());
        assertEquals("", request.getMessages().get(2).getBody());
    }

    @Test
    public void testSetMessagesWithValidList() {
        AssistanceRequest request = request();
        ArrayList<Message> messages = new ArrayList<>();
        Message message = new Message(user("sender"), "Saved message");
        messages.add(message);

        request.setMessages(messages);

        assertSame(messages, request.getMessages());
        assertSame(message, request.getMessages().get(0));
    }

    @Test
    public void testSetMessagesWithNullCreatesEmptyList() {
        AssistanceRequest request = request();

        request.setMessages(null);

        assertNotNull(request.getMessages());
        assertTrue(request.getMessages().isEmpty());
    }

    @Test
    public void testAddMessageAfterSetMessagesNull() {
        AssistanceRequest request = request();
        User sender = user("sender");

        request.setMessages(null);

        assertTrue(request.addMessage(sender, "Still works"));
        assertEquals(1, request.getMessages().size());
        assertSame(sender, request.getMessages().get(0).getSender());
    }

    @Test
    public void testUpdateStatusTransitions() {
        AssistanceRequest request = request();

        for (RequestStatus status : RequestStatus.values()) {
            request.updateStatus(status);
            assertEquals(status, request.getStatus());
            request.setStatus(status);
            assertEquals(status, request.getStatus());
        }
    }

    @Test
    public void testSetHasPetThreeStates() {
        AssistanceRequest request = request();

        request.setHasPet(true);
        assertEquals(Boolean.TRUE, request.getHasPet());
        request.setHasPet(false);
        assertEquals(Boolean.FALSE, request.getHasPet());
        request.setHasPet(null);
        assertNull(request.getHasPet());
    }

    @Test
    public void testSetAndGetAssignedVolunteerUsername() {
        AssistanceRequest request = request();

        request.setAssignedVolunteerUsername("volunteer1");
        assertEquals("volunteer1", request.getAssignedVolunteerUsername());
        request.setAssignedVolunteerUsername(null);
        assertNull(request.getAssignedVolunteerUsername());
    }

    @Test
    public void testAssistanceRequestSettersAndGetters() {
        AssistanceRequest request = request();
        UUID updatedId = UUID.fromString(
                "11111111-1111-1111-1111-111111111125");
        Address newAddress = new Address("45 Oak Ave", "Columbia", "SC", "29201");
        Address newLocation = new Address("89 Pine St", "Columbia", "SC", "29201");

        request.setId(updatedId);
        request.setContactInfo("new contact");
        request.setAddress(newAddress);
        request.setAidCategory(AidCategory.TRANSPORTATION);
        request.setSeverity(Severity.LOW);
        request.setStatus(RequestStatus.COMPLETED);
        request.setMessage("Updated request");
        request.setLocation(newLocation);

        assertEquals(updatedId, request.getId());
        assertEquals("new contact", request.getContactInfo());
        assertSame(newAddress, request.getAddress());
        assertEquals(AidCategory.TRANSPORTATION, request.getAidCategory());
        assertEquals(Severity.LOW, request.getSeverity());
        assertEquals(RequestStatus.COMPLETED, request.getStatus());
        assertEquals("Updated request", request.getMessage());
        assertSame(newLocation, request.getLocation());
    }

    // VolunteerRequest tests
    @Test
    public void testVolunteerRequestConstructorDefaultsToPending() {
        UUID id = UUID.randomUUID();
        ArrayList<Skill> skills = new ArrayList<>(
                Arrays.asList(Skill.MEDICAL, Skill.GENERAL));
        VolunteerRequest request = new VolunteerRequest(
                id, "Rosa", "Carter", skills);

        assertEquals(id, request.getId());
        assertEquals("Rosa", request.getFirstName());
        assertEquals("Carter", request.getLastName());
        assertSame(skills, request.getSkills());
        assertEquals(VolunteerRequestStatus.PENDING, request.getStatus());
    }

    @Test
    public void testVolunteerRequestSetStatusApprovedAndRejected() {
        VolunteerRequest request = new VolunteerRequest(
                UUID.randomUUID(), "Rosa", "Carter", new ArrayList<>());

        request.setStatus(VolunteerRequestStatus.APPROVED);
        assertEquals(VolunteerRequestStatus.APPROVED, request.getStatus());
        request.setStatus(VolunteerRequestStatus.REJECTED);
        assertEquals(VolunteerRequestStatus.REJECTED, request.getStatus());
    }

    @Test
    public void testVolunteerRequestWithNullOrEmptySkills() {
        VolunteerRequest nullSkills = new VolunteerRequest(
                UUID.randomUUID(), "Rosa", "Carter", null);
        assertNull(nullSkills.getSkills());

        VolunteerRequest request = new VolunteerRequest(
                UUID.randomUUID(), "Rosa", "Carter", new ArrayList<>());
        assertNotNull(request.getSkills());
        assertTrue(request.getSkills().isEmpty());

        request.setSkills(null);
        assertNull(request.getSkills());
        request.setSkills(new ArrayList<>());
        assertNotNull(request.getSkills());
        assertTrue(request.getSkills().isEmpty());
    }

    @Test
    public void testVolunteerRequestSettersAndGetters() {
        VolunteerRequest request = new VolunteerRequest(
                UUID.fromString("22222222-2222-2222-2222-222222222224"),
                "Rosa", "Carter", new ArrayList<>());
        UUID updatedId = UUID.fromString(
                "22222222-2222-2222-2222-222222222225");
        ArrayList<Skill> skills = new ArrayList<>(
                Arrays.asList(Skill.ELECTRICAL, Skill.TRANSPORTATION));

        request.setId(updatedId);
        request.setFirstName("Updated");
        request.setLastName("Applicant");
        request.setSkills(skills);

        assertEquals(updatedId, request.getId());
        assertEquals("Updated", request.getFirstName());
        assertEquals("Applicant", request.getLastName());
        assertSame(skills, request.getSkills());
    }

    // Message tests
    @Test
    public void testMessageConstructorSetsFieldsAndTimestamp() {
        User sender = user("sender");
        LocalDateTime beforeCreation = LocalDateTime.now();

        Message message = new Message(sender, "Hello");
        LocalDateTime afterCreation = LocalDateTime.now();

        assertSame(sender, message.getSender());
        assertEquals("Hello", message.getBody());
        assertNotNull(message.getTimestamp());
        assertFalse(message.getTimestamp().isBefore(beforeCreation));
        assertFalse(message.getTimestamp().isAfter(afterCreation));
    }

    @Test
    public void testMessageGetSenderAndGetSenderUserMatch() {
        User sender = user("sender");
        Message message = new Message(sender, "Hello");

        assertSame(sender, message.getSender());
        assertSame(message.getSender(), message.getSenderUser());
    }

    @Test
    public void testMessageToStringFormat() {
        Message message = new Message(user("rosa"), "Need supplies");

        String text = message.toString();

        assertTrue(text.contains(message.getTimestamp().toString()));
        assertTrue(text.contains("Need supplies"));
        assertTrue(text.contains("rosa"));
    }

    @Test
    public void testMessageToStringWithNullSender() {
        Message message = new Message(null, "Anonymous");

        assertThrows(NullPointerException.class, message::toString);
    }

    @Test
    public void testMessageSetters() {
        Message message = new Message(user("first"), "Original");
        User replacementSender = user("replacement");
        LocalDateTime replacementTimestamp =
                LocalDateTime.of(2026, 1, 2, 3, 4, 5);

        message.setBody("Updated");
        message.setSender(replacementSender);
        message.setTimestamp(replacementTimestamp);

        assertEquals("Updated", message.getBody());
        assertSame(replacementSender, message.getSender());
        assertEquals(replacementTimestamp, message.getTimestamp());
    }

    // EmergencyAlert and enum tests
    @Test
    public void testEmergencyAlertDefaultConstructorAndFields() {
        EmergencyAlert alert = new EmergencyAlert();

        assertNotNull(alert);
        assertNull(alert.id);
        assertNull(alert.message);
        assertNull(alert.targetArea);

        UUID alertId = UUID.randomUUID();
        alert.id = alertId;
        alert.message = "Evacuate the area";
        alert.targetArea = "Coastal zone";

        assertEquals(alertId, alert.id);
        assertEquals("Evacuate the area", alert.message);
        assertEquals("Coastal zone", alert.targetArea);
    }

    @Test
    public void testRequestEnumsValues() {
        assertArrayEquals(new AidCategory[] {
                AidCategory.MEDICAL,
                AidCategory.PHYSICAL,
                AidCategory.TRANSPORTATION
        }, AidCategory.values());
        assertArrayEquals(new Severity[] {
                Severity.LOW,
                Severity.HIGH,
                Severity.CRITICAL
        }, Severity.values());
        assertArrayEquals(new RequestStatus[] {
                RequestStatus.SUBMITTED,
                RequestStatus.ASSIGNED,
                RequestStatus.IN_PROGRESS,
                RequestStatus.COMPLETED
        }, RequestStatus.values());
        assertArrayEquals(new VolunteerRequestStatus[] {
                VolunteerRequestStatus.PENDING,
                VolunteerRequestStatus.APPROVED,
                VolunteerRequestStatus.REJECTED
        }, VolunteerRequestStatus.values());
    }
}
