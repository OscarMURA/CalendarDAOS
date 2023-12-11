package com.example.tasktodo8d.controllers.Screens;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.tasktodo8d.model.Task;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.application.Platform;


public class ControllerConsultsTasks extends BaseTableView implements  Initializable{
    @FXML
    protected ButtonBar removeBtn;
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
    private TextField search;
    private Task taskShow;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        isRunning = true;
        initComBoxes();
        initShowTask();
        new Thread(() -> {
            while (isRunning) {
                Platform.runLater(() -> {
                    updateTableTask();
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


    }
    protected void initComBoxes() {
        periodsCombox.getItems().addAll("Today","This week", "This month");
        periodsCombox.getSelectionModel().selectFirst();
        categoryOption.getItems().addAll("PERSONAL", "WORK", "HEALTH", "PROJECTS","SHOPPING","REMINDERS");
        categoryOption.getSelectionModel().selectFirst();
        statusCombox.getItems().addAll("TO_DO", "IN_PROGRESS", "CANCELED", "COMPLETED","NO_COMPLETED");
        statusCombox.getSelectionModel().selectFirst();
        days.setText("1");
    }


    @Override
    public void setLightMode() {
        Image remove = new Image(getClass().getResourceAsStream("/icon/removeL.png"));
        removeImg.setImage(remove);
        parent.getStylesheets().clear();
        parent.getStylesheets().add(getClass().getResource("/styles/darkMode.css").toExternalForm());
    }
    @Override
    public void setDarkMode() {
        Image remove = new Image(getClass().getResourceAsStream("/icon/removeD.png"));
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
    private void activateRadioButtos(){
        if(statusRadio.isSelected()){
            statusCombox.setDisable(true);
        }else{
            statusCombox.setDisable(false);
        }
        if(categoriesRadio.isSelected()){
            categoryOption.setDisable(true);
        }else{
            categoryOption.setDisable(false);
        }
        if(periodsOptions.isSelected()){
            periodsCombox.setDisable(true);
        }else{
            periodsCombox.setDisable(false);
        }
        if(expirationRadio.isSelected()){
            sliderDays.setDisable(true);
            days.setDisable(true);
        }else{
            sliderDays.setDisable(false);
            days.setDisable(false);
        }
    }
        

}
