package com.example.tasktodo8d.controllers.Screens;


import com.example.tasktodo8d.controllers.ControllerTasks;


import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;


public class ControllerAddTask extends BaseScreen implements Initializable  {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

    public void addTask(){
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
            int amPM=this.amPM.getValue().equals("AM")?Calendar.AM:Calendar.PM;
            dateG.set(Calendar.AM_PM,amPM);
            date.setTime(dateG.getTime());
            String period = periodsOptions.getValue();
            ControllerTasks.getInstance().addTask(title,description,category,date,period);
            taskShow=(ControllerTasks.getInstance().getTask(title));
            updateTableTask();
            initElements();
        }else{
            showAlert("Please validate all fields correctly");
        }


    }

    private boolean isTaskValid(String title){
        boolean allow= true;
        System.out.println("->" +title);
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



}
