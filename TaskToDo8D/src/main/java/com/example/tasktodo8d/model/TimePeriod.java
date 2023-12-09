package com.example.tasktodo8d.model;
public enum TimePeriod {
    SINGLE_DAY("Single Day", "#FF4500", 0),             // Single Day: Orange Red
    EVERY_DAY("Every Day", "#1E90FF", 1),               // Every Day: Dodger Blue
    EVERY_TWO_DAYS("Every Two Days", "#FFD700", 2),      // Every Two Days: Gold
    EVERY_THREE_DAYS("Every Three Days", "#32CD32", 3),  // Every Three Days: Lime Green
    EVERY_FOUR_DAYS("Every Four Days", "#FF6347", 4),    // Every Four Days: Tomato
    EVERY_FIVE_DAYS("Every Five Days", "#8A2BE2", 5),    // Every Five Days: Blue Violet
    EVERY_SIX_DAYS("Every Six Days", "#FFA500", 6),      // Every Six Days: Orange
    WEEKLY("Weekly", "#FF4500", 7),                     // Weekly: Orange Red
    BIWEEKLY("Biweekly", "#1E90FF", 14),                 // Biweekly: Dodger Blue
    MONTHLY("Monthly", "#FFD700", 30),                   // Monthly: Gold
    BIMONTHLY("Bimonthly", "#32CD32", 60),               // Bimonthly: Lime Green
    SEMESTRAL("Semiannual", "#FF6347", 182),             // Semiannual: Tomato
    QUARTERLY("Quarterly", "#8A2BE2", 91),               // Quarterly: Blue Violet
    ANNUAL("Annual", "#FFA500", 365);                    // Annual: Orange

    private final String description;
    private final String color;
    private final int days;

    TimePeriod(String description, String color, int days) {
        this.description = description;
        this.color = color;
        this.days = days;
    }

    @Override
    public String toString() {
        return description;
    }

    public String getColor() {
        return color;
    }

    public int getDays() {
        return days;
    }
}
