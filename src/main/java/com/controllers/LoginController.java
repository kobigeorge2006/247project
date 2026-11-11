package com.controllers;

import java.io.IOException;

import com.frontend.App;

import javafx.fxml.FXML;

/**
 * Handles navigation actions from the secondary view.
 */
public class LoginController {

    /**
     * Switches the displayed view to the primary screen.
     *
     * @throws IOException if the primary view cannot be loaded
     */
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("home");
    }
}