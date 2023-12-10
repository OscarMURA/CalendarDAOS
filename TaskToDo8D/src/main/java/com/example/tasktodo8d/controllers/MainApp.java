package com.example.tasktodo8d.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDecorator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Configuración específica de macOS
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        System.setProperty("com.apple.mrj.application.apple.menu.about.name", "YourAppName");

        // Crear un botón de JFoenix
        JFXButton button = new JFXButton("Hello, JFoenix!");

        // Aplicar un estilo de JFoenix al botón
        button.getStyleClass().add("button-raised");

        // Colocar el botón en un contenedor
        StackPane root = new StackPane(button);

        // Utilizar JFXDecorator para darle un estilo de macOS a la ventana
        JFXDecorator decorator = new JFXDecorator(primaryStage, root, false, true, true);

        // Crear la escena
        Scene scene = new Scene(decorator, 300, 200);

        // Cargar el archivo CSS de JFoenix para aplicar estilos

        // Configurar la escena y mostrar la ventana
        primaryStage.setTitle("JavaFX App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
