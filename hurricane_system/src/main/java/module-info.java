module com.relief {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;

<<<<<<< HEAD

    opens com.model to avafx.fxml;
    exports com.model;
}
=======
    opens com.model.frontend to javafx.fxml;
    exports com.model.frontend;
    exports com.model;
}
>>>>>>> 3b578d5e223b5b112ba2e059c26a7ddaaeee880d
