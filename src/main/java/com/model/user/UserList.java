package com.model.user;

import java.util.ArrayList;
import com.model.data.DataBase;
import com.model.data.DataLoader;
import com.model.data.DataWriter;

/**
 * Loads, authenticates, and manages registered system users.
 */
public class UserList {

    private ArrayList<User> users;

    /**
     * Loads users from persistent storage and publishes them to the database.
     */
    public UserList() {
        DataBase database = DataBase.getInstance();
        users = DataLoader.loadUsers();
        database.setUsers(users);
    }

    /**
     * Finds a user whose username and password match the supplied credentials.
     *
     * @param username the username to authenticate
     * @param password the password to authenticate
     * @return the matching user, or {@code null} if authentication fails
     */
    public User logIn(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)
                    && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Registers a user if the username is not already in use.
     *
     * @param firstName the user's first name
     * @param lastName the user's last name
     * @param username the desired username
     * @param password the user's password
     * @param email the user's email address
     * @param volunteer whether the user is registering as a volunteer
     * @param phoneNumber the user's phone number
     * @return the newly registered user, or {@code null} if the username exists
     */
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

    /**
     * Persists the current user list.
     */
    public void saveUsers() {
        DataWriter.saveUsers(users);
    }

    /**
     * Returns the in-memory user list.
     *
     * @return the registered users
     */
    public ArrayList<User> getUsers() {
        return users;
    }
}