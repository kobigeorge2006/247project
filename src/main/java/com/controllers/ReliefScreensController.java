package com.controllers;

import com.frontend.App;
import com.model.HurricaneReliefSystem;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ReliefScreensController {

    private final HurricaneReliefSystem system = new HurricaneReliefSystem();

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField username;

    @FXML
    private TextField email;

    @FXML
    private PasswordField signupPassword;

    @FXML
    private Label signupStatus;

    @FXML
    private void navigate(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        String destination = (String) source.getUserData();

        switch (destination) {
            case "home" -> App.setRoot("home");
            case "shelters" -> App.setRoot("NearByShelters");
            case "stormupdates", "storms" -> App.setRoot("stormupdates");
            case "browse-requests", "my-requests", "requests" ->
                    App.setRoot("requests");
            case "volunteerapplication", "volunteer" ->
                    App.setRoot("volunteerapplication");
            case "welcometotheteam" -> App.setRoot("welcometotheteam");
            case "requestdetails" -> App.setRoot("requestdetails");
            case "login" -> App.setRoot("login");
            default -> throw new IllegalArgumentException(
                    "Unknown relief screen: " + destination);
        }
    }

    @FXML
    private void submitApplication() throws IOException {
        App.setRoot("applicationreceived");
    }

    @FXML
    private void createAccount() throws IOException {
        String first = firstName.getText().trim();
        String last = lastName.getText().trim();
        String user = username.getText().trim();
        String emailAddress = email.getText().trim();
        String password = signupPassword.getText();

        if (first.isEmpty() || last.isEmpty() || user.isEmpty()
                || emailAddress.isEmpty() || password.isBlank()) {
            showSignupStatus("Complete all fields before creating an account.",
                    false);
            return;
        }

        if (!emailAddress.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
            showSignupStatus("Enter a valid email address.", false);
            return;
        }

        try {
            boolean created = system.signUp(
                    first, last, user, password, emailAddress, false, "");
            if (!created) {
                showSignupStatus("That username is already in use.", false);
                return;
            }

            App.setRoot("home");
        } catch (IllegalStateException exception) {
            System.err.println("Unable to save the new account: "
                    + exception.getMessage());
            showSignupStatus(
                    "Unable to save your account. Please try again.", false);
        }
    }

    private void showSignupStatus(String message, boolean success) {
        signupStatus.setText(message);
        signupStatus.setStyle(success
                ? "-fx-text-fill: #18794E;"
                : "-fx-text-fill: #B42318;");
    }
}
