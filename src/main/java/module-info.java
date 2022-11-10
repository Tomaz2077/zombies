module org.example {
    requires javafx.media;
    requires com.googlecode.lanterna;

    opens org.example to javafx.fxml;
    exports org.example;
}