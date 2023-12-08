package com.example.tasktodo8d.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Task {
        private String name;
        private String description;
        private TaskCategory category;
        private Calendar date;
        private String dateString;
        private TaskStatus status;
        private TimePeriod timePeriod;

        public Task(String name, String description, TaskCategory category, Calendar date,  TimePeriod timePeriod) {
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

        private String getDateAsString(){
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy --hh:mm a");
                String date = format.format(this.date.getTime());
                return date;
        }

        public String getDateString(){
                return dateString;
        }



}

