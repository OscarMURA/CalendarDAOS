package com.example.tasktodo8d.controllers.Screens;

import com.example.tasktodo8d.controllers.Mode;
import com.example.tasktodo8d.controllers.Modeable;
import com.example.tasktodo8d.model.Task;
import com.example.tasktodo8d.model.TaskStatus;
import com.example.tasktodo8d.model.TimePeriod;
import com.example.tasktodo8d.controllers.ControllerTaskToDo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public abstract class BaseTableView implements Modeable {
    /**
     * The parent container for the UI elements in the screen.
     */
    @FXML
    public BorderPane parent;
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
    /**
     * Represents a rectangular shape in JavaFX.
     */
    @FXML
    public Rectangle colorProgress;
    /**
     * The progress label used in the base screen.
     */
    @FXML
    protected Label progressLabel;
    /**
     * The label used to display periods.
     */
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
     * The label used to display the category.
     */
    @FXML
    protected Label categoryLabel;
    @FXML
    protected TextArea descriptionText;
    @FXML
    protected Rectangle colorFig;
    protected Mode mode;
    protected boolean isRunning;

     /**
     * Displays the details of a given task on the screen.
     * If the task is not null, it sets the visibility and values of various UI
     * elements based on the task's properties.
     * If the task is null, it hides the UI elements.
     *
     * @param task The task to be displayed on the screen.
     */
    protected void showTask(Task task) {
        if (task != null) {
            colorFig.setVisible(true);
            colorFig.setFill(Color.web(task.getColor()));
            titleLabel.setText(task.getName());
            categoryLabel.setText(task.getCategory().toString());
            dateLabel.setText(task.getDateString());
            progressLabel.setText(task.getStatus().getStatus());
            periodsLabel.setText(task.getTimePeriod().getDescription());
            descriptionText.setText(task.getDescription());
            colorProgress.setVisible(true);
            colorProgress.setFill(Color.web(task.getStatus().getColor()));
        } else {
            colorFig.setVisible(false);
            titleLabel.setText("");
            categoryLabel.setText("");
            dateLabel.setText("");
            progressLabel.setText("");
            periodsLabel.setText("");
            descriptionText.setText("");
            colorProgress.setVisible(false);
        }
    }

    /**
     * Updates the table with the latest tasks.
     * Retrieves the tasks from the ControllerTasks instance and sets them in the
     * table.
     * Configures the cell value factories for each column in the table.
     * Sets a custom cell factory for the color column to display a colored
     * rectangle based on the color value.
     */
    protected void updateTableTask() {
       titleTC.setCellValueFactory(new PropertyValueFactory("name"));
       categoryTC.setCellValueFactory(new PropertyValueFactory("category"));
       dateTC.setCellValueFactory(new PropertyValueFactory("dateString"));
       statusTC.setCellValueFactory(new PropertyValueFactory("status"));
       periodsTC.setCellValueFactory(new PropertyValueFactory("timePeriod"));
       colorTC.setCellValueFactory(new PropertyValueFactory<>("color"));
       columnsCellFactories(); 
    }


    protected void columnsCellFactories() {
        statusTC.setCellFactory(column -> new TableCell<Task, TaskStatus>() {
            @Override
            protected void updateItem(TaskStatus item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(item.getStatus());
                    Circle colorBox = new Circle(10, Color.web(item.getColor()));
                    setGraphic(colorBox);
                }
            }
        });
        colorTC.setCellFactory(column -> new TableCell<Task, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setGraphic(null);
                } else {
                    Circle colorBox = new Circle(10, Color.web(item));
                    setGraphic(colorBox);
                }
            }
        });
        periodsTC.setCellFactory(column -> new TableCell<Task, TimePeriod>() {
            @Override
            protected void updateItem(TimePeriod item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(item.getDescription());
                }
            }
        });
    }
    /**
     * Updates the selected task in the table view.
     */
    protected abstract void selectionTask();
    /**
     * Initializes the combo boxes for category, hour, and period options.
     */
    protected abstract void initComBoxes();
    /**
     * Initializes the show task functionality.
     * Retrieves a list of tasks from the controller and sets the first task as the
     * task to be shown.
     * If no task is currently being shown and the list of tasks is not empty, the
     * first task in the list is selected.
     */
    protected abstract void initShowTask();
    
    @Override
    public void changeMode() {
        Mode isLight = ControllerTaskToDo.isLight();
        if (isLight == Mode.LIGHT && mode !=isLight) {
            mode=isLight;
            setLightMode();
        } else if (isLight == Mode.DARK && mode !=isLight) {
            mode=isLight;
            setDarkMode();
        }
    }


}
