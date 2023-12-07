package com.example.tasktodo8d.controllers;

import com.example.tasktodo8d.model.Task;

import java.util.ArrayList;

public class ControllerTasks {

    private ArrayList<Task> tasks;
    private static ControllerTaskToDo instance;

    private ControllerTasks() {
        tasks = new ArrayList<>();
    }

    private ControllerTaskToDo getInstance() {
        if (instance == null) {
            instance = new ControllerTaskToDo();
        }
        return instance;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

}
