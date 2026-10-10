
package com.model.frontend;

import com.model.HurricaneReliefSystem;

//Runs scenarios for the Hurricane Relief System
 
public class HurricaneUI {

    private HurricaneReliefSystem system;

    public HurricaneUI() {
        system = new HurricaneReliefSystem();
    }

    public void run() {
        scenario1();
        scenario2();
        scenario3();
    }

    //Scenario 1: A user logs into the system

    public void scenario1() {
        System.out.println("\nScenario 1: Login");

        if (system.logIn("testuser", "12345")) {
            System.out.println("Login successful.");
        } else {
            System.out.println("Login failed.");
        }
    }

    //Scenario 2: A new user creates an account

    public void scenario2() {
        System.out.println("\nScenario 2: Signup");

        if (system.signUp("Amy", "Smith", "asmith", "12345",
                "amy@email.com", "8035551234")) {
            System.out.println("Account created successfully.");
        } else {
            System.out.println("Signup failed.");
        }
    }

    //Scenario 3: The current user logs out 
    
    public void scenario3() {
        System.out.println("\nScenario 3: Logout");

        if (system.isLoggedIn()) {
            system.logOut();
            System.out.println("User logged out.");
        } else {
            System.out.println("No user is logged in.");
        }
    }

    public static void main(String[] args) {
        HurricaneUI ui = new HurricaneUI();
        ui.run();
    }
}
