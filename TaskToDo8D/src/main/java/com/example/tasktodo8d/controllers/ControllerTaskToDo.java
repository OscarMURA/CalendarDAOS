package com.example.tasktodo8d.controllers;

import com.example.tasktodo8d.AppTaskToDo;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalTime;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ControllerTaskToDo implements Initializable,Modeable {

    /**
     * The mode button in the task to-do controller.
     */
    @FXML
    private Button mode;
    /**
     * The MenuBar class represents a horizontal bar of menus that can be added to a JavaFX application.
     * It provides a convenient way to create and manage menus and menu items.
     */
    @FXML
    private MenuBar menuBar;
    /**
     * The AnchorPane class provides a container for managing layout constraints of its child nodes.
     * It is a part of the JavaFX library and is used in graphical user interface (GUI) development.
     * AnchorPane allows developers to anchor child nodes to the top, bottom, left, right, or center of the pane.
     * This helps in creating responsive and flexible UI designs.
     */
    @FXML
    private AnchorPane parent;

    /**
        * The search TextField for searching tasks.
        */
    @FXML
    private TextField search;

    /**
     * The hour label.
     */
    @FXML
    private Label hour;

    /**
     * Represents an image view, which is a graphical control that displays an image.
     */
    @FXML
    private ImageView modeImg;

    @FXML
    private ImageView searchImg;
    /**
     * Represents a container that divides its area into five regions: top, bottom, left, right, and center.
     * The BorderPane class is a layout container that provides flexible arrangement of nodes in a border-like fashion.
     * It is commonly used in JavaFX applications for creating user interfaces.
     */
    @FXML
    private  BorderPane miPanel;
    /**
     * This class represents a container that divides its space into five regions: top, bottom, left, right, and center.
     * It is used as a layout container in JavaFX to arrange nodes in a specific layout.
     */
    private static BorderPane staticPanel;

    /**
     * Represents the mode of the task.
     */
    private static Mode isLight;



    /**
     * Returns the mode of the application.
     *
     * @return the mode of the application
     */
    public static Mode isLight() {
        return isLight;
    }

    /**
     * Sets the light mode for the task.
     *
     * @param light the light mode to be set
     */
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
        staticPanel = miPanel;
        try {
            loadScreen("showTask.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Changes the mode of the application between light mode and dark mode.
     * If the current mode is light mode, it switches to dark mode, and vice versa.
     */
    public void changeMode(){
        isLight = (isLight == Mode.LIGHT)?Mode.DARK:Mode.LIGHT;
        if(isLight==Mode.LIGHT){
            setLightMode();
        }else{
            setDarkMode();
        }
    }

    /**
     * Sets the light mode for the controller.
     * This method clears the existing stylesheets and adds the light mode stylesheet.
     * It also sets the images for search and mode icons to the light mode versions.
     * Finally, it applies the light mode style to the menu bar.
     */
     public void setLightMode(){
        parent.getStylesheets().clear();
        parent.getStylesheets().add(getClass().getResource("/styles/lightMode.css").toExternalForm());
        Image image = new Image(getClass().getResourceAsStream("/icon/searchL.png"));
        Image image2 = new Image(getClass().getResourceAsStream("/icon/modeL.png"));
        searchImg.setImage(image);
        modeImg.setImage(image2);
        menuBar.getStyleClass().add(getClass().getResource("/styles/menuLight.css").toExternalForm());
    }


    /**
     * Sets the dark mode for the controller.
     * This method clears the existing stylesheets and adds the dark mode stylesheet.
     * It also updates the images and menu bar style to reflect the dark mode.
     */
    public void setDarkMode(){
        parent.getStylesheets().clear();
        parent.getStylesheets().add(getClass().getResource("/styles/darkMode.css").toExternalForm());
        Image image = new Image(getClass().getResourceAsStream("/icon/searchD.png"));
        Image image2 = new Image(getClass().getResourceAsStream("/icon/modeD.png"));
        searchImg.setImage(image);
        modeImg.setImage(image2);
        menuBar.getStyleClass().add(getClass().getResource("/styles/menuDark.css").toExternalForm());
    }


    /**
     * Adds a task to the application.
     * This method loads the "showTask.fxml" file using FXMLLoader and sets it as the center of the miPanel BorderPane.
     * @throws IOException if an I/O error occurs while loading the fxml file.
     */
    public void addTask() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AppTaskToDo.class.getResource("showTask.fxml"));
        BorderPane root = loader.load();
        miPanel.getChildren().clear();
        miPanel.setCenter(root);

    }


    /**
     * Loads a screen specified by the given FXML file path and sets it as the center content of the static panel.
     * The screen's appearance is determined by the current light/dark mode.
     *
     * @param fxml the file path of the FXML file representing the screen to be loaded
     * @throws IOException if an I/O error occurs while loading the FXML file
     */
    public static void loadScreen(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AppTaskToDo.class.getResource(fxml));
        BorderPane root = loader.load();
        if(isLight==Mode.LIGHT){
            root.getStylesheets().add(AppTaskToDo.class.getResource("/styles/lightMode.css").toExternalForm());
        }else{
            root.getStylesheets().add(AppTaskToDo.class.getResource("/styles/darkMode.css").toExternalForm());
        }
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.15), event -> {
            staticPanel.getChildren().clear();
            staticPanel.setCenter(root);
        }));
        timeline.play();
    }


}
