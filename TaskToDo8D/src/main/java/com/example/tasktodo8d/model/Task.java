package com.example.tasktodo8d.model;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


/**
 * Represents a task in the application.
 */
public class Task {

        /**
         * Represents the name of a task.
         */
        private String name;
        /**
         * Represents the description of a task.
         */
        private String description;
        /**
         * Represents the category of a task.
         */
        private TaskCategory category;
        /**
         * Represents a task with a specific date.
         */
        private Calendar date;
        private String dateString;
        /**
         * Represents the status of a task.
         */
        private TaskStatus status;
        /**
         * Represents the time period of a task.
         */
        private TimePeriod timePeriod;
        /**
         * The color RGB of the task.
         */
        private String color;

        public Task(String name, String description, TaskCategory category, Calendar date,  TimePeriod timePeriod,String color) {
                this.color = color;
                this.name = name;
                this.description = description;
                this.category = category;
                this.date = date;
                this.status = TaskStatus.TO_DO;
                this.timePeriod = timePeriod;
                this.dateString = getDateAsString();
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public TaskCategory getCategory() {
                return category;
        }

        public void setCategory(TaskCategory category) {
                this.category = category;
        }

        public Calendar getDate() {
                return date;
        }

        public void setDate(Calendar date) {
                this.date = date;
                dateString = getDateAsString();
        }

        public TaskStatus getStatus() {
                return status;
        }

        public void setStatus(TaskStatus status) {
                this.status = status;
        }

        public TimePeriod getTimePeriod() {
                return timePeriod;
        }

        public void setTimePeriod(TimePeriod timePeriod) {
                this.timePeriod = timePeriod;
        }

        /**
         * The function getDateAsString() returns a formatted string representation of a date and time.
         * 
         * @return The method is returning a formatted date string.
         */
        private String getDateAsString(){
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat hourF= new SimpleDateFormat("hh:mm a");
                String hour = hourF.format(this.date.getTime());
                String date = format.format(this.date.getTime());
                if(!hour.contains("11:59 p.")){
                        date = date + "  " + hour;
                }
                int dayOfWeek = this.date.get(Calendar.DAY_OF_WEEK);
                date = new DateFormatSymbols(Locale.ENGLISH).getWeekdays()[dayOfWeek] + " " + date;
                return date;
        }

        public String getDateString(){
                return dateString;
        }

        public String getColor() {
                return color;
        }
        public void setColor(String color) {
                this.color = color;
        }
        public String toString(){
                return "Name: " + name + "\n" +
                        "Description: " + description + "\n" +
                        "Category: " + category + "\n" +
                        "Date: " + getDateAsString()+ "\n" +
                        "Status: " + status + "\n" +
                        "Time Period: " + timePeriod + "\n" +
                        "Color: " + color + "\n";
        }


}

