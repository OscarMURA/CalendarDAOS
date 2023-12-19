package com.example.tasktodo8d.controllers.Screens;

import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import com.example.tasktodo8d.controllers.ControllerTasks;
import com.example.tasktodo8d.model.Task;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ControllerConsultsTasks extends BaseTableView implements  Initializable{

    @FXML
    protected Button removeBtn;
    @FXML
    protected Slider sliderDays;
    @FXML
    protected RadioButton expirationRadio;
    @FXML 
    private RadioButton periodsOptions;
    @FXML
    private RadioButton categoriesRadio;
    @FXML
    private RadioButton statusRadio;
    @FXML
    private Label days;
    @FXML
    private ComboBox<String> periodsCombox;
    @FXML
    private ComboBox<String> categoryOption;
    @FXML
    private ComboBox<String> statusCombox;
    @FXML 
    private ImageView removeImg;
    @FXML 
    private ComboBox<String> typeCombox;
    @FXML
    private ImageView searchImg;
    private Task taskShow;
    private List<Task> tasks;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tasks=new ArrayList<Task>();
        isRunning = true;
        initComBoxes();
        initShowTask();
        updateTableTask();
        new Thread(() -> {
            while (isRunning) {
                Platform.runLater(() -> { 
                    activateRadioButtos();
                    changeMode();
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
    }

    protected void initShowTask() {
        tasks=ControllerTasks.getInstance().getAllTasks();
        if(!tasks.isEmpty())
            taskShow = tasks.get(0);
        else
            taskShow=null;
    }
    protected void initComBoxes() {
        periodsCombox.getItems().addAll("Today","This week", "This month");
        periodsCombox.getSelectionModel().selectFirst();
        categoryOption.getItems().addAll("PERSONAL", "WORK", "HEALTH", "PROJECTS","SHOPPING","REMINDERS");
        categoryOption.getSelectionModel().selectFirst();
        statusCombox.getItems().addAll("TO_DO", "IN_PROGRESS", "CANCELED", "COMPLETED","NO_COMPLETED");
        statusCombox.getSelectionModel().selectFirst();
        days.setText("1");
        typeCombox.getItems().addAll("CURRENTS","ALL");
        typeCombox.getSelectionModel().selectFirst();
    }

    @Override
    public void setLightMode() {
        Image remove = new Image(getClass().getResourceAsStream("/icon/removeL.png"));
        Image search= new Image(getClass().getResourceAsStream("/icon/searchBtnL.png"));
        searchImg.setImage(search);
        removeImg.setImage(remove);
        parent.getStylesheets().clear();
        parent.getStylesheets().add(getClass().getResource("/styles/lightMode.css").toExternalForm());

    }
    @Override
    public void setDarkMode() {
        Image remove = new Image(getClass().getResourceAsStream("/icon/removeD.png"));
        Image search= new Image(getClass().getResourceAsStream("/icon/searchBtnD.png"));
        searchImg.setImage(search);
        removeImg.setImage(remove);
        parent.getStylesheets().clear();
        parent.getStylesheets().add(getClass().getResource("/styles/darkMode.css").toExternalForm());
    }
    @Override
    protected void selectionTask() {
        Task task = (Task) tableTask.getSelectionModel().getSelectedItem();
        if (task != null) {
            this.taskShow = task;
        }
    }
    /**
     * The function activates or deactivates certain UI elements based on the selected radio buttons.
     */
    private void activateRadioButtos(){
        if(!statusRadio.isSelected())
            statusCombox.setDisable(true);
        else
            statusCombox.setDisable(false);
        
        if(!categoriesRadio.isSelected())
            categoryOption.setDisable(true);
         else
            categoryOption.setDisable(false);
        
        if(!periodsOptions.isSelected())
            periodsCombox.setDisable(true);
        else
            periodsCombox.setDisable(false);
        if(!expirationRadio.isSelected()){
            sliderDays.setDisable(true);
            days.setDisable(true);
            sliderDays.valueProperty().addListener((observable, oldValue, newValue) -> {
                days.setText(newValue.intValue()+"");
            });
        }else{
            sliderDays.setDisable(false);
            days.setDisable(false);
        }
    }

    /**
     * The `selectionSearch` function filters tasks based on selected criteria and updates the task list.
     */
    public void selectionSearch(){
        String type=typeCombox.getSelectionModel().getSelectedItem();
        boolean status=statusRadio.isSelected();
        boolean category=categoriesRadio.isSelected();
        boolean period=periodsOptions.isSelected();
        boolean expiration=expirationRadio.isSelected();
        List<Task> auxTasks=ControllerTasks.getInstance().getAllTasks();
        if(status){
            tasks.clear();
            System.out.println("Entre al status");
            String statusTask=statusCombox.getSelectionModel().getSelectedItem();
            tasks.addAll(ControllerTasks.getInstance().getTaskStatus(statusTask,type));
            auxTasks.retainAll(tasks);
        }
        if(category){
            tasks.clear();
            System.out.println("Entre al category");
            String categoryTask=categoryOption.getSelectionModel().getSelectedItem();
            tasks.addAll(ControllerTasks.getInstance().getTaskCategory(categoryTask,type));
            auxTasks.retainAll(tasks);
        }
        if(period){
            tasks.clear();
            System.out.println("Entre al period");
            String periodTask=periodsCombox.getSelectionModel().getSelectedItem();
            tasks.addAll(ControllerTasks.getInstance().getTaskPeriod(periodTask));
            auxTasks.retainAll(tasks);
        }
        if(expiration){
            tasks.clear();
            System.out.println("Entre al expiration");
            int days=(int)sliderDays.getValue();
            tasks.addAll(ControllerTasks.getInstance().getTaskByDayExpiration(days));
            auxTasks.retainAll(tasks);
        }
        Set<Task> aux=new HashSet<>(tasks);
        List<Task> aux2=new ArrayList<>(aux);
        tasks.clear();
        tasks.addAll(aux2);
        tasks.sort(Comparator.comparing(Task::getDate));
        if(!tasks.isEmpty())
            taskShow=tasks.get(0);
        updateTableTask();
    }

    @Override
    protected void updateTableTask(){
        if(tasks.isEmpty())
            tasks=ControllerTasks.getInstance().getAllTasks();
        ObservableList<Task> tasksTable = FXCollections.observableArrayList(tasks);
        tableTask.setItems(tasksTable);
        super.updateTableTask();
    }


   /**
    * The function `selectRemove()` removes a selected task from a table and updates the table display.
    */
    public void selectRemove(){
        Task task = (Task) tableTask.getSelectionModel().getSelectedItem();
        if (task != null) {
            ControllerTasks.getInstance().removeTask(task);
            tasks.remove(task);
            
        }else if(taskShow!=null){
            ControllerTasks.getInstance().removeTask(taskShow);
            tasks.remove(taskShow);
        }
        
        if(!tasks.isEmpty())
                taskShow=tasks.get(0);
            else
                taskShow=null;
            updateTableTask();
    }
        

}
