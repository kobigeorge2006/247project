package com.model.requests;
import java.time.LocalDateTime;
import com.model.user.User;
public class Message {
    public LocalDateTime timestamp;
    private String body;
    private User sender;

    public Message(User sender, String body) {
        this.sender = sender;
        this.body = body;
        this.timestamp = LocalDateTime.now();
    }

    public String getBody() {
        return body;
    }

    public User getSender() {
        return sender;
    }

    public User getSenderUser() {
        return sender;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    @Override 
    public String toString() {
        return "Message{" +
                "timestamp=" + timestamp +
                ", body='" + body + '\'' +
                ", sender=" + sender.getUsername() +
                '}';
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
