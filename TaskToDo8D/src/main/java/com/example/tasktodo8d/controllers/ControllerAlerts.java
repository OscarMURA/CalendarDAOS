package com.example.tasktodo8d.controllers;

import java.util.Optional;

import com.example.tasktodo8d.AppTaskToDo;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.StageStyle;

/**
 * The ControllerAlerts class provides methods for displaying different types of
 * alerts.
 */
public class ControllerAlerts {

    /**
     * Displays an error alert with the given text.
     *
     * @param text the text to be displayed in the alert
     */
    public static void errorContext(String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        modeChange(alert);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }

    /**
     * Displays a confirmation alert with the given message.
     *
     * @param message the message to be displayed in the alert
     * @return true if the user accepts the confirmation, false otherwise
     */
    public static boolean showConfirmation(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        modeChange(alert);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText(message);
        ButtonType buttonTypeOK = new ButtonType("Accept", ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == buttonTypeOK;
    }

    public static boolean showConfirmation(String message,String hearder) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        modeChange(alert);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText(hearder+"\n\n"+message);
        ButtonType buttonTypeOK = new ButtonType("Accept", ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == buttonTypeOK;
    }

    /**
     * The function displays an information message in a pop-up window.
     * 
     * @param message The message parameter is a string that represents the
     *                information you want to display in the alert dialog.
     */
    public static void showInformation(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        modeChange(alert);
        alert.showAndWait();
    }

    private static void modeChange(Alert alert){
        Mode mode=ControllerTaskToDo.isLight();
        if(mode==Mode.DARK){
            alert.getDialogPane().getStylesheets().add(
                AppTaskToDo.class.getResource("/styles/darkAlert.css").toExternalForm());
        }else{
            alert.getDialogPane().getStylesheets().add(
                AppTaskToDo.class.getResource("/styles/lightAlert.css").toExternalForm());
        }
    }


}
