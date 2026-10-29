package com.model;

import com.model.data.DataWriter;
import com.model.user.User;
import com.model.data.DataLoader;
import com.model.user.*;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.assertSame;

import org.junit.Test;


public class DataWriterTest {

    //Written by Kobi George
    @Test
    public void testSaveUsers(){
        String username = "test-" + UUID.randomUUID();

        User user = new User(
                "Micheal",
                "Jordan",
                "MikeJ",
                "MJGOAT23",
                "mj@gmail.com",
                "232-232-2323",
                false
        );

        ArrayList<User> users = new ArrayList<>();
        users.add(user);

        DataWriter.saveUsers(users);

        User savedUser = null;
        for (User loadedUser : DataLoader.loadUsers()) {
            if (username.equals(loadedUser.getUsername())) {
                savedUser = loadedUser;
                break;
            }
        }

        assertNotNull("Saved user should be present", savedUser);
        assertEquals("Test", savedUser.getFirstName());
        assertEquals("mj@gmail.com", savedUser.getEmail());
    }

    @Test
    public void testSaveUsersWithNullList(){
    ArrayList<String> usernamesBefore = new ArrayList<>();
    for (User user : DataLoader.loadUsers()) {
        usernamesBefore.add(user.getUsername());
    }

    DataWriter.saveUsers(null);

    ArrayList<String> usernamesAfter = new ArrayList<>();
    for (User user : DataLoader.loadUsers()) {
        usernamesAfter.add(user.getUsername());
    }

    assertEquals(usernamesBefore, usernamesAfter);
    }

    @Test
    public void testSaveUsersSkipNull(){
    String username = "test-" + UUID.randomUUID();
    User user = new User(
            "Test",
            "User",
            username,
            "test-password",
            "test@example.com",
            "555-0100",
            false
    );

    ArrayList<User> users = new ArrayList<>();
    users.add(null);
    users.add(user);

    DataWriter.saveUsers(users);

    User savedUser = null;
    for (User loadedUser : DataLoader.loadUsers()) {
        if (username.equals(loadedUser.getUsername())) {
            savedUser = loadedUser;
            break;
        }
    }

    assertNotNull("The valid user should be saved despite the null entry", savedUser);
    } 
}       
        
    

    
