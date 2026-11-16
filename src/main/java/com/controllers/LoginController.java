package com.controllers;

import com.frontend.App;
import com.model.HurricaneReliefSystem;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    private final HurricaneReliefSystem system = new HurricaneReliefSystem();

    @FXML
    private TextField txt_username;

    @FXML
    private PasswordField txt_password;

    @FXML
    private Label statusLabel;

    @FXML
    private void handleLogin() throws IOException {
        boolean success = system.logIn(
            txt_username.getText(),
            txt_password.getText()
        );

        if (success) {
            App.setRoot("home");
        } else {
            statusLabel.setText("Incorrect username or password.");
        }
    }
}