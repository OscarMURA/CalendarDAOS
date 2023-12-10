package com.example.tasktodo8d.controllers.Screens;

import com.example.tasktodo8d.model.Task;
import com.example.tasktodo8d.model.TaskStatus;
import com.example.tasktodo8d.model.TimePeriod;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.shape.Rectangle;

public class ControllerConsultsTasks {

    @FXML
    protected TableView tableTask;
    @FXML
    protected TableColumn<Task, String> titleTC;
    @FXML
    protected TableColumn<Task, String> dateTC;
    @FXML
    protected TableColumn<Task, ?> categoryTC;
    @FXML
    protected TableColumn<Task, TimePeriod> periodsTC;
    @FXML
    protected TableColumn<Task, TaskStatus> statusTC;
    @FXML
    protected TableColumn<Task, String> colorTC;
    @FXML
    public Rectangle colorProgress;
    @FXML
    protected Label progressLabel;
    @FXML
    protected Label periodsLabel;
    /**
     * The label used to display the title on the screen.
     */
    @FXML
    protected Label titleLabel;

    /**
     * The label used to display the date.
     */
    @FXML
    protected Label dateLabel;
    /**
     * The ComboBox that displays the available periods options.
     */
    @FXML
    protected ComboBox<String> periodsOptions;
    @FXML
    protected Label categoryLabel;





}
