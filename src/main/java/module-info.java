module org.example.program10 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens org.example.program10 to javafx.fxml;
    exports org.example.program10;
}