package com.example.tasktodo8d.controllers.Screens;

import com.example.tasktodo8d.controllers.ControllerAlerts;
import com.example.tasktodo8d.controllers.ControllerTaskToDo;
import com.example.tasktodo8d.controllers.ControllerTasks;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;


public class ControllerAddTask extends BaseScreen implements Initializable  {


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        changeMode();
        updateTableTask();
        initComBoxes();
        initShowTask();
        new Thread(() -> {
            while (true) {
                Platform.runLater(() -> {
                    changeMode();
                    activatePeriodic();
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

    private void initElements(){
        titleWrite.setText("");
        descriptions.setText("");
        dateInit.setValue(null);
        initComBoxes();
    }

    @Override
    public void selectAdd(){
        String title = titleWrite.getText();

        String description = descriptions.getText();
        String category = categoryOption.getValue();
        Calendar date = Calendar.getInstance();
        GregorianCalendar dateG=new GregorianCalendar();
        boolean taskValid=isTaskValid(title);
        boolean hourValid=isHourValid();
        boolean dateValid=isDateValid();
        if(taskValid && hourValid && dateValid){
            int year=dateInit.getValue().getYear();
            int month=dateInit.getValue().getMonthValue()-1;
            int day=dateInit.getValue().getDayOfMonth();
            int hour=Integer.parseInt(this.hour.getValue());
            int minutes=Integer.parseInt(this.minutes.getValue());
            dateG.set(year,month,day,hour,minutes);
            System.out.println(dateG.getTime());
            int amPM=this.amPM.getValue().equals("AM")?Calendar.AM:Calendar.PM;
            dateG.set(Calendar.AM_PM,amPM);
            date.setTime(dateG.getTime());
            String period = periodsOptions.getValue();
            Color colorSelect = color.getValue();
            if(period=="SINGLE_DAY") {
                System.out.println(date.getTime());
                ControllerTasks.getInstance().addTask(title, description, category, date, period, colorToString(colorSelect));
            }else{
                int yearEnd=endCalendar.getValue().getYear();
                int monthEnd=endCalendar.getValue().getMonthValue()-1;
                int dayEnd=endCalendar.getValue().getDayOfMonth();
                GregorianCalendar dateEnd=new GregorianCalendar();
                dateEnd.set(yearEnd,monthEnd,dayEnd);
                ControllerTasks.getInstance().addTask(title,description,category,date,period,colorToString(colorSelect),dateEnd);
            }
            taskShow=(ControllerTasks.getInstance().getTask(title));
            updateTableTask();
            initElements();
            try {
                ControllerTaskToDo.loadScreen("showTask.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            ControllerAlerts.errorContext("Please validate all fields correctly");
        }
    }

    private boolean isTaskValid(String title){
        boolean allow= true;
        if(title.isEmpty()){
            allow=false;
            titleError.setVisible(true);
        }else{
            titleError.setVisible(false);
        }
        return allow;
    }
    private boolean isHourValid(){
        boolean allow= true;
        try{
            int hour=Integer.parseInt(this.hour.getValue());
            int minutes=Integer.parseInt(this.minutes.getValue());
            hourError.setVisible(false);

        }catch (Exception e){
            allow=false;
            hourError.setVisible(true);
        }
        return allow;
    }

    private boolean isDateValid(){
        boolean allow=true;
        allow=isDateValid(dateInit);
        if(periodicOption.isSelected()){
            allow=isDateValid(endCalendar);
        }
        return allow;
    }


    private boolean isDateValid(DatePicker date){
        boolean allow= true;
        try{
            int year=date.getValue().getYear();
            int month=date.getValue().getMonthValue()-1;
            int day=date.getValue().getDayOfMonth();
            if(date==dateInit)
                dateInitError.setVisible(false);
            if(date==endCalendar)
                endDateError.setVisible(false);

        }catch (Exception e){
            allow=false;
            if(date==dateInit)
                dateInitError.setVisible(true);
            if(date==endCalendar)
                endDateError.setVisible(true);

        }
        return allow;
    }
    private String colorToString(Color color) {
        int red = (int) (color.getRed() * 255);
        int green = (int) (color.getGreen() * 255);
        int blue = (int) (color.getBlue() * 255);

        return String.format("#%02X%02X%02X", red, green, blue);
    }



}
