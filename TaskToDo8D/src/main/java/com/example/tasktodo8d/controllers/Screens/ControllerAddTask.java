package com.example.tasktodo8d.controllers.Screens;


import com.example.tasktodo8d.controllers.ControllerTasks;

import com.example.tasktodo8d.model.Task;
import com.example.tasktodo8d.model.TaskCategory;
import com.example.tasktodo8d.model.TaskStatus;
import com.example.tasktodo8d.model.TimePeriod;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;


public class ControllerAddTask extends BaseScreen implements Initializable  {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initComBoxes();
        new Thread(() -> {
            while (true) {
                Platform.runLater(() -> {
                    changeMode();
                    activatePeriodic();

                });
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        setLightMode();
    }



    private void activatePeriodic() {
        if (!periodicOption.isSelected()) {
            endDate.setDisable(true);
            endCalendar.setDisable(true);
            labelPeriod.setDisable(true);
            periodsOptions.setDisable(true);
        } else {
            endDate.setDisable(false);
            endCalendar.setDisable(false);
            labelPeriod.setDisable(false);
            periodsOptions.setDisable(false);
        }
    }

    private void updateTableTask(){
        ObservableList<Task> tasks = FXCollections.observableArrayList(ControllerTasks.getInstance().Tasks());
        if(!tasks.isEmpty()){
            System.out.println("Tasks not empty");
        }
        tableTask.setItems(tasks);
        titleTC.setCellValueFactory(new PropertyValueFactory<Task,String>("name"));
        categoryTC.setCellValueFactory(new PropertyValueFactory<Task, TaskCategory>("category"));
        dateTC.setCellValueFactory(new PropertyValueFactory<Task, String>("dateString"));
        statusTC.setCellValueFactory(new PropertyValueFactory<Task, TaskStatus>("status"));
        periodsTC.setCellValueFactory(new PropertyValueFactory<Task, TimePeriod>("timePeriod"));
    }

    public void addTask(){
        String title = titleWrite.getText();
        String description = descriptions.getText();
        String category = categoryOption.getValue();
        Calendar date = Calendar.getInstance();
        GregorianCalendar dateG=new GregorianCalendar();
        int year=dateInit.getValue().getYear();
        int month=dateInit.getValue().getMonthValue();
        int day=dateInit.getValue().getDayOfMonth();
        int hour=Integer.parseInt(this.hour.getValue());
        int minutes=Integer.parseInt(this.minutes.getValue());
        dateG.set(year,month,day,hour,minutes);
        int amPM=this.amPM.getValue().equals("AM")?Calendar.AM:Calendar.PM;
        dateG.set(Calendar.AM_PM,amPM);
        date.setTime(dateG.getTime());
        String period = periodsOptions.getValue();
        ControllerTasks.getInstance().addTask(title,description,category,date,period);
        System.out.println("Task added");
        updateTableTask();
    }

}
