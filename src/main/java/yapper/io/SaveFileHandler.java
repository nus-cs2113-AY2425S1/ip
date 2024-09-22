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
            throw new YapperException(
                    StringStorage.SAVING_ERROR_MESSAGE +
                    "error occurred when saving task to file: "
                    + e.getMessage()); // ?
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
            throw new YapperException(
                    StringStorage.SAVING_ERROR_MESSAGE
                    + "error occurred when deleting task from file: "
                    + e.getMessage());
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
            throw new YapperException(
                    StringStorage.SAVING_ERROR_MESSAGE +
                    "error occurred when amending task status in file: "
                    + e.getMessage());
        }
    }

    // File Data Retrieval Operations
    public static TaskHandler loadTasks() {
        TaskHandler taskHandler = new TaskHandler();
        File file = new File(SAVE_FILE_PATH);
        int invalidTaskCount = 0;
        System.out.println(StringStorage.BEFORE_LOADING_STRING + SAVE_FILE_PATH);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String taskData = scanner.nextLine();
                try {
                    Task task = loadTask(taskData);
                    taskHandler.addTask(task);
                } catch (YapperException e) {
                    System.out.println("skipping invalid task: " + e.getMessage());
                    invalidTaskCount++;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println(StringStorage.LOADING_ERROR_MESSAGE);
            System.out.println("File not found. Starting with an empty task list."); // ?
        }

        if (invalidTaskCount > 0) {
            System.out.println("total invalid tasks found: " + invalidTaskCount); // ?
        } else {
            System.out.println("no invalid tasks detected in file");;
        }
        System.out.println(StringStorage.AFTER_LOADING_STRING);
        System.out.println(StringStorage.LINE_DIVIDER);
        return taskHandler;
    }
    private static Task loadTask(String taskData) throws YapperException {
        try {
            String[] taskParts = StringStorage.splitByDelimiter(taskData);
            // Check Task Type, indirectly checks if missing
            String taskType = taskParts[0];
            ExceptionHandler.checkIfTaskTypeValid(taskType);
            // Check Task Status, indirectly checks if missing
            String taskStatus = taskParts[1];
            ExceptionHandler.checkIfTaskStatusValid(taskStatus);
            boolean isDone = taskStatus.equals(StringStorage.IS_DONE_SYMBOL);
            // Check Desc and other Arguments, indirectly checks if missing
            String taskDesc = taskParts[2];
            switch (taskType) {
                case StringStorage.TODO_SYMBOL:
                    ExceptionHandler.checkIfTodoArgsMissing(
                            taskDesc.trim() );
                    return new Todo(taskDesc, isDone);
                case StringStorage.DEADLINE_SYMBOL:
                    ExceptionHandler.checkIfDeadlineArgsMissing(
                            taskDesc.trim(), taskParts[3].trim() );
                    return new Deadline(taskDesc, isDone, taskParts[3]);
                case StringStorage.EVENT_SYMBOL:
                    ExceptionHandler.checkIfEventArgsMissing(
                            taskDesc.trim(), taskParts[3].trim(), taskParts[4].trim() );
                    return new Event(taskDesc, isDone, taskParts[3], taskParts[4]);
            }
        } catch (YapperException e) {
            throw new YapperException(taskData + " because " + e.getMessage()); // ?
        }
        return null; // what to do with return statement?
    }

}
