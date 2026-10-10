package com.model.frontend;

import java.util.UUID;
import java.util.ArrayList;
import java.util.HashMap;
import com.model.user.*;
import com.model.data.*;
import com.model.other.*;
import java.util.Scanner;
import com.model.HurricaneReliefSystem;

public class HurricaneUI {
    
public void scenario1() {
    // Create a new instance of the HurricaneReliefSystem
    HurricaneReliefSystem system = new HurricaneReliefSystem();

    // Sign up a new user
    boolean signUpSuccess = system.signUp("John", "Doe", "johndoe", "password123", "john.doe@example.com", "123-456-7890");
    if(signUpSuccess) {
        System.out.println("User signed up successfully.");
    } else {
        System.out.println("Sign up failed. Username may already be taken.");
    }
}
public void scenario2() {
    HurricaneReliefSystem system = new HurricaneReliefSystem();

    boolean loginSuccess = system.logIn("johndoe", "password123");
    if(loginSuccess) {
        System.out.println("User logged in successfully.");
    } else {
        System.out.println("Login failed. Please check your username and password.");
    }
}

public void scenario3(){
    HurricaneReliefSystem system = new HurricaneReliefSystem();
    system.logOut();
}
public static void main(String[] args) {
    HurricaneUI ui = new HurricaneUI();
    ui.scenario1();
    ui.scenario2();
    ui.scenario3();
}
}

