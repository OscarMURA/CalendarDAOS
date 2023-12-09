package com.example.tasktodo8d.controllers.Screens;

import com.example.tasktodo8d.controllers.*;
import com.example.tasktodo8d.model.Task;
import com.example.tasktodo8d.model.TimePeriod;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class BaseScreen implements Modeable {

    /**
     * The parent container for the UI elements in the screen.
     */
    @FXML
    public  BorderPane parent;

    /**
     * The text field for entering the title.
     */
    @FXML
    public TextField titleWrite;

    /**
     * Represents a rectangular shape in JavaFX.
     */
    @FXML
    public Rectangle colorProgress;

    /**
     * The DatePicker class represents a control that allows the user to select a date from a calendar.
     */
    @FXML
    public DatePicker dateInit;

    /**
     * The DatePicker class represents a control that allows the user to select a date from a calendar.
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
     * The Label class represents a text component in JavaFX that can be used to display text.
     */
    @FXML
    protected Label endDate;

    /**
     * The label used to display the period.
     */
    @FXML
    protected Label labelPeriod;

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

    /**
        * The label used to display periods.
        */
    @FXML
    protected Label periodsLabel;

    /**
     * The label used to display the category.
     */
    @FXML
    protected Label categoryLabel;

    /**
        * The progress label used in the base screen.
        */
    @FXML
    protected Label progressLabel;

    /**
     * The table column for the title of a task.
     */
    @FXML
    protected TableColumn<Task, String> titleTC;

    @FXML
    protected TableColumn<Task, String> dateTC;
    @FXML
    protected TableColumn<Task, ?> categoryTC;
    @FXML
    protected TableColumn<Task, ?> periodsTC;
    @FXML
    protected TableColumn<Task, ?> statusTC;
    @FXML
    protected TableColumn<Task,String> colorTC;
    
    protected static Task taskShow;
    @FXML
    protected TableView tableTask;

    @FXML
    protected TextArea descriptionText;
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
    protected Rectangle colorFig;
    
    @Override
    public void changeMode(){
        Mode isLight = ControllerTaskToDo.isLight();
        if(isLight==Mode.LIGHT){
            setLightMode();
        }else{
            setDarkMode();
        }
    }

    @Override
    public void setLightMode(){
        parent.getStylesheets().clear();
        parent.getStylesheets().add(getClass().getResource("/styles/lightMode.css").toExternalForm());
        Image plus = new Image(getClass().getResourceAsStream("/icon/plusL.png"));
        Image remove = new Image(getClass().getResourceAsStream("/icon/removeL.png"));
        Image edit = new Image(getClass().getResourceAsStream("/icon/editL.png"));
        plusImg.setImage(plus);
        removeImg.setImage(remove);
        editImg.setImage(edit);
    }

    @Override
    public void setDarkMode(){
        parent.getStylesheets().clear();
        parent.getStylesheets().add(getClass().getResource("/styles/darkMode.css").toExternalForm());
        Image plus = new Image(getClass().getResourceAsStream("/icon/plusD.png"));
        Image remove = new Image(getClass().getResourceAsStream("/icon/removeD.png"));
        Image edit = new Image(getClass().getResourceAsStream("/icon/editD.png"));
        plusImg.setImage(plus);
        removeImg.setImage(remove);
        editImg.setImage(edit);
    }

    /**
     * Initializes the category options for the screen.
     * Adds the predefined categories to the categoryOption ComboBox and sets the default value to "WORK".
     */
    protected void initCategoryOptions(){
        categoryOption.getItems().addAll("WORK", "PERSONAL", "HEALTH", "PROJECTS", "SHOPPING", "REMINDERS");
        categoryOption.setValue("WORK");
    }

    /**
     * Initializes the hour, minutes, and amPM options for the screen.
     */
    protected void initHourOptions(){
        hour.getItems().addAll("01","02","03","04","05","06","07","08","09","10","11","12");
        minutes.getItems().addAll("00","01","02","03","04","05","06","07","08","09","10","11","12",
                "13","14","15","16","17","18","19","20","21","22","23","24","25","26","27",
                "28","29","30","31","32","33","34","35","36","37","38","39","40","41","42",
                "43","44","45","46","47","48","49","50","51","52","53","54","55","56","57",
                "58","59");
        amPM.getItems().addAll("AM","PM");
        amPM.setValue("AM");
    }

    /**
     * Initializes the period options for the screen.
     * Adds the available period options to the periodOptions ComboBox
     * and sets the default value to "SINGLE_DAY".
     */
    protected void initPeriodOptions(){
        periodsOptions.getItems().addAll(
                "SINGLE_DAY", "EVERY_DAY", "EVERY_TWO_DAYS",
                "EVERY_THREE_DAYS", "EVERY_FOUR_DAYS", "EVERY_FIVE_DAYS",
                "EVERY_SIX_DAYS", "WEEKLY", "BIWEEKLY", "MONTHLY",
                "BIMONTHLY", "SEMESTRAL", "QUARTERLY", "ANNUAL"
        );
        periodsOptions.setValue("SINGLE_DAY");
    }

    /**
     * Initializes the combo boxes for category, hour, and period options.
     */
    protected void initComBoxes(){
        initCategoryOptions();
        initHourOptions();
        initPeriodOptions();
    }

    /**
     * Displays the details of a given task on the screen.
     * If the task is not null, it sets the visibility and values of various UI elements based on the task's properties.
     * If the task is null, it hides the UI elements.
     *
     * @param task The task to be displayed on the screen.
     */
    protected void showTask(Task task){
        if(task!=null){
            colorFig.setVisible(true);
            colorFig.setFill(Color.web(task.getColor()));
            titleLabel.setText(task.getName());
            categoryLabel.setText(task.getCategory().toString());
            dateLabel.setText(task.getDateString());
            progressLabel.setText(task.getStatus().toString());
            periodsLabel.setText(task.getTimePeriod().toString());
            descriptionText.setText(task.getDescription());
            colorProgress.setVisible(true);
            colorProgress.setFill(Color.web(task.getStatus().getColor()));
        }else{
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
     * Initializes the show task functionality.
     * Retrieves a list of tasks from the controller and sets the first task as the task to be shown.
     * If no task is currently being shown and the list of tasks is not empty, the first task in the list is selected.
     */
    protected void initShowTask(){
        ObservableList<Task> tasks = FXCollections.observableArrayList(ControllerTasks.getInstance().Tasks());
        if(taskShow==null && !tasks.isEmpty()){
            Task task=tasks.get(0);
            this.taskShow=task;
        }
    }

    /**
     * Updates the selected task in the table view.
     */
    protected void selectionTask(){
        Task task=(Task) tableTask.getSelectionModel().getSelectedItem();
        if(task!=null){
            this.taskShow=task;
        }
    }

    public void selectEdit(){
        System.out.println("Edit");
    }

    /**
     * Removes the selected task from the task list.
     * If the task is a single-day task, it is removed directly.
     * If the task is a repeated task, a confirmation dialog is shown to ask if all the tasks should be deleted.
     * If confirmed, all the repeated tasks are removed. Otherwise, only the selected task is removed.
     * After removing the task, the table and the task display are updated accordingly.
     */
    public void selectRemove(){
        if(taskShow!=null){
            if(taskShow.getTimePeriod()== TimePeriod.SINGLE_DAY){
            ControllerTasks.getInstance().Tasks().remove(taskShow);
            }else if(ControllerTasks.getInstance().taskSame(taskShow).size()>=1){
                boolean removeRepeat= ControllerAlerts.showConfirmation("This task is repeated, do you want to delete all the tasks?");
                if(removeRepeat && ControllerTasks.getInstance().taskSame(taskShow).size()>1){
                    ControllerTasks.getInstance().removeTaskRepeat(taskShow);
                }else{
                    ControllerTasks.getInstance().Tasks().remove(taskShow);
                }
            }
            taskShow=null;
            updateTableTask();
            initShowTask();
        }
    }

    /**
     * Updates the table with the latest tasks.
     * Retrieves the tasks from the ControllerTasks instance and sets them in the table.
     * Configures the cell value factories for each column in the table.
     * Sets a custom cell factory for the color column to display a colored rectangle based on the color value.
     */
    protected void updateTableTask(){
        ObservableList<Task> tasks = FXCollections.observableArrayList(ControllerTasks.getInstance().Tasks());
        tableTask.setItems(tasks);
        FilteredList<Task> filteredData = new FilteredList<>(tasks, p -> true);
        titleTC.setCellValueFactory(new PropertyValueFactory("name"));
        categoryTC.setCellValueFactory(new PropertyValueFactory("category"));
        dateTC.setCellValueFactory(new PropertyValueFactory("dateString"));
        statusTC.setCellValueFactory(new PropertyValueFactory("status"));
        periodsTC.setCellValueFactory(new PropertyValueFactory("timePeriod"));
        colorTC.setCellValueFactory(new PropertyValueFactory<>("color"));
        colorTC.setCellFactory(column ->
            new TableCell<Task, String>() {
                /**
                 * Updates the item in the cell with the specified color.
                 * If the item is null or empty, the cell will be empty.
                 * Otherwise, a colored rectangle will be displayed in the cell.
                 * @param item  the color value to be displayed in the cell
                 * @param empty a boolean indicating whether the cell is empty or not
                 */
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setGraphic(null);
                    } else {
                        Rectangle colorBox = new Rectangle(20, 20, Color.web(item));
                        setGraphic(colorBox);
                    }
                }
            }
         );
    }

    /**
     * Selects the "Add" option.
     */
    public abstract void selectAdd();
}
