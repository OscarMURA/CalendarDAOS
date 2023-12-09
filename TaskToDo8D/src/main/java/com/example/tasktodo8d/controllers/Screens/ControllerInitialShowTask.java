package com.example.tasktodo8d.controllers.Screens;

import com.example.tasktodo8d.controllers.ControllerTaskToDo;
import com.example.tasktodo8d.controllers.Mode;

import javafx.application.Platform;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerInitialShowTask extends BaseScreen implements Initializable {


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        isRunning=true;
        initShowTask();
        new Thread(() -> {
            while (isRunning) {
                Platform.runLater(() -> {
                    changeMode();
                    updateTableTask();
                    selectionTask();
                    showTask(taskShow);
                });
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * Selects the "Add" option, which loads the "addTask.fxml" screen.
     * @throws RuntimeException if an IOException occurs while loading the screen.
     */
    @Override
    public void selectAdd() {
        try {
            isRunning=false;
            ControllerTaskToDo.loadScreen("addTask.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
