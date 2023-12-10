package com.example.tasktodo8d.controllers.Screens;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

import com.example.tasktodo8d.controllers.ControllerAlerts;
import com.example.tasktodo8d.controllers.ControllerTaskToDo;
import com.example.tasktodo8d.controllers.ControllerTasks;

import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.paint.Color;


public class ControllerAddTask extends BaseScreen implements Initializable  {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        isRunning=true;
        initComBoxes();
        initShowTask();
        new Thread(() -> {
            while (isRunning) {
                Platform.runLater(() -> {
                    updateTableTask();
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


    /**
     * Activates or deactivates the periodic option for adding tasks.
     * If the periodic option is selected, it enables the end date, end calendar, label period, and periods options.
     * If the periodic option is not selected, it disables the end date, end calendar, label period, and periods options.
     */
    private void activatePeriodic() {
        if (!periodicOption.isSelected()) {
            endDate.setDisable(true);
            endCalendar.setDisable(true);
            labelPeriod.setDisable(true);
            periodsOptions.setDisable(true);
            errorPeriodImg.setVisible(false);
            endDateError.setVisible(false);
        } else {
            endDate.setDisable(false);
            endCalendar.setDisable(false);
            labelPeriod.setDisable(false);
            periodsOptions.setDisable(false);
        }
    }


    /**
     * Initializes the elements in the ControllerAddTask screen.
     * Sets the titleWrite and descriptions fields to an empty string.
     * Sets the dateInit field to null.
     * Calls the initComBoxes method to initialize the combo boxes.
     */
    private void initElements(){
        titleWrite.setText("");
        descriptions.setText("");
        dateInit.setValue(null);
        initComBoxes();
    }

    /**
     * Selects the "Add" action and performs the necessary operations to add a task.
     * Retrieves the title, description, category, date, and other details of the task from the input fields.
     * Validates the task, hour, and date to ensure they are in the correct format.
     * If all validations pass, creates a new task with the provided details and adds it to the task list.
     * If the task is a single-day task, checks if the date is valid and adds the task to the task list.
     * If the task is a multi-day task, checks if the end date is valid and adds the task to the task list.
     * Updates the table of tasks, initializes the input elements, and loads the "showTask" screen.
     * If any validation fails, displays an error message.
     */
    @Override
    public void selectAdd(){
        String title = titleWrite.getText();
        boolean allow= false;
        String description = descriptions.getText();
        String category = categoryOption.getValue();
        Calendar date = Calendar.getInstance();
        GregorianCalendar dateG=new GregorianCalendar();
        boolean taskValid=isTaskValid(title);
        boolean hourValid=isHourValid();
        boolean dateValid=isDateValid();
        if(taskValid && hourValid && dateValid && !colorToString(color.getValue()).equals("#FFFFFF")){
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
            if(period=="SINGLE_DAY" ) {
                System.out.println(date.getTime());
                allow= dateValid(date);
                if(allow) allow=ControllerTasks.getInstance().addTask(title, description, category, date, period, colorToString(colorSelect));
            }else{
                int yearEnd=endCalendar.getValue().getYear();
                int monthEnd=endCalendar.getValue().getMonthValue()-1;
                int dayEnd=endCalendar.getValue().getDayOfMonth();
                GregorianCalendar dateEnd=new GregorianCalendar();
                dateEnd.set(yearEnd,monthEnd,dayEnd);
                if(dateValid(dateEnd,date))
                  allow= ControllerTasks.getInstance().addTask(title,description,category,date,period,colorToString(colorSelect),dateEnd);
            }
            if(allow){
                taskShow=(ControllerTasks.getInstance().getTask(title));
                updateTableTask();
                initElements();
                try {
                    ControllerTaskToDo.loadScreen("showTask.fxml");
                    isRunning=false;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }else{
            String errors="";
            errorPeriodImg.setVisible(false);
            colorError.setVisible(false);
            if(periodsOptions.getValue()=="SINGLE_DAY"){
                errors="--select a period diferent to SINGLE_DAY";
                errorPeriodImg.setVisible(true);
            }
            if(colorToString(color.getValue()).equals("#FFFFFF")){

               errors="--select a color";
               colorError.setVisible(true);
            }
            ControllerAlerts.errorContext("Please validate all fields correctly"+errors);
        }
    }

    /**
     * Checks if the given date is valid.
     * @param date The date to be validated.
     * @return true if the date is valid, false otherwise.
     */
    private boolean dateValid(Calendar date){
        Calendar now=Calendar.getInstance();
        boolean allow=true;
        dateInitError.setVisible(false);

        if(date.before(now)) {
            dateInitError.setVisible(true);
            ControllerAlerts.errorContext("Please validate the date, it is before the current date");
            allow= false;
        }
        return allow;
    }

    /**
     * Checks if the given end date is valid based on the given start date.
     * If the end date is before the start date, an error message is displayed.
     * 
     * @param dateEnd The end date to be validated.
     * @param dateInit The start date to compare against.
     * @return true if the end date is valid, false otherwise.
     */
    private boolean dateValid(Calendar dateEnd,Calendar dateInit){
        boolean allow=true;
        endDateError.setVisible(false);
        allow=dateValid(dateInit);
        if(allow && dateEnd.before(dateInit)) {
            endDateError.setVisible(true);
            ControllerAlerts.errorContext("Please validate the date, it is before the  init date");
            allow= false;
        }
        return allow;
    }

    /**
     * Checks if a task is valid based on its title.
     * 
     * @param title the title of the task
     * @return true if the task is valid, false otherwise
     */
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
    /**
     * Checks if the hour and minutes values are valid.
     * 
     * @return true if the hour and minutes values are valid, false otherwise.
     */
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


    /**
     * Checks if the date is valid.
     * 
     * @return true if the date is valid, false otherwise.
     */
    private boolean isDateValid(){
        boolean allow=true;
        allow=isDateValid(dateInit);
        if(periodicOption.isSelected()){
            allow=isDateValid(endCalendar);
        }
        return allow;
    }


    /**
     * Checks if the given date is valid.
     * 
     * @param date The DatePicker object representing the date to be checked.
     * @return true if the date is valid, false otherwise.
     */
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
    /**
     * Converts a Color object to its corresponding hexadecimal string representation.
     * @param color the Color object to convert
     * @return the hexadecimal string representation of the color
     */
    private String colorToString(Color color) {
        int red = (int) (color.getRed() * 255);
        int green = (int) (color.getGreen() * 255);
        int blue = (int) (color.getBlue() * 255);

        return String.format("#%02X%02X%02X", red, green, blue);
    }

    
    @Override
    protected void initComBoxes(){
        super.initComBoxes();
        initPeriodOptions();
    }
    /**
     * Initializes the period options for the screen.
     * Adds the available period options to the periodOptions ComboBox
     * and sets the default value to "SINGLE_DAY".
     */
    private void initPeriodOptions(){
        periodsOptions.getItems().addAll(
                "SINGLE_DAY", "EVERY_DAY", "EVERY_TWO_DAYS",
                "EVERY_THREE_DAYS", "EVERY_FOUR_DAYS", "EVERY_FIVE_DAYS",
                "EVERY_SIX_DAYS", "WEEKLY", "BIWEEKLY", "MONTHLY",
                "BIMONTHLY", "SEMESTRAL", "QUARTERLY", "ANNUAL"
        );
        periodsOptions.setValue("SINGLE_DAY");
    }




}
