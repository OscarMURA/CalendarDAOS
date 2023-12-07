package com.example.tasktodo8d.controllers;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class LimitedColorPickerExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        ColorPicker colorPicker = new ColorPicker();

        // Establecer colores permitidos
        Color[] allowedColors = {
                Color.RED,
                Color.GREEN,
                Color.BLUE,
                Color.YELLOW,
                Color.ORANGE
        };

        // Configurar un evento de cambio en el ColorPicker para limitar los colores
        colorPicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (!isValidColor(newValue, allowedColors)) {
                // Si el color seleccionado no está permitido, establecer el valor anterior
                colorPicker.setValue(oldValue);
            }
        });

        StackPane root = new StackPane(colorPicker);
        Scene scene = new Scene(root, 300, 200);

        primaryStage.setTitle("Limited ColorPicker Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private boolean isValidColor(Color color, Color[] allowedColors) {
        for (Color allowedColor : allowedColors) {
            if (color.equals(allowedColor)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
