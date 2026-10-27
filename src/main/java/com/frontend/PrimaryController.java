package com.frontend;

import java.io.IOException;

import javafx.fxml.FXML;

/**
 * Handles navigation actions from the primary view.
 */
public class PrimaryController {

    /**
     * Switches the displayed view to the secondary screen.
     *
     * @throws IOException if the secondary view cannot be loaded
     */
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
