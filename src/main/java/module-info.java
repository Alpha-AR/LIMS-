module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
    requires java.xml;
    requires org.jetbrains.annotations;

    opens com.example.Project to javafx.fxml;
    exports com.example.Project;
}