package com.example.tasktodo8d.controllers.Screens;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.Initializable;

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
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
