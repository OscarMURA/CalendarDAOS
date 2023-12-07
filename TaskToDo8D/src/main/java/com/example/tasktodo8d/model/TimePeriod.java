package com.example.tasktodo8d.model;

public enum TimePeriod {
    SINGLE_DAY("Single Day", "#FF4500"),             // Single Day: Orange Red
    EVERY_DAY("Every Day", "#1E90FF"),               // Every Day: Dodger Blue
    EVERY_TWO_DAYS("Every Two Days", "#FFD700"),      // Every Two Days: Gold
    EVERY_THREE_DAYS("Every Three Days", "#32CD32"),  // Every Three Days: Lime Green
    EVERY_FOUR_DAYS("Every Four Days", "#FF6347"),    // Every Four Days: Tomato
    EVERY_FIVE_DAYS("Every Five Days", "#8A2BE2"),    // Every Five Days: Blue Violet
    EVERY_SIX_DAYS("Every Six Days", "#FFA500"),      // Every Six Days: Orange
    WEEKLY("Weekly", "#FF4500"),                     // Weekly: Orange Red
    BIWEEKLY("Biweekly", "#1E90FF"),                 // Biweekly: Dodger Blue
    MONTHLY("Monthly", "#FFD700"),                   // Monthly: Gold
    BIMONTHLY("Bimonthly", "#32CD32"),               // Bimonthly: Lime Green
    SEMESTRAL("Semiannual", "#FF6347"),              // Semiannual: Tomato
    QUARTERLY("Quarterly", "#8A2BE2"),               // Quarterly: Blue Violet
    ANNUAL("Annual", "#FFA500");                      // Annual: Orange

    private final String description;
    private final String color;

    TimePeriod(String description, String color) {
        this.description = description;
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public String getColor() {
        return color;
    }
}
