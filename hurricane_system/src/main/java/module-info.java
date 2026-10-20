module com.relief {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires transitive json.simple;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires javafx.web;

    opens com.model to javafx.fxml;
    opens com.model.frontend to javafx.fxml;

    exports com.location;
    exports com.model;
    exports com.model.frontend;
    exports com.model.other;
    exports com.model.requests;
    exports com.model.user;
}
