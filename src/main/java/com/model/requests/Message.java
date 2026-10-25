package com.model.requests;
import java.time.LocalDateTime;
import com.model.user.User;

/**
 * A timestamped message sent by a user.
 */
public class Message {
    /** Time at which the message was created. */
    public LocalDateTime timestamp;
    private String body;
    private User sender;

    /**
     * Creates a message and records the current time as its timestamp.
     *
     * @param sender the user sending the message
     * @param body the message text
     */
    public Message(User sender, String body) {
        this.sender = sender;
        this.body = body;
        this.timestamp = LocalDateTime.now();
    }

    /**
     * Returns the message text.
     *
     * @return the message body
     */
    public String getBody() {
        return body;
    }

    /**
     * Returns the user who sent the message.
     *
     * @return the sender
     */
    public User getSender() {
        return sender;
    }

    /**
     * Returns the sender; retained as an alternate accessor for callers.
     *
     * @return the user who sent the message
     */
    public User getSenderUser() {
        return sender;
    }

    /**
     * Replaces the message text.
     *
     * @param body the new message text
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * Replaces the message sender.
     *
     * @param sender the user who sent the message
     */
    public void setSender(User sender) {
        this.sender = sender;
    }

    /**
     * Returns a string containing the timestamp, message body, and sender name.
     *
     * @return the message description
     */
    @Override 
    public String toString() {
        return "Message{" +
                "timestamp=" + timestamp +
                ", body='" + body + '\'' +
                ", sender=" + sender.getUsername() +
                '}';
    }

    /**
     * Returns the message timestamp.
     *
     * @return the time at which the message was created
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Updates the message timestamp.
     *
     * @param timestamp the message creation time
     */
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
