package yapper.tasks;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;

import yapper.io.OutputStringHandler;
import yapper.io.StringStorage;

// manages Tasks for Yapper
public class TaskHandler {
    public static final String SAVE_FILE_PATH = "./data/duke.txt";
    private List<Task> tasks;

    public TaskHandler() {
        tasks = new ArrayList<>();
    }
    // Getters
    public List<Task> getAllTasks() {
        return tasks;
    }
    public int getCurrTaskTotal() {
        return tasks.size();
    }
    public Task getTask(int taskOrdinal) {
        return tasks.get(taskOrdinal);
    }

    // Create, Update, Delete Operations
    public void addTask(Task task) {
        tasks.add(task);
        task.printAddedTask(tasks.size());
    }
    public void deleteTask(int taskOrdinal) {
        Task task = tasks.remove(taskOrdinal);
        task.printDeletedTask(tasks.size());
    }
    public void updateTaskStatus(int taskOrdinal, boolean isDone) {
        Task task = tasks.get(taskOrdinal);

        boolean isAlreadyInDesiredState =
                ( isDone && task.isDone() ) || ( !isDone && !task.isDone() );
        if (isAlreadyInDesiredState) {
            StringStorage.printWithDividers("This task is already " +
                    (isDone ? "" : "not") + " marked as done" );
            return;
        }

        task.setDoneStatus(isDone);
        OutputStringHandler.printTaskStatus(task, isDone);
    }

    //
    public void storeTasksData(List<Task> tasks) {
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
    public void loadTasksData(List<Task> tasks) {
        System.out.println(StringStorage.BEFORE_LOADING_STRING + SAVE_FILE_PATH);
        try {
            File file = new File(SAVE_FILE_PATH);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String taskData = scanner.nextLine();
                Task task = stringToTasks(taskData);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Starting with an empty task list.");
        } catch (Exception e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        System.out.println(StringStorage.AFTER_LOADING_STRING);
    }
    private Task stringToTasks(String taskData) {
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
