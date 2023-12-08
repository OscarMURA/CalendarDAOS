package com.example.tasktodo8d.controllers.Screens;

import javafx.application.Platform;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerInitialShowTask extends BaseScreen implements Initializable {


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initShowTask();
        new Thread(() -> {
            while (true) {
                Platform.runLater(() -> {
                    updateTableTask();
                    changeMode();
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
}
