package com.example.tasktodo8d.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.example.tasktodo8d.model.Task;
import com.example.tasktodo8d.model.TaskCategory;
import com.example.tasktodo8d.model.TaskStatus;
import com.example.tasktodo8d.model.TimePeriod;

/**
 * The ControllerTasks class represents the controller for currentTasks in the
 * application.
 * It is responsible for managing and handling currentTasks.
 */
public class ControllerTasks {
    /**
     * The list of currentTasks .
     */
    private List<Task> currentTasks;
    private List<Task> allTasks;

    /**
     * This class represents the controller for currentTasks in the application.
     * It is responsible for managing and handling currentTasks.
     */
    private static ControllerTasks instance;

    private ControllerTasks() {
        currentTasks = new ArrayList<>();
        allTasks = new ArrayList<>();
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
        int insertionIndex = binarySearchInsertionIndex(currentTasks, task);
        currentTasks.add(insertionIndex, task);
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
     * The function performs a binary search on a sorted list of currentTasks to
     * find the
     * insertion index for a new task based on its date.
     * 
     * @param tasks   A list of Task objects, sorted in ascending order by date.
     * @param newTask The `newTask` parameter is an instance of the `Task` class.
     * @return The method is returning the index at which the newTask should be
     *         inserted into the currentTasks list in order to maintain the sorted
     *         order
     *         based on the date of the currentTasks.
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
        return currentTasks;
    }

    /**
     * The function "getTask" returns a Task object with a specific name from a list
     * of currentTasks.
     * 
     * @param name The name of the task that you want to retrieve.
     * @return The method is returning a Task object.
     */
    public Task getTask(String name) {
        Task result = null;
        for (Task task : currentTasks) {
            if (task.getName().equals(name)) {
                result = task;
            }
        }
        return result;
    }

    /**
     * The function removes all currentTasks that are the same as the given task
     * from a
     * list of currentTasks and returns true if any currentTasks were removed.
     * 
     * @param task The parameter "task" is of type Task, which represents a task
     *             object.
     * @return The method is returning a boolean value. It returns true if there
     *         were currentTasks with the same properties as the given task that
     *         were
     *         successfully removed from the list of currentTasks, and false if no
     *         currentTasks
     *         were removed.
     */
    public boolean removeTaskRepeat(Task task) {
        List<Task> tasksSame = taskSame(task);
        for (Task task1 : tasksSame) {
            removeTask(task1);
        }
        return tasksSame.size() == 0;
    }

    /**
     * The function "taskSame" returns a list of currentTasks that are equal to a
     * given
     * task.
     * 
     * @param task The parameter "task" is an object of type "Task".
     * @return The method is returning a List of Task objects.
     */
    public List<Task> taskSame(Task task) {
        List<Task> result = new ArrayList<>();
        for (Task task1 : currentTasks) {
            if (task1.getName().equals(task.getName()) && task1.getDescription().equals(task.getDescription())
                    && task1.getCategory().equals(task.getCategory())
                    && task1.getTimePeriod().equals(task.getTimePeriod()) && task1.getColor().equals(task.getColor())) {
                result.add(task1);
            }
        }
        return result;
    }

    /**
     * The function searches for currentTasks that contain any of the given word
     * keys and
     * returns a list of matching currentTasks.
     * 
     * @param wordKeys The `wordKeys` parameter is a string that contains the
     *                 keywords to search for in the currentTasks. These keywords
     *                 are
     *                 separated by spaces.
     * @return The method is returning a List of Task objects that match the search
     *         criteria specified by the wordKeys parameter.
     */
    public List<Task> searchTask(String wordKeys) {
        List<Task> result = new ArrayList<>();
        wordKeys = wordKeys.toLowerCase();
        String[] keys = wordKeys.split(" ");

        boolean found = false;
        for (int i = 0; i < currentTasks.size(); i++) {
            for (int j = 0; j < keys.length && !found; j++) {
                if (currentTasks.get(i).toString().toLowerCase().contains(keys[j])) {
                    result.add(currentTasks.get(i));
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
     * @param taskModify  The task that you want to modify.
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
        if (category != null && !category.equals("") && !category.equals(task.getCategory() + "")) {
            task.setCategory(getCategory(category));
            result += "- Category modified\n";
        }
        if (date != null && !getDateAsString(date).equals(getDateAsString(task.getDate()))) {
            task.setDate(date);
            result += "- Date modified\n";
        }
        if (status != null && !status.equals("") && !status.equals(task.getStatus() + "")) {
            task.setStatus(getStatus(status));
            result += "- Status modified\n";
            if(status.equals("COMPLETED"))
                allTasks.add(task);
            
        }

        if (color != null && !color.equals("") && !color.equals(task.getColor())) {
            task.setColor(color);
            result += "- Color modified\n";
        }
        if (result.equals("")) {
            result = "No changes were made";
        } else{
            result = "The following changes were made:\n" + result;
            currentTasks.remove(task);
            if(!status.equals("COMPLETED")) {
            int insertionIndex = binarySearchInsertionIndex(currentTasks, task);
            currentTasks.add(insertionIndex, task);
            }
        }

        return result;
    }

    /**
     * The function modifies a given task and updates all other currentTasks with
     * the same
     * name, description, category, and color.
     * 
     * @param taskModify  The task that needs to be modified.
     * @param title       The new title for the task.
     * @param description The "description" parameter is a string that represents
     *                    the new description for the task.
     * @param category    The category parameter is a string that represents the
     *                    category of the task. It is used to categorize
     *                    currentTasks into
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
            result = result + "\n\n " + tasksSame.size() + " currentTasks were also modified ";
        }
        return result;
    }

    /**
     * The function removes a task from both the currentTasks and allTasks lists and returns a boolean indicating whether the removal was successful.
     * 
     * @param task The "task" parameter is an object of type Task, which represents a task that needs to be removed.
     * @return The method is returning a boolean value, which indicates whether the task was successfully removed from the currentTasks list.
     */
    public boolean removeTask(Task task) {
        boolean result = false;
        result = currentTasks.remove(task); 
        allTasks.remove(task);
        return result;
    }

    /**
     * The function takes a Calendar object and returns a formatted string representation of the date and time.
     * 
     * @param date The parameter "date" is a Calendar object representing a specific date and time.
     * @return The method is returning a string representation of the provided Calendar date in the format "yyyy-MM-dd hh:mm a".
     */
    private String getDateAsString(Calendar date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
        String dateS = format.format(date.getTime());
        return dateS;
    }

    /**
     * The function `getTaskPeriod` returns a list of tasks based on the specified period (Today, This week, or This month).
     * 
     * @param period The "period" parameter is a string that specifies the time period for which you want to retrieve tasks. It can have three possible values: "Today", "This week", or "This month".
     * @return The method is returning a List of Task objects.
     */
    public List<Task> getTaskPeriod(String period) {
        List<Task> result = new ArrayList<>();
        Calendar date = Calendar.getInstance();
        if (period.equalsIgnoreCase("Today")) {
            for (Task task : currentTasks) {
                if (areOnSameDay(task.getDate(), date)) {
                    result.add(task);
                }
            }
        } else if (period.equalsIgnoreCase("This week")) {
            for (Task task : currentTasks) {
                if (task.getDate().after(date)
                        && task.getDate().get(Calendar.WEEK_OF_YEAR) == date.get(Calendar.WEEK_OF_YEAR)) {
                    result.add(task);
                }
            }
        } else if (period.equalsIgnoreCase("This month")) {
            for (Task task : currentTasks) {
                if (task.getDate().after(date) && task.getDate().get(Calendar.MONTH) == date.get(Calendar.MONTH)) {
                    result.add(task);
                }
            }
        }
        return result;
    }
    /**
     * The function "getTaskCategory" returns a list of tasks that belong to a specific category, based on the given category and type of task list.
     * 
     * @param category The category parameter is a string that represents the category of tasks you want to retrieve.
     * @param typeList The typeList parameter is a string that specifies the type of task list to retrieve. It can have two possible values: "currents" or "all".
     * @return The method is returning a List of Task objects that belong to the specified category.
     */
    public List<Task> getTaskCategory(String category,String typeList) {
        List<Task> result = new ArrayList<>();
        List<Task> storegeTask = new ArrayList<>();
        if(typeList.equalsIgnoreCase(StorageTask.CURRENTS+""))
            storegeTask=currentTasks;
        else if(typeList.equalsIgnoreCase(StorageTask.ALL+"")){
            storegeTask.addAll(allTasks);
            storegeTask.addAll(currentTasks);
        }
        for (Task task : storegeTask) {
            if (task.getCategory().toString().equalsIgnoreCase(category)) {
                result.add(task);
            }
        }
        return result;
    }
    /**
     * The function "getTaskStatus" returns a list of tasks with a specific status based on the given parameters.
     * 
     * @param status The "status" parameter is a string that represents the status of the tasks. It is used to filter the tasks based on their status.
     * @param typeList The parameter "typeList" is a string that specifies the type of task list to search for. It can have two possible values: "currents" or "all".
     * @return The method is returning a List<Task> containing tasks with a specific status.
     */
    public List<Task> getTaskStatus(String status,String typeList) {
        List<Task> result = new ArrayList<>();
         List<Task> storegeTask = new ArrayList<>();
        if(typeList.equalsIgnoreCase(StorageTask.CURRENTS+""))
            storegeTask=currentTasks;
        else if(typeList.equalsIgnoreCase(StorageTask.ALL+"")){
            storegeTask.addAll(allTasks);
            storegeTask.addAll(currentTasks);
        }
        for (Task task : storegeTask) {
            if (task.getStatus().toString().equalsIgnoreCase(status)) {
                result.add(task);
            }
        }
        return result;
    }

    /**
     * The function checks if two Calendar objects represent the same day.
     * 
     * @param date1 A Calendar object representing the first date.
     * @param date2 The above code defines a method named "areOnSameDay" that takes two Calendar objects as parameters: "date1" and "date2". The method checks if the two Calendar objects represent the same day by comparing their year, month, and day of the month values. If all three values
     * @return The method is returning a boolean value, which indicates whether the two given Calendar objects represent the same day.
     */
    private boolean areOnSameDay(Calendar date1, Calendar date2) {
        return date1.get(Calendar.YEAR) == date2.get(Calendar.YEAR) &&
                date1.get(Calendar.MONTH) == date2.get(Calendar.MONTH) &&
                date1.get(Calendar.DAY_OF_MONTH) == date2.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * The function returns a list of tasks that have an expiration date before a specified number of days from the current date.
     * 
     * @param days The "days" parameter represents the number of days from the current date that you want to check for task expiration.
     * @return The method is returning a List of Task objects.
     */
    public List<Task> getTaskByDayExpiration(int days){
        List<Task> result = new ArrayList<>();
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DAY_OF_MONTH, days);
        for (Task task : currentTasks) {
            if (task.getDate().before(date)) {
                result.add(task);
            }
        }
        return result;
    }


    /**
     * The getAllTasks() function returns a list of all tasks, including both allTasks and currentTasks.
     * 
     * @return The method is returning a list of tasks.
     */
    public List<Task> getAllTasks(){
        List<Task> result=new ArrayList<>();
        result.addAll(allTasks);
        result.addAll(currentTasks);
        
        return result;
    }

    /**
     * The saveData() function saves the data of the current object using the FileManager class.
     */
    public void saveData() {
        FileManager.getInstance().saveData(this);
    }

    /**
     * The function loads data from a file and updates the task list.
     */
    public void loadData() {
        ControllerTasks controllerTasks = FileManager.getInstance().loadData();
        if (controllerTasks != null) {
            currentTasks = controllerTasks.currentTasks;
            allTasks = controllerTasks.allTasks;
        }
        updateTask();
    }

    /**
     * The function updates the status of tasks that are past their due date and moves them from the currentTasks list to the allTasks list.
     */
    private void updateTask(){
        List<Task> taskToChange=new ArrayList<>();
        for(Task task:currentTasks){
            if(task.getDate().before(Calendar.getInstance()) && task.getStatus()!=TaskStatus.COMPLETED){
                task.setStatus(TaskStatus.NO_COMPLETED);
                allTasks.add(task);
                taskToChange.add(task);
            }
        }
        for(Task task:taskToChange){
            currentTasks.remove(task);
        }
    }





}
