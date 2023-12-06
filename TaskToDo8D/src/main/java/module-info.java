module com.example.tasktodo8d {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    opens com.example.tasktodo8d to javafx.fxml;
    exports com.example.tasktodo8d;
    exports com.example.tasktodo8d.controllers;
    opens com.example.tasktodo8d.controllers to javafx.fxml;
}