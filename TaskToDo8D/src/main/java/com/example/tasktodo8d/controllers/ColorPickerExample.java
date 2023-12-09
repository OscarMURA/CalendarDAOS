package com.example.tasktodo8d.controllers;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ColorPickerExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        ColorPicker colorPicker = new ColorPicker();

        // Agregar un oyente para detectar cambios en el valor seleccionado
        colorPicker.setOnAction(event -> {
            Color selectedColor = colorPicker.getValue();
            String colorString = colorToString(selectedColor);
            System.out.println("Color seleccionado: " + colorString);
        });

        StackPane root = new StackPane();
        root.getChildren().add(colorPicker);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("ColorPicker Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Método para convertir un objeto Color a una cadena RGB
    private String colorToString(Color color) {
        int red = (int) (color.getRed() * 255);
        int green = (int) (color.getGreen() * 255);
        int blue = (int) (color.getBlue() * 255);

        return String.format("#%02X%02X%02X", red, green, blue);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
