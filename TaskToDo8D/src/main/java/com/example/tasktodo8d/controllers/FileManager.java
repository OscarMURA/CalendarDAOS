package com.example.tasktodo8d.controllers;
import com.google.gson.Gson;


import java.io.*;

public class FileManager {

    private static FileManager instance;
    private File dataFolder;
    private File dataTasks;
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