package com.example.tasktodo8d.controllers;

import com.example.tasktodo8d.model.Task;
import com.example.tasktodo8d.model.TaskCategory;
import com.example.tasktodo8d.model.TaskStatus;
import com.example.tasktodo8d.model.TimePeriod;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ControllerTasks  {

    private List<Task> tasks;
    private static ControllerTasks instance;

    private ControllerTasks() {
        tasks = new ArrayList<>();
    }

    public static ControllerTasks getInstance() {
        if (instance == null) {
            instance = new ControllerTasks();
        }
        return instance;
    }



    public void addTask(String title, String description, String category, Calendar date, String timePeriod) {
        Task task = new Task(title, description, getCategory(category), date,  getTimePeriod(timePeriod));

        int insertionIndex = binarySearchInsertionIndex(tasks, task);

        tasks.add(insertionIndex, task);
    }

    private int binarySearchInsertionIndex(List<Task> tasks, Task newTask) {
        int low = 0;
        int high = tasks.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            Calendar midDate = tasks.get(mid).getDate();
            if (midDate.before(newTask.getDate())) {
                low = mid + 1;
            } else if (midDate.after(newTask.getDate())) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return low;
    }



    private TaskCategory getCategory(String category) {
        switch (category) {
            case "WORK":
                return TaskCategory.WORK;
            case "PERSONAL":
                return TaskCategory.PERSONAL;
            case "HEALTH":
                return TaskCategory.HEALTH;
            case "PROJECTS":
                return TaskCategory.PROJECTS;
            case "SHOPPING":
                return TaskCategory.SHOPPING;
            case "REMINDERS":
                return TaskCategory.REMINDERS;
            default:
                return null;
        }
    }

    private TaskStatus getStatus(String status) {
        switch (status) {
            case "TO_DO":
                return TaskStatus.TO_DO;
            case "IN_PROGRESS":
                return TaskStatus.IN_PROGRESS;
            case "CANCELED":
                return TaskStatus.CANCELED;
            case "COMPLETED":
                return TaskStatus.COMPLETED;
            default:
                return null;
        }
    }

    private TimePeriod getTimePeriod(String timePeriod) {
        TimePeriod result = null;
        switch (timePeriod) {
            case "SINGLE_DAY"->result=TimePeriod.SINGLE_DAY;
            case "EVERY_DAY"->
                    result= TimePeriod.EVERY_DAY;
            case "EVERY_TWO_DAYS"->
                    result= TimePeriod.EVERY_TWO_DAYS;
            case "EVERY_THREE_DAYS"->
                    result= TimePeriod.EVERY_THREE_DAYS;
            case "EVERY_FOUR_DAYS"->
                    result= TimePeriod.EVERY_FOUR_DAYS;
            case "EVERY_FIVE_DAYS"->
                    result= TimePeriod.EVERY_FIVE_DAYS;
            case "EVERY_SIX_DAYS"->
                    result= TimePeriod.EVERY_SIX_DAYS;
            case "WEEKLY"->
                    result= TimePeriod.WEEKLY;
            case "BIWEEKLY"->
                    result= TimePeriod.BIWEEKLY;
            case "MONTHLY"->
                    result= TimePeriod.MONTHLY;
            case "BIMONTHLY"->
                    result= TimePeriod.BIMONTHLY;
            case "SEMESTRAL"->
                    result= TimePeriod.SEMESTRAL;
            case "QUARTERLY"->
                    result= TimePeriod.QUARTERLY;
            case "ANNUAL"->
                    result=TimePeriod.ANNUAL;
        }
        return result;
    }
    public List<Task> Tasks() {
        return tasks;
    }



}
