package yapper.tasks;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

import yapper.io.OutputStringHandler;
import yapper.io.StringStorage;

// manages Tasks for Yapper
public class TaskHandler {
    private Task[] tasks;
    private int currTaskTotal;
    public static final String SAVE_FILE_PATH = "./data/duke.txt"

    // Constructor and Getters
    public TaskHandler(int maxCapacity) {
        tasks = new Task[maxCapacity];
        currTaskTotal = 0;
    }
    public Task[] getAllTasks() {
        return tasks;
    }
    public int getCurrTaskTotal() {
        return currTaskTotal;
    }
//    public Task getTask(int taskOrdinal) {
//        return tasks[taskOrdinal];
//    }

    // TaskManager Operations
    public void addTask(Task task) {
        tasks[currTaskTotal] = task;
        currTaskTotal++;
        task.printAddedTask(currTaskTotal);
    }
    public void updateTaskStatus(int taskOrdinal, boolean isDone) {
        Task task = tasks[taskOrdinal];

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


    public void checkIfFileExists() {
        // create file if it doesn't exist
        File file = new File("./data");
        if (!file.exists()) {
            file.mkdirs();
        }
    }
    public void storeTasksData(Task[] tasks) throws Exception {
        try {
            FileWriter fileWriter = new FileWriter("./data/duke.txt");
            for (Task task : tasks) {
                fileWriter.write(task.taskToString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("error during saving: " + e.getMessage()); // ?
        }
        System.out.println("saved successfully!");
    }
    public void loadTasksData(Task[] tasks) throws Exception {
        System.out.println("retrieving data from: " + SAVE_FILE_PATH);
        try {
            File file = new File(SAVE_FILE_PATH);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String taskData = scanner.nextLine();
                Task task = stringToTask(taskData);
                if (task != null) {
                    addTask(task);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Starting with an empty task list.");
        } catch (Exception e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        System.out.println("retrieved successfully!");
    }
    private Task stringToTask(String taskData) {
        try {
            String[] parts = taskData.split(" \\| ", 1);
            String taskType = parts[0];
            String taskArgs = parts[1];
            switch (taskType) {
                case "T":
                    return Todo.stringToTask(taskType, taskArgs);
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
