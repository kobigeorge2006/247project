package com.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField txt_username;

    @FXML
    private PasswordField txt_password;

    @FXML
    private Label statusLabel;

    @FXML
    private void login() {
        if (txt_username.getText().isBlank() || txt_password.getText().isBlank()) {
            statusLabel.setText("Enter both username and password.");
            return;
        }

        statusLabel.setText("Authentication is not configured yet.");
    }
}