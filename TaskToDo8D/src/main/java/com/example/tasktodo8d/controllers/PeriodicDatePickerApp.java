package com.example.tasktodo8d.controllers;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.Period;


public class PeriodicDatePickerApp extends Application {

    private DatePicker startDatePicker;
    private Label endDateLabel;

    @Override
    public void start(Stage primaryStage) {
        // Crear controles
        Label label = new Label("Selecciona la fecha de inicio:");

        startDatePicker = new DatePicker();
        startDatePicker.setOnAction(event -> updateEndDate());

        endDateLabel = new Label("Fecha de fin:");

        // Configurar diseño
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(label, startDatePicker, endDateLabel);

        // Configurar escena
        Scene scene = new Scene(root, 300, 200);

        // Configurar escenario principal
        primaryStage.setTitle("JavaFX Periodic DatePicker");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void updateEndDate() {
        LocalDate startDate = startDatePicker.getValue();

        if (startDate != null) {
            // Definir un periodo, por ejemplo, 30 días
            Period period = Period.ofDays(30);

            // Calcular la fecha de fin sumando el periodo a la fecha de inicio
            LocalDate endDate = startDate.plus(period);

            // Mostrar la fecha de fin
            endDateLabel.setText("Fecha de fin: " + endDate);
        } else {
            endDateLabel.setText("Fecha de fin:");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
