package yapper.io;

import yapper.tasks.Deadline;
import yapper.tasks.Event;
import yapper.tasks.Task;
import yapper.tasks.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class SaveFileHandler {
    public static final String SAVE_FILE_PATH = "./data/duke.txt";

    public void checkIfFileExists() {
        // create file if it doesn't exist
        File file = new File("./data");
        if (!file.exists()) {
            file.mkdirs();
        }
    }
    public static void storeTasksData(List<Task> tasks) {
        System.out.println(StringStorage.BEFORE_SAVING_STRING + SAVE_FILE_PATH);
        try {
            FileWriter fileWriter = new FileWriter(SAVE_FILE_PATH);
            for (Task task : tasks) {
                fileWriter.write(task.taskToString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("error during saving: " + e.getMessage()); // ?
        }
        System.out.println(StringStorage.AFTER_SAVING_STRING);
    }
    public static void loadTasksData() {
        System.out.println(StringStorage.BEFORE_LOADING_STRING + SAVE_FILE_PATH);
        try {
            File file = new File(SAVE_FILE_PATH);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String taskData = scanner.nextLine();
                Task task = stringToTasks(taskData);
//                if (task != null) {
//                    addTask(task);
//                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Starting with an empty task list.");
        } catch (Exception e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        System.out.println(StringStorage.AFTER_LOADING_STRING);
    }
    private static Task stringToTasks(String taskData) {
        try {
            String[] parts = taskData.split(" \\| ", 1);
            String taskType = parts[0];
//            String taskArgs = parts[1];
            switch (taskType) {
                case "T":
                    return Todo.stringToTask(taskData);
                case "D":
                    return Deadline.stringToTask(taskData);
                case "E":
                    return Event.stringToTask(taskData);
            }
        } catch (Exception e) {
            System.out.println("Corrupted data detected. Skipping line: " + taskData);
        }
    }

}
