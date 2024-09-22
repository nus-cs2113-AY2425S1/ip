package yapper.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import yapper.exceptions.ExceptionHandler;
import yapper.exceptions.YapperException;
import yapper.tasks.*; // need all classes in tasks folder

public class SaveFileHandler {
    public static final String SAVE_FILE_PATH = "./data/duke.txt";

    // File Data Saving Operations
    public static void storeAddedTask(Task task) throws YapperException {
        try {
            System.out.println(StringStorage.BEFORE_SAVING_STRING + SAVE_FILE_PATH);
            FileWriter fileWriter = new FileWriter(SAVE_FILE_PATH, true);
            fileWriter.write(task.taskToString() + "\n");
            fileWriter.close();
            System.out.println(StringStorage.AFTER_SAVING_STRING);
        } catch (IOException e) {
            throw new YapperException("error occured when saving task to file: " + e.getMessage()); // ?
        }
    }
    public static void unstoreDeletedTask(int taskOrdinal) throws YapperException {
        try {
            File file = new File(SAVE_FILE_PATH);
            // Convert Entire File to Array for easier search of taskLine
            Scanner scanner = new Scanner(file);
            ArrayList<String> taskLines = new ArrayList<>();
            while (scanner.hasNextLine()) {
                taskLines.add(scanner.nextLine());
            }
            scanner.close();
            // Remove Task at Ordinal
            taskLines.remove(taskOrdinal - StringStorage.INDEX_OFFSET);
            // Rewrite File without Deleted Task
            FileWriter fileWriter = new FileWriter(SAVE_FILE_PATH);
            for (String taskLine : taskLines) {
                fileWriter.write(taskLine + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new YapperException("error occurred when deleting task from file: " + e.getMessage());
        }
    }
    public static void amendTaskStatus(Task task, int taskOrdinal) throws YapperException {
        try {
            File file = new File(SAVE_FILE_PATH);
            // Convert Entire File to Array for easier search of taskLine
            Scanner scanner = new Scanner(file);
            ArrayList<String> taskLines = new ArrayList<>();
            while (scanner.hasNextLine()) {
                taskLines.add(scanner.nextLine());
            }
            scanner.close();
            // Update Task at Ordinal
            taskLines.set(taskOrdinal - StringStorage.INDEX_OFFSET, task.taskToString());
            // Rewrite File without Deleted Task
            FileWriter fileWriter = new FileWriter(SAVE_FILE_PATH);
            for (String taskLine : taskLines) {
                fileWriter.write(taskLine + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new YapperException("error occurred when changing task status: " + e.getMessage());
        }
    }

    public static TaskHandler loadTasks() {
        TaskHandler taskHandler = new TaskHandler();
        File file = new File(SAVE_FILE_PATH);
//        System.out.println(StringStorage.BEFORE_LOADING_STRING + SAVE_FILE_PATH);
        if (!file.exists()) {
            System.out.println("no saved tasks found. start with empty task list"); //
            return taskHandler; // guard clause for if file doesn't exist
        }
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String taskData = scanner.nextLine();
                Task task = loadTask(taskData);
                if (task != null) {
                    taskHandler.addTask(task);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Starting with an empty task list."); // ?
        } catch (Exception e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
//        System.out.println(StringStorage.AFTER_LOADING_STRING);
        return taskHandler;
    }
    private static Task loadTask(String taskData) {
        try {
            String[] taskParts = StringStorage.splitByDelimiter(taskData);

            String taskType = taskParts[0];
            try {
                ExceptionHandler.checkIfTaskTypeValid(taskType); // indirectly checks if missing
            } catch (YapperException e) {
                StringStorage.printWithDividers(e.getMessage());
                return null;
            }

            String taskStatus = taskParts[1];
            try {
                ExceptionHandler.checkIfTaskStatusValid(taskStatus); // indirectly checks if missing
            } catch (YapperException e) {
                StringStorage.printWithDividers(e.getMessage());
                return null;
            }
            boolean isDone = taskStatus.equals(StringStorage.NOT_DONE_SYMBOL);

            String taskDesc = taskParts[2];
            switch (taskType) {
                case StringStorage.TODO_SYMBOL:
                    InputStringHandler.validateTodoInstruction(taskDesc);
                    return new Todo(taskDesc, isDone);
                case StringStorage.DEADLINE_SYMBOL:
                    InputStringHandler.validateDeadlineInstruction(taskDesc, taskParts[3]);
                    return new Deadline(taskDesc, isDone, taskParts[3]);
                case StringStorage.EVENT_SYMBOL:
                    InputStringHandler.validateEventInstruction(taskDesc, taskParts[3], taskParts[4]);
                    return new Event(taskDesc, isDone, taskParts[3], taskParts[4]);
            }
        } catch (Exception e) {
            System.out.println("data unrecognised, skipping line: " + taskData); // ?
            return null;
        }
        return null; // ?
    }

}
