package com.example.tasktodo8d.model;


/**
 * Represents the categories of tasks.
 */
public enum TaskCategory {
    WORK("#1E90FF"),        // Trabajo: Dodger Blue
    PERSONAL("#FFD700"),     // Personal: Gold
    HEALTH("#32CD32"),       // Salud: Lime Green
    PROJECTS("#FF6347"),     // Proyectos: Tomato
    SHOPPING("#8A2BE2"),     // Compras: Blue Violet
    REMINDERS("#FFA500");    // Recordatorios: Orange

    private final String color;

    /**
     * Constructs a TaskCategory with the specified color.
     * @param color the color associated with the category
     */
    TaskCategory(String color) {
        this.color = color;
    }

    /**
     * Returns the color associated with the category.
     * @return the color
     */
    public String getColor() {
        return color;
    }
}
