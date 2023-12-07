package com.example.tasktodo8d.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.application.Platform;

import java.time.LocalTime;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ControllerTaskToDo implements Initializable {

    @FXML
    private Button mode;
    @FXML
    private MenuBar menuBar;
    @FXML
    private AnchorPane parent;

    @FXML
    private TextField search;

    @FXML
    private Label hour;

    @FXML
    private ImageView modeImg;

    @FXML
    private ImageView searchImg;

    private boolean isLight;

    public boolean isLight() {
        return isLight;
    }

    public void setLight(boolean light) {
        isLight = light;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
        new Thread(()->{
            while (true){
                LocalTime localTime = LocalTime.now();
                String formattedTime = localTime.format(formatter);
                Platform.runLater(() -> hour.setText(formattedTime));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        isLight = false;

    }

    public void changeMode(){
        isLight = !isLight;
        if(isLight){
            setLightMode();
        }else{
            setDarkMode();
        }
    }

    private void setLightMode(){
        parent.getStylesheets().clear();
        parent.getStylesheets().add(getClass().getResource("/styles/lightMode.css").toExternalForm());
        Image image = new Image(getClass().getResourceAsStream("/icon/searchL.png"));
        Image image2 = new Image(getClass().getResourceAsStream("/icon/modeL.png"));
        searchImg.setImage(image);
        modeImg.setImage(image2);
        menuBar.getStyleClass().add(getClass().getResource("/styles/menuLight.css").toExternalForm());
    }


    private void setDarkMode(){
        parent.getStylesheets().clear();
        parent.getStylesheets().add(getClass().getResource("/styles/darkMode.css").toExternalForm());
        Image image = new Image(getClass().getResourceAsStream("/icon/searchD.png"));
        Image image2 = new Image(getClass().getResourceAsStream("/icon/modeD.png"));
        searchImg.setImage(image);
        modeImg.setImage(image2);
        menuBar.getStyleClass().add(getClass().getResource("/styles/menuDark.css").toExternalForm());
    }


}
