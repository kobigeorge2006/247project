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
}
