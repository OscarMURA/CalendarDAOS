package com.example.tasktodo8d.controllers;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.google.gson.Gson;

/**
 * The FileManager class is responsible for managing the file operations in the application.
 * It provides methods to access and manipulate files and directories.
 */
public class FileManager {

    private static FileManager instance;
    /**
     * Represents a file in the file system.
     */
    private File dataFolder;
    /**
     * Represents a file used for storing data tasks.
     */
    private File dataTasks;
    /**
     * The FileManager class is responsible for managing the file operations in the application.
     * It provides methods to access and manipulate files and directories.
     */
    private FileManager() {
        File projectDir = new File(System.getProperty("user.dir"));
        dataFolder = new File(projectDir + "/data");
        dataTasks = new File(dataFolder + "/studentsData.json");
    }
    /**
     * The getInstance() function returns the instance of the FileManager class, creating it if it
     * doesn't already exist.
     *
     * @return The `getInstance()` method is returning an instance of the `FileManager` class.
     */
    public static FileManager getInstance() {
        if (instance == null) {
            instance = new FileManager();
        }
        return instance;
    }

    /**
     * The function creates necessary directories and files if they do not already exist.
     */
    public void createResources() throws IOException {
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
            if (!dataTasks.exists()) {
                dataTasks.createNewFile();
            }
        }
    }

    /**
     * The function saves the data from the ControllerTasks object to a file using Gson library in Java.
     * 
     * @param controllerTasks The parameter `controllerTasks` is an object of type `ControllerTasks`.
     * @return The method is returning a String. If the data is saved successfully, it will return "Data saved successfully". If there is an error while saving the data, it will return "Error saving data".
     */
    public String saveData(ControllerTasks controllerTasks) {
        try {
            createResources();
            Gson gson = new Gson();
            FileOutputStream fos = new FileOutputStream(dataTasks);
            String json = gson.toJson(controllerTasks);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
            writer.write(json);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            return "Error saving data";
        }
        return "Data saved successfully";
    }

    /**
     * The function loads data from a file and returns a ControllerTasks object.
     * 
     * @return The method is returning an instance of the ControllerTasks class.
     */
    public ControllerTasks loadData()  {
        ControllerTasks controllerTasks;
        try {
            createResources();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Gson gson = new Gson();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(dataTasks));
            controllerTasks = gson.fromJson(br, ControllerTasks.class);
        } catch (FileNotFoundException e) {
            controllerTasks=ControllerTasks.getInstance();
        }
        return controllerTasks;
    }
}