module com.example.theislandproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.theislandproject to javafx.fxml;
    exports com.example.theislandproject;
}