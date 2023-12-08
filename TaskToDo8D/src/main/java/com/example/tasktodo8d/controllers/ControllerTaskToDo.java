package com.example.tasktodo8d.controllers;

import com.example.tasktodo8d.AppTaskToDo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.application.Platform;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.time.LocalTime;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ControllerTaskToDo implements Initializable,Modeable {

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
    @FXML
    private BorderPane miPanel;

    private static Mode isLight;



    public static Mode isLight() {
        return isLight;
    }

    public void setLight(Mode light) {
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
        isLight = Mode.LIGHT;
        try {
            addTask();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void changeMode(){
        isLight = (isLight == Mode.LIGHT)?Mode.DARK:Mode.LIGHT;
        if(isLight==Mode.LIGHT){
            setLightMode();
        }else{
            setDarkMode();
        }
    }

     public void setLightMode(){
        parent.getStylesheets().clear();
        parent.getStylesheets().add(getClass().getResource("/styles/lightMode.css").toExternalForm());
        Image image = new Image(getClass().getResourceAsStream("/icon/searchL.png"));
        Image image2 = new Image(getClass().getResourceAsStream("/icon/modeL.png"));
        searchImg.setImage(image);
        modeImg.setImage(image2);
        menuBar.getStyleClass().add(getClass().getResource("/styles/menuLight.css").toExternalForm());
    }


    public void setDarkMode(){
        parent.getStylesheets().clear();
        parent.getStylesheets().add(getClass().getResource("/styles/darkMode.css").toExternalForm());
        Image image = new Image(getClass().getResourceAsStream("/icon/searchD.png"));
        Image image2 = new Image(getClass().getResourceAsStream("/icon/modeD.png"));
        searchImg.setImage(image);
        modeImg.setImage(image2);
        menuBar.getStyleClass().add(getClass().getResource("/styles/menuDark.css").toExternalForm());
    }

    @FXML
    public void addTask() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AppTaskToDo.class.getResource("showTask.fxml"));
        BorderPane root =(BorderPane) loader.load();
        miPanel.getChildren().clear();
        miPanel.setCenter(root);

    }


}
