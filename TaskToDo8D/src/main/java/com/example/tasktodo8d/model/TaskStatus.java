package com.example.tasktodo8d.model;
public enum TaskStatus {
    TO_DO("To Do", "#FF4500"),          // Por hacer: Orange Red
    IN_PROGRESS("In Progress", "#1E90FF"),  // En progreso: Dodger Blue
    CANCELED("Canceled", "#808080"),   // Cancelada: Gray
    COMPLETED("Completed", "#32CD32");  // Finalizada: Lime Green

    private final String status;
    private final String color;
    TaskStatus(String status, String color) {
        this.status = status;
        this.color = color;
    }
    public String getStatus() {
        return status;
    }
    public String getColor() {
        return color;
    }
}

