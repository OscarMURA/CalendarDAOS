package com.example.tasktodo8d.controllers;

import com.example.tasktodo8d.model.Task;
import com.example.tasktodo8d.model.TaskCategory;
import com.example.tasktodo8d.model.TaskStatus;
import com.example.tasktodo8d.model.TimePeriod;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class ControllerTasks  {

    private List<Task> tasks;
    private static ControllerTasks instance;

    private ControllerTasks() {
        tasks = new ArrayList<>();
        addTask("Work", "Work", "WORK", new GregorianCalendar(2023, 5, 1), "SINGLE_DAY","#000080");
        addTask("Play", "Play", "PERSONAL", new GregorianCalendar(2022, 5, 2), "SINGLE_DAY","#FFFF00");
        addTask("Health", "Health", "HEALTH", new GregorianCalendar(2021, 5, 3), "SINGLE_DAY","#00FF00");
    }

    public static ControllerTasks getInstance() {
        if (instance == null) {
            instance = new ControllerTasks();
        }
        return instance;
    }



    public boolean addTask(String title, String description, String category, Calendar date, String timePeriod, String color) {
        boolean result;
        Task task = new Task(title, description, getCategory(category), date,  getTimePeriod(timePeriod), color);
        int insertionIndex = binarySearchInsertionIndex(tasks, task);
        tasks.add(insertionIndex, task);
        result = true;
        return result;
    }

    public boolean addTask(String title, String description, String category, Calendar date, String timePeriod, String color, Calendar endDate){
        boolean result;
        int periodTime =getTimePeriod(timePeriod).getDays();
        addTask(title,description,category,date,timePeriod,color);
        do{
            date=(Calendar) date.clone();
            date.add(Calendar.DAY_OF_MONTH,periodTime);
            if(date.before(endDate)){
                addTask(title,description,category,date,timePeriod,color);
            }
        }while(date.before(endDate));
        result=true;
        return result;
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

    public Task getTask(String name) {
        Task result=null;
        for (Task task : tasks) {
            if (task.getName().equals(name)) {
                result= task;
            }
        }
        return result;
    }

    public boolean removeTaskRepeat(Task task){
        List<Task> tasksSame=taskSame(task);
        for(Task task1:tasksSame){
            tasks.remove(task1);

        }
        return tasksSame.size()>0;
    }

    public List<Task> taskSame(Task task){
        List<Task> result=new ArrayList<>();
        for(Task task1:tasks){
            if(task1.equals(task)){
                result.add(task1);
            }
        }
        return result;
    }




}
