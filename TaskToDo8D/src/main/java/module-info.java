module com.example.tasktodo8d {
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires transitive javafx.graphics;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires com.jfoenix;
    requires com.google.gson;
    opens com.example.tasktodo8d to javafx.fxml;
    exports com.example.tasktodo8d;
    exports com.example.tasktodo8d.controllers;
    opens com.example.tasktodo8d.controllers to javafx.fxml, com.google.gson;
    exports com.example.tasktodo8d.controllers.Screens;
    opens com.example.tasktodo8d.controllers.Screens to javafx.fxml;
    exports com.example.tasktodo8d.model;
    opens com.example.tasktodo8d.model to javafx.fxml, com.google.gson;
}