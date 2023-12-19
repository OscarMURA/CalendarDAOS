package com.example.tasktodo8d.controllers.Screens;
import java.io.IOException;
import java.util.List;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.time.LocalDate;

import com.example.tasktodo8d.controllers.ControllerAlerts;
import com.example.tasktodo8d.controllers.ControllerTaskToDo;
import com.example.tasktodo8d.controllers.ControllerTasks;
import com.example.tasktodo8d.model.Task;
import com.example.tasktodo8d.model.TimePeriod;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public abstract class BaseScreen extends BaseTableView  {

    /**
     * The text field for entering the title.
     */
    @FXML
    public TextField titleWrite;

    /**
     * The DatePicker class represents a control that allows the user to select a
     * date from a calendar.
     */
    @FXML
    public DatePicker dateInit;

    /**
     * The DatePicker class represents a control that allows the user to select a
     * date from a calendar.
     */
    @FXML
    protected DatePicker endCalendar;

    /**
     * The ComboBox representing the hour.
     */
    @FXML
    protected ComboBox<String> hour;

    /**
     * The ComboBox representing the minutes.
     */
    @FXML
    protected ComboBox<String> minutes;

    /**
     * The amPM ComboBox represents the selection of AM or PM for time.
     */
    @FXML
    protected ComboBox<String> amPM;
    /**
     * The ComboBox used for selecting a category.
     */
    @FXML
    protected ComboBox<String> categoryOption;
    /**
     * The add button used in the base screen.
     */
    @FXML
    protected Button addButtom;
    /**
     * The TextArea component is used to display and edit multi-line text.
     */
    @FXML
    protected TextArea descriptions;

    /**
     * Represents a radio button control.
     */
    @FXML
    protected RadioButton periodicOption;

    /**
     * The Label class represents a text component in JavaFX that can be used to
     * display text.
     */
    @FXML
    protected Label endDate;
    /**
     * The label used to display the period.
     */
    @FXML
    protected Label labelPeriod;
    /**
     * The ComboBox that displays the available periods options.
     */
    @FXML
    protected ComboBox<String> periodsOptions;
    protected static Task taskShow;
    @FXML
    ImageView plusImg;
    @FXML
    ImageView removeImg;
    @FXML
    ImageView editImg;
    @FXML
    protected Button removeBtn;
    @FXML
    protected Button editBtn;
    @FXML
    protected Button plusBtn;
    @FXML
    protected ImageView titleError;
    @FXML
    protected ImageView dateInitError;
    @FXML
    protected ImageView endDateError;
    @FXML
    protected ImageView hourError;
    @FXML
    protected ColorPicker color;
    @FXML
    protected ImageView backImg;
    @FXML
    protected ImageView errorPeriodImg;
    @FXML
    protected ImageView colorError;

    
    @Override
    public void setLightMode() {
        parent.getStylesheets().clear();
        parent.getStylesheets().add(getClass().getResource("/styles/lightMode.css").toExternalForm());
        Image plus = new Image(getClass().getResourceAsStream("/icon/plusL.png"));
        Image remove = new Image(getClass().getResourceAsStream("/icon/removeL.png"));
        Image edit = new Image(getClass().getResourceAsStream("/icon/editL.png"));
        Image back = new Image(getClass().getResourceAsStream("/icon/backL.png"));
        backImg.setImage(back);
        plusImg.setImage(plus);
        removeImg.setImage(remove);
        editImg.setImage(edit);
    }

    @Override
    public void setDarkMode() {
        parent.getStylesheets().clear();
        parent.getStylesheets().add(getClass().getResource("/styles/darkMode.css").toExternalForm());
        Image plus = new Image(getClass().getResourceAsStream("/icon/plusD.png"));
        Image remove = new Image(getClass().getResourceAsStream("/icon/removeD.png"));
        Image edit = new Image(getClass().getResourceAsStream("/icon/editD.png"));
        Image back = new Image(getClass().getResourceAsStream("/icon/backD.png"));
        backImg.setImage(back);
        plusImg.setImage(plus);
        removeImg.setImage(remove);
        editImg.setImage(edit);
    }

    /**
     * Initializes the category options for the screen.
     * Adds the predefined categories to the categoryOption ComboBox and sets the
     * default value to "WORK".
     */
    protected void initCategoryOptions() {
        categoryOption.getItems().addAll("WORK", "PERSONAL", "HEALTH", "PROJECTS", "SHOPPING", "REMINDERS");
        categoryOption.setValue("WORK");
    }

    /**
     * Initializes the hour, minutes, and amPM options for the screen.
     */
    protected void initHourOptions() {
        hour.getItems().addAll("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12");
        minutes.getItems().addAll("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12",
                "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27",
                "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42",
                "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57",
                "58", "59");
        amPM.getItems().addAll("AM", "PM");
        hour.setValue("11");
        minutes.setValue("59");
        amPM.setValue("PM");
    }
    @Override
    protected void initComBoxes() {
        initCategoryOptions();
        initHourOptions();
    }
    @Override
    protected void initShowTask() {
        ObservableList<Task> tasks = FXCollections.observableArrayList(ControllerTasks.getInstance().Tasks());
        if (taskShow == null && !tasks.isEmpty()) {
            Task task = tasks.get(0);
            BaseScreen.taskShow = task;
        }
    }
    @Override
    protected void selectionTask() {
        Task task = (Task) tableTask.getSelectionModel().getSelectedItem();
        if (task != null) {
            BaseScreen.taskShow = task;
        }
    }
    public void selectEdit() {
        try {
            isRunning = false;
            ControllerTaskToDo.loadScreen("modifyTask.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Removes the selected task from the task list.
     * If the task is a single-day task, it is removed directly.
     * If the task is a repeated task, a confirmation dialog is shown to ask if all
     * the tasks should be deleted.
     * If confirmed, all the repeated tasks are removed. Otherwise, only the
     * selected task is removed.
     * After removing the task, the table and the task display are updated
     * accordingly.
     */
    public void selectRemove() {
        if (taskShow != null) {
            if (taskShow.getTimePeriod() == TimePeriod.SINGLE_DAY) {
                ControllerTasks.getInstance().Tasks().remove(taskShow);
            } else if (ControllerTasks.getInstance().taskSame(taskShow).size() >= 1) {
                boolean removeRepeat = ControllerAlerts
                        .showConfirmation("This task is repeated, do you want to delete all the tasks?");
                if (removeRepeat && ControllerTasks.getInstance().taskSame(taskShow).size() > 1) {
                    ControllerTasks.getInstance().removeTaskRepeat(taskShow);
                } else {
                    ControllerTasks.getInstance().removeTask(taskShow);
                }
            }
            ControllerAlerts.showInformation("Action successfully");
            taskShow = null;
            updateTableTask();
            initShowTask();
        }
    }

    @Override
    protected void updateTableTask() {
        List<Task> tasksControl = ControllerTasks.getInstance().Tasks();
        String searchTask = ControllerTaskToDo.getSearchTask();
        if (searchTask != null && !ControllerTasks.getInstance().searchTask(searchTask).isEmpty()) {
            tasksControl = ControllerTasks.getInstance().searchTask(searchTask);
            taskShow = tasksControl.get(0);
            selectionTask();
        }
        ObservableList<Task> tasks = FXCollections.observableArrayList(tasksControl);
        tableTask.setItems(tasks);
        super.updateTableTask();
    }

    /**
     * The function "selectBack" sets the variable "isRunning" to false and loads
     * the "showTask.fxml" screen.
     */
    public void selectBack() {
        isRunning = false;
        try {
            ControllerTaskToDo.loadScreen("showTask.fxml");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Selects the "Add" option, which loads the "addTask.fxml" screen.
     * 
     * @throws RuntimeException if an IOException occurs while loading the screen.
     */
    public void selectAdd() {
        try {
            isRunning = false;
            ControllerTaskToDo.loadScreen("addTask.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Checks if the given date is valid.
     * 
     * @param date The date to be validated.
     * @return true if the date is valid, false otherwise.
     */
    protected boolean dateValid(Calendar date) {
        Calendar now = Calendar.getInstance();
        boolean allow = true;
        dateInitError.setVisible(false);

        if (date.before(now)) {
            dateInitError.setVisible(true);
            ControllerAlerts.errorContext("Please validate the date, it is before the current date");
            allow = false;
            System.out.println(allow);
        }
        return allow;
    }

    /**
     * The function returns a Calendar object representing a specific date and time.
     * 
     * @return The method is returning a Calendar object.
     */
    protected Calendar getCalendar() {
        Calendar date = Calendar.getInstance();
        GregorianCalendar dateG = new GregorianCalendar();
        int year = dateInit.getValue().getYear();
        int month = dateInit.getValue().getMonthValue() - 1;
        int day = dateInit.getValue().getDayOfMonth();
        int hour = Integer.parseInt(this.hour.getValue());
        int minutes = Integer.parseInt(this.minutes.getValue());
        dateG.set(year, month, day, hour, minutes);
        System.out.println(dateG.getTime());
        int amPM = this.amPM.getValue().equals("AM") ? Calendar.AM : Calendar.PM;
        dateG.set(Calendar.AM_PM, amPM);
        date.setTime(dateG.getTime());
        return date;
    }

    /**
     * Converts a Color object to its corresponding hexadecimal string
     * representation.
     * 
     * @param color the Color object to convert
     * @return the hexadecimal string representation of the color
     */
    protected String colorToString(Color color) {
        int red = (int) (color.getRed() * 255);
        int green = (int) (color.getGreen() * 255);
        int blue = (int) (color.getBlue() * 255);

        return String.format("#%02X%02X%02X", red, green, blue);
    }

    /**
     * The function updates the value of a date picker widget with the date provided
     * in the Calendar object.
     * 
     * @param date The parameter "date" is a Calendar object representing a specific
     *             date.
     */
    protected void updateDateInitPicker(Calendar date) {
        LocalDate localDate = LocalDate.of(
                date.get(Calendar.YEAR),
                date.get(Calendar.MONTH) + 1,
                date.get(Calendar.DAY_OF_MONTH));
        dateInit.setValue(localDate);
    }
}
