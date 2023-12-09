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

    @FXML
    public  BorderPane parent;

    @FXML
    public TextField titleWrite;

    @FXML
    public Rectangle colorProgress;

    @FXML
    public DatePicker dateInit;

    @FXML
    protected DatePicker endCalendar;

    @FXML
    protected ComboBox<String> hour;

    @FXML
    protected ComboBox<String> minutes;

    @FXML
    protected ComboBox<String> amPM;
    @FXML
    protected ComboBox<String> categoryOption;
    @FXML
    protected Button addButtom;
    @FXML
    protected TextArea descriptions;

    @FXML
    protected RadioButton periodicOption;

    @FXML
    protected Label endDate;

    @FXML
    protected Label labelPeriod;

    @FXML
    protected Label titleLabel;

    @FXML
    protected Label dateLabel;
    @FXML
    protected ComboBox<String> periodsOptions;

    @FXML
    protected Label periodsLabel;

    @FXML
    protected Label categoryLabel;

    @FXML
    protected Label progressLabel;

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
    @FXML
    protected TableColumn<Task,String> colorTC;
    protected static Task taskShow;
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

    protected void initCategoryOptions(){
        categoryOption.getItems().addAll("WORK", "PERSONAL", "HEALTH", "PROJECTS", "SHOPPING", "REMINDERS");
        categoryOption.setValue("WORK");
    }

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

    protected void initPeriodOptions(){
        periodsOptions.getItems().addAll(
                "SINGLE_DAY", "EVERY_DAY", "EVERY_TWO_DAYS",
                "EVERY_THREE_DAYS", "EVERY_FOUR_DAYS", "EVERY_FIVE_DAYS",
                "EVERY_SIX_DAYS", "WEEKLY", "BIWEEKLY", "MONTHLY",
                "BIMONTHLY", "SEMESTRAL", "QUARTERLY", "ANNUAL"
        );
        periodsOptions.setValue("SINGLE_DAY");
    }

    protected void initComBoxes(){
        initCategoryOptions();
        initHourOptions();
        initPeriodOptions();
    }

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

    protected void initShowTask(){
        ObservableList<Task> tasks = FXCollections.observableArrayList(ControllerTasks.getInstance().Tasks());
        if(taskShow==null && !tasks.isEmpty()){
            Task task=tasks.get(0);
            this.taskShow=task;
        }
    }

    protected void selectionTask(){
        Task task=(Task) tableTask.getSelectionModel().getSelectedItem();
        if(task!=null){
            this.taskShow=task;
        }
    }

    public void selectEdit(){
        System.out.println("Edit");
    }

    public void selectRemove(){
        if(taskShow!=null){
            if(taskShow.getTimePeriod()== TimePeriod.SINGLE_DAY){
            ControllerTasks.getInstance().Tasks().remove(taskShow);
            }else if(ControllerTasks.getInstance().taskSame(taskShow).size()>1){
                boolean removeRepeat= ControllerAlerts.showConfirmation("This task is repeated, do you want to delete all the tasks?");
                if(removeRepeat){
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

    public abstract void selectAdd();
}
