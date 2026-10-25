package com.model.user;

import java.util.ArrayList;
import com.model.data.DataBase;
import com.model.data.DataLoader;
import com.model.data.DataWriter;

//Manages registered users in the Hurricane Relief System

public class UserList {

    private ArrayList<User> users;

    //Loads registered users from the database.
   
    public UserList() {
        DataBase database = DataBase.getInstance();
        users = DataLoader.loadUsers();
        database.setUsers(users);
    }

    //Finds a user with the correct username and password
  
    public User logIn(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)
                    && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    //Registers a new user if the username is available

    public User signUp(String firstName, String lastName,
                       String username, String password,
                       String email, Boolean volunteer, String phoneNumber) {

        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return null;
            }
        }

        User newUser = new User(firstName, lastName, username, password, email, phoneNumber, volunteer, new ArrayList<String>());

        users.add(newUser);
        DataWriter.saveUsers(users);
        return newUser;
    }

    //Saves registered users to the JSON file
    
    public void saveUsers() {
        DataWriter.saveUsers(users);
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}