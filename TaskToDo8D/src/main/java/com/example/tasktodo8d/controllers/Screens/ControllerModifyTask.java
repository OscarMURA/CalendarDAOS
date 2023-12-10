package com.example.tasktodo8d.controllers.Screens;

import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.ResourceBundle;

import com.example.tasktodo8d.model.Task;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;

public class ControllerModifyTask extends BaseScreen implements Initializable {

    /**
     * The ComboBox used for selecting the status of a task.
     */
    @FXML
    private ComboBox<String> statusCombox;
    /**
     * Represents a task that is being modified.
     */
    private Task taskModify;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        isRunning = true;
        initComBoxes();
        initShowTask();
        new Thread(() -> {
            while (isRunning) {
                Platform.runLater(() -> {
                    updateTaskModify();
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

    @Override
    protected void initComBoxes() {
        super.initComBoxes();
        initStatusCombox();
    }

    /**
     * The function initializes a ComboBox with a list of status options.
     */
    private void initStatusCombox() {
        statusCombox.getItems().addAll("TO_DO", "IN_PROGRESS", "CANCELED", "COMPLETED", "NO_COMPLETED");
    }

    /**
     * The function updates the task modification fields with the values of the
     * currently displayed task.
     */
    private void updateTaskModify() {
        if (taskModify != taskShow) {
            taskModify = taskShow;
            titleWrite.setText(taskModify.getName());
            color.setValue(Color.web(taskModify.getColor()));
            descriptions.setText(taskModify.getDescription());
            categoryOption.setValue(taskModify.getCategory() + "");
            statusCombox.setValue(taskModify.getStatus() + "");
            updateDate();
        }
    }

    /**
     * The function "updateDate" updates the date and time values in a user
     * interface based on a given Calendar object.
     */
    private void updateDate() {
        Calendar date = Calendar.getInstance();
        date = taskModify.getDate();
        LocalDate localDate = LocalDate.of(
                date.get(Calendar.YEAR),
                date.get(Calendar.MONTH) + 1,
                date.get(Calendar.DAY_OF_MONTH));
        dateInit.setValue(localDate);
        int hour = date.get(Calendar.HOUR_OF_DAY);
        hour = (hour > 12) ? hour - 12 : hour;
        hour = (hour == 0) ? 12 : hour;
        int minutes = date.get(Calendar.MINUTE);
        String amPM = (date.get(Calendar.AM_PM) == Calendar.AM) ? "AM" : "PM";
        this.hour.setValue(hour + "");
        this.minutes.setValue(minutes + "");
        this.amPM.setValue(amPM);
    }

}
