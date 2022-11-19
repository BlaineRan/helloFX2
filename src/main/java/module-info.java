module org.ran {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens org.ran to javafx.fxml;
    exports org.ran;
}
