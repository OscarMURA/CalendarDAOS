package com.example.tasktodo8d.model;


public enum TaskCategory {
    WORK("#1E90FF"),        // Trabajo: Dodger Blue
    PERSONAL("#FFD700"),     // Personal: Gold
    HEALTH("#32CD32"),       // Salud: Lime Green
    PROJECTS("#FF6347"),     // Proyectos: Tomato
    SHOPPING("#8A2BE2"),     // Compras: Blue Violet
    REMINDERS("#FFA500");    // Recordatorios: Orange
    private final String color;
    TaskCategory(String color) {
        this.color = color;
    }
    public String getColor() {
        return color;
    }
}
