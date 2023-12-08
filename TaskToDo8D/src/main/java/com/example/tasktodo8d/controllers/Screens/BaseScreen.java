package com.example.tasktodo8d.controllers.Screens;

import com.example.tasktodo8d.controllers.ControllerTaskToDo;
import com.example.tasktodo8d.controllers.Mode;
import com.example.tasktodo8d.controllers.Modeable;
import com.example.tasktodo8d.model.Task;
import com.example.tasktodo8d.model.TaskCategory;
import com.example.tasktodo8d.model.TaskStatus;
import com.example.tasktodo8d.model.TimePeriod;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

public class BaseScreen implements Modeable {


    @FXML
    public BorderPane parent;

    @FXML
    public TextField titleWrite;

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
    protected TableColumn<Task, TaskCategory> categoryTC;


    @FXML
    protected TableColumn<Task, TimePeriod> periodsTC;

    @FXML
    protected TableColumn<Task, TaskStatus> statusTC;

    @FXML
    protected TableView tableTask;


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
    }

    @Override
    public void setDarkMode(){
        parent.getStylesheets().clear();
        parent.getStylesheets().add(getClass().getResource("/styles/darkMode.css").toExternalForm());
    }

    protected void initCategoryOptions(){
        categoryOption.getItems().addAll("WORK", "PERSONAL", "HEALTH", "PROJECTS", "SHOPPING", "REMINDERS");
    }

    protected void initHourOptions(){
        hour.getItems().addAll("01","02","03","04","05","06","07","08","09","10","11","12");
        minutes.getItems().addAll("00","01","02","03","04","05","06","07","08","09","10","11","12",
                "13","14","15","16","17","18","19","20","21","22","23","24","25","26","27",
                "28","29","30","31","32","33","34","35","36","37","38","39","40","41","42",
                "43","44","45","46","47","48","49","50","51","52","53","54","55","56","57",
                "58","59");
        amPM.getItems().addAll("AM","PM");
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
}
