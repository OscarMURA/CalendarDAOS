package com.example.tasktodo8d;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AppTaskToDo extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AppTaskToDo.class.getResource("tasktodo.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("TaskToDo");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(windowEvent -> {
            Platform.exit();System.exit(0);});
    }

    public static void main(String[] args) {
        launch();
    }
}