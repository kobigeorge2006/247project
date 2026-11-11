package com.controllers;

import java.io.IOException;

import com.frontend.App;

import javafx.fxml.FXML;


/**
 * Handles navigation actions from the primary view.
 */
public class HomeController {

    /**
     * Switches the displayed view to the secondary screen.
     *
     * @throws IOException if the secondary view cannot be loaded
     */
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("login");
    }
}
