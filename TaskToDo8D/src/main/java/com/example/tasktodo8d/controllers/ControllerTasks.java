package com.example.tasktodo8d.controllers;

import com.example.tasktodo8d.model.Task;
import com.example.tasktodo8d.model.TaskCategory;
import com.example.tasktodo8d.model.TaskStatus;
import com.example.tasktodo8d.model.TimePeriod;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.text.SimpleDateFormat;


/**
 * The ControllerTasks class represents the controller for tasks in the
 * application.
 * It is responsible for managing and handling tasks.
 */
public class ControllerTasks {
    /**
     * The list of tasks.
     */
    private List<Task> tasks;
    /**
     * This class represents the controller for tasks in the application.
     * It is responsible for managing and handling tasks.
     */
    private static ControllerTasks instance;

    private ControllerTasks() {
        tasks = new ArrayList<>();
        addTask("Work", "Work", "WORK", new GregorianCalendar(2023, 5, 1), "SINGLE_DAY", "#000080");
        addTask("Play", "Play", "PERSONAL", new GregorianCalendar(2022, 5, 2), "SINGLE_DAY", "#FFFF00");
        addTask("Health", "Health", "HEALTH", new GregorianCalendar(2021, 5, 3), "SINGLE_DAY", "#00FF00");
    }

    public static ControllerTasks getInstance() {
        if (instance == null) {
            instance = new ControllerTasks();
        }
        return instance;
    }

    /**
     * Adds a new task to the task list.
     *
     * @param title       the title of the task
     * @param description the description of the task
     * @param category    the category of the task
     * @param date        the date of the task
     * @param timePeriod  the time period of the task
     * @param color       the color of the task
     * @return true if the task was successfully added, false otherwise
     */
    public boolean addTask(String title, String description, String category, Calendar date, String timePeriod,
            String color) {
        boolean result;
        Task task = new Task(title, description, getCategory(category), date, getTimePeriod(timePeriod), color);
        int insertionIndex = binarySearchInsertionIndex(tasks, task);
        tasks.add(insertionIndex, task);
        result = true;
        return result;
    }

    /**
     * Adds a task with the given details to the task list.
     * 
     * @param title       the title of the task
     * @param description the description of the task
     * @param category    the category of the task
     * @param date        the starting date of the task
     * @param timePeriod  the time period of the task
     * @param color       the color of the task
     * @param endDate     the end date of the task
     * @return true if the task is successfully added, false otherwise
     */
    public boolean addTask(String title, String description, String category, Calendar date, String timePeriod,
            String color, Calendar endDate) {
        boolean result;
        int periodTime = getTimePeriod(timePeriod).getDays();
        addTask(title, description, category, date, timePeriod, color);
        do {
            date = (Calendar) date.clone();
            date.add(Calendar.DAY_OF_MONTH, periodTime);
            if (date.before(endDate)) {
                addTask(title, description, category, date, timePeriod, color);
            }
        } while (date.before(endDate));
        result = true;
        return result;
    }

    /**
     * The function performs a binary search on a sorted list of tasks to find the
     * insertion index for a new task based on its date.
     * 
     * @param tasks   A list of Task objects, sorted in ascending order by date.
     * @param newTask The `newTask` parameter is an instance of the `Task` class.
     * @return The method is returning the index at which the newTask should be
     *         inserted into the tasks list in order to maintain the sorted order
     *         based on the date of the tasks.
     */
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

    /**
     * The function takes a string input representing a category and returns the
     * corresponding TaskCategory enum value.
     * 
     * @param category A string representing the category of a task.
     * @return The method is returning a TaskCategory object based on the input
     *         category string.
     */
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

    /**
     * The function takes a string input and returns the corresponding TaskStatus
     * enum value.
     * 
     * @param status A string representing the status of a task.
     * @return The method is returning a TaskStatus object.
     */
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

    /**
     * The function `getTimePeriod` takes a string input representing a time period
     * and returns the corresponding `TimePeriod` enum value.
     * 
     * @param timePeriod A string representing a time period.
     * @return The method is returning a TimePeriod object.
     */
    private TimePeriod getTimePeriod(String timePeriod) {
        TimePeriod result = null;
        switch (timePeriod) {
            case "SINGLE_DAY" -> result = TimePeriod.SINGLE_DAY;
            case "EVERY_DAY" ->
                result = TimePeriod.EVERY_DAY;
            case "EVERY_TWO_DAYS" ->
                result = TimePeriod.EVERY_TWO_DAYS;
            case "EVERY_THREE_DAYS" ->
                result = TimePeriod.EVERY_THREE_DAYS;
            case "EVERY_FOUR_DAYS" ->
                result = TimePeriod.EVERY_FOUR_DAYS;
            case "EVERY_FIVE_DAYS" ->
                result = TimePeriod.EVERY_FIVE_DAYS;
            case "EVERY_SIX_DAYS" ->
                result = TimePeriod.EVERY_SIX_DAYS;
            case "WEEKLY" ->
                result = TimePeriod.WEEKLY;
            case "BIWEEKLY" ->
                result = TimePeriod.BIWEEKLY;
            case "MONTHLY" ->
                result = TimePeriod.MONTHLY;
            case "BIMONTHLY" ->
                result = TimePeriod.BIMONTHLY;
            case "SEMESTRAL" ->
                result = TimePeriod.SEMESTRAL;
            case "QUARTERLY" ->
                result = TimePeriod.QUARTERLY;
            case "ANNUAL" ->
                result = TimePeriod.ANNUAL;
        }
        return result;
    }

    public List<Task> Tasks() {
        return tasks;
    }

    /**
     * The function "getTask" returns a Task object with a specific name from a list
     * of tasks.
     * 
     * @param name The name of the task that you want to retrieve.
     * @return The method is returning a Task object.
     */
    public Task getTask(String name) {
        Task result = null;
        for (Task task : tasks) {
            if (task.getName().equals(name)) {
                result = task;
            }
        }
        return result;
    }

    /**
     * The function removes all tasks that are the same as the given task from a
     * list of tasks and returns true if any tasks were removed.
     * 
     * @param task The parameter "task" is of type Task, which represents a task
     *             object.
     * @return The method is returning a boolean value. It returns true if there
     *         were tasks with the same properties as the given task that were
     *         successfully removed from the list of tasks, and false if no tasks
     *         were removed.
     */
    public boolean removeTaskRepeat(Task task) {
        List<Task> tasksSame = taskSame(task);
        for (Task task1 : tasksSame) {
            tasks.remove(task1);

        }
        return tasksSame.size() > 0;
    }

    /**
     * The function "taskSame" returns a list of tasks that are equal to a given
     * task.
     * 
     * @param task The parameter "task" is an object of type "Task".
     * @return The method is returning a List of Task objects.
     */
    public List<Task> taskSame(Task task) {
        List<Task> result = new ArrayList<>();
        for (Task task1 : tasks) {
            if (task1.getName().equals(task.getName()) && task1.getDescription().equals(task.getDescription())
                    && task1.getCategory().equals(task.getCategory())
                    && task1.getTimePeriod().equals(task.getTimePeriod()) && task1.getColor().equals(task.getColor())) {
                result.add(task1);
            }
        }
        return result;
    }

    /**
     * The function searches for tasks that contain any of the given word keys and
     * returns a list of matching tasks.
     * 
     * @param wordKeys The `wordKeys` parameter is a string that contains the
     *                 keywords to search for in the tasks. These keywords are
     *                 separated by spaces.
     * @return The method is returning a List of Task objects that match the search
     *         criteria specified by the wordKeys parameter.
     */
    public List<Task> searchTask(String wordKeys) {
        List<Task> result = new ArrayList<>();
        wordKeys = wordKeys.toLowerCase();
        String[] keys = wordKeys.split(" ");

        boolean found = false;
        for (int i = 0; i < tasks.size(); i++) {
            for (int j = 0; j < keys.length && !found; j++) {
                if (tasks.get(i).toString().toLowerCase().contains(keys[j])) {
                    result.add(tasks.get(i));
                    found = true;
                }
            }
            found = false;
        }
        return result;
    }

    /**
     * The function modifies the properties of a task object based on the provided
     * parameters and returns a string indicating the changes made.
     * 
     * @param name        The name of the task that you want to modify.
     * @param title       The new title for the task.
     * @param description The "description" parameter is a string that represents
     *                    the new description for the task.
     * @param category    The category parameter is a string that represents the
     *                    category of the task.
     * @param date        The "date" parameter is of type Calendar and represents
     *                    the new date for the task.
     * @param status      The "status" parameter represents the status of the task.
     *                    It can have values such as "in progress", or
     *                    "completed",etc.
     * @param color       The color parameter is a String that represents the color
     *                    of the task.
     * @return The method is returning a String that indicates the changes made to
     *         the task. If no changes were made, it returns "No changes were made".
     *         Otherwise, it returns a string that lists the specific changes that
     *         were made.
     */
    public String modifyTask(Task taskModify, String title, String description, String category, Calendar date,
            String status, String color) {
        String result = "";
        Task task = taskModify;
        if (title != null && !title.equals("") && !title.equals(task.getName())) {
            task.setName(title);
            result += "- Title modified\n";
        }
        if (description != null && !description.equals(task.getDescription())) {
            task.setDescription(description);
            result += "- Description modified\n";
        }
        if (category != null && !category.equals("") && !category.equals(task.getCategory()+"")) {
            task.setCategory(getCategory(category));
            result += "- Category modified\n";
        }
        if (date != null && !getDateAsString(date).equals(getDateAsString(task.getDate()))) {
            task.setDate(date);
            result += "- Date modified\n";
        }
        if (status != null && !status.equals("") && !status.equals(task.getStatus()+"")) {
            task.setStatus(getStatus(status));
            result += "- Status modified\n";
        }

        if (color != null && !color.equals("") && !color.equals(task.getColor())) {
            task.setColor(color);
            result += "- Color modified\n";
        }
        if (result.equals("")) {
            result = "No changes were made";
        } else {
            result = "The following changes were made:\n" + result;
        }
        tasks.remove(task);
        int insertionIndex = binarySearchInsertionIndex(tasks, task);
        tasks.add(insertionIndex, task);

        return result;
    }

    /**
     * The function modifies a given task and updates all other tasks with the same
     * name, description, category, and color.
     * 
     * @param taskModify  The task that needs to be modified.
     * @param title       The new title for the task.
     * @param description The "description" parameter is a string that represents
     *                    the new description for the task.
     * @param category    The category parameter is a string that represents the
     *                    category of the task. It is used to categorize tasks into
     *                    different groups or types.
     * @param date        The "date" parameter is a Calendar object representing the
     *                    new date for the task.
     * @param status      The "status" parameter is a string that represents the
     *                    status of the task. It could be values like "completed",
     *                    "in progress", etc.
     * @param color       The "color" parameter is a string that represents the
     *                    color of the task. It is used to modify the color of the
     *                    task in the modifyTasksPeriodics method.
     * @return The method is returning a String.
     */
    public String modifyTasksPeriodics(Task taskModify, String title, String description, String category,
            Calendar date, String status, String color) {
        String result = "";
        List<Task> tasksSame = taskSame(taskModify);
        tasksSame.remove(taskModify);
        result += modifyTask(taskModify, title, description, category, date, status, color);
        if (!result.equals("No changes were made")) {
            for (Task task : tasksSame) {
                task.setName(title);
                task.setDescription(description);
                task.setCategory(getCategory(category));
                task.setColor(color);
            }
            result =result+ "\n\n " + tasksSame.size() + " tasks were also modified ";
        }
        return result;
    }

    private String getDateAsString(Calendar date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
        String dateS = format.format(date.getTime());
        return dateS;
    }
}
