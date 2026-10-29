package com.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class UsersTest {
        @Test
    public void testLoginValid(){
        HurricaneReliefSystem System = new HurricaneReliefSystem();
        System.logIn("asmith", "12345");
        String firstName = System.getCurrentUser().getFirstName();
        assertEquals("Amy", firstName);
    }

    @Test
    public void testLoginInvalid(){
        HurricaneReliefSystem System = new HurricaneReliefSystem();
        boolean isLogInFalse = System.logIn("Kobig20", "ILoveCSCE247");
        assertFalse(isLogInFalse);
    }
    public void testLogInWrongPassword(){
        HurricaneReliefSystem System = new HurricaneReliefSystem();
        boolean isLogInFalse = System.logIn("asmith", "ILoveSWE");
        assertFalse(isLogInFalse);
    }

    @Test
    public void testSignUpValid(){
        HurricaneReliefSystem System = new HurricaneReliefSystem();
        System.signUp("John", "Doe", "JDoe", "JohnnyD03", "JohnD@gmail.com", false, "123-405-4444");
        System.logOut();
        boolean loggedIn = System.logIn("JDoe", "JohnnyD03");

        assertTrue(loggedIn);
    }
}
