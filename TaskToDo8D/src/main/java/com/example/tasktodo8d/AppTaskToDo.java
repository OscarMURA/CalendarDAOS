package com.example.tasktodo8d;


import java.io.IOException;
import com.example.tasktodo8d.controllers.ControllerTasks;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AppTaskToDo extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ControllerTasks controllerTasks = ControllerTasks.getInstance();
        FXMLLoader fxmlLoader = new FXMLLoader(AppTaskToDo.class.getResource("tasktodo.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.getIcons().add(new Image(AppTaskToDo.class.getResourceAsStream("/icon/icon.png")));
        stage.setTitle("TaskToDo");
        stage.setScene(scene);
        stage.show();
        controllerTasks.loadData();
        stage.setOnCloseRequest(windowEvent -> {
            controllerTasks.saveData();
            Platform.exit();System.exit(0);}
        );
    }

    public static void main(String[] args) {
        launch();
    }
}