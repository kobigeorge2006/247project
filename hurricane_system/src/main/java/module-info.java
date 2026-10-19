module com.relief {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;

    opens com.model to avafx.fxml;
    exports com.model;
}
