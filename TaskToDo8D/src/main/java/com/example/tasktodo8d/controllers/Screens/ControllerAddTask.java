package com.example.tasktodo8d.controllers.Screens;

import com.example.tasktodo8d.controllers.ControllerTaskToDo;
import com.example.tasktodo8d.controllers.Mode;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
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


    private void addTask(){
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


    }

}
