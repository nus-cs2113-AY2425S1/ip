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
    public static final String SAVE_FILE_PATH = "./data/savedata.txt";

    // File Data Saving Operations
    public static void storeAddedTask(Task task) throws YapperException {
        try {
            FileWriter fileWriter = new FileWriter(SAVE_FILE_PATH, true);
            fileWriter.write(task.taskToString() + "\n");
            fileWriter.close();
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
            taskLines.remove(taskOrdinal);
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
            taskLines.set(taskOrdinal, task.taskToString());
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
        File file = null;
        try {
            file = new File(SAVE_FILE_PATH);
            if (!file.exists()) {
                System.out.println("File not found. Starting with an empty task list.");
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(" ");
        }

        int invalidTaskCount = 0;
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String taskData = scanner.nextLine();
                if (taskData.isEmpty()) {
                    break; // end scanning once blank line is detected
                }

                try {
                    Task task = loadTask(taskData);
                    taskHandler.addTask(task);
                } catch (YapperException e) {
                    System.out.println("skipping invalid task: " + e.getMessage());
                    invalidTaskCount++;
                }
            }
            scanner.close();

            if (invalidTaskCount > 0) {
                System.out.println("total invalid tasks found: " + invalidTaskCount); // ?
            } else {
                System.out.println("no invalid tasks detected in file");
            }
            System.out.println(StringStorage.LINE_DIVIDER);
        } catch (FileNotFoundException e) {
//            System.out.println(StringStorage.LOADING_ERROR_MESSAGE);
        }
        return taskHandler;
    }
    private static Task loadTask(String taskData) throws YapperException {
        try {
            String[] taskParts = StringStorage.splitByDelimiter(taskData);
            if (taskParts.length < 3) {
                throw new YapperException("invalid task format, missing fields");
            }
            // Check Task Type, indirectly checks if missing
            String taskType = taskParts[0];
            ExceptionHandler.checkIfTaskTypeValid(taskType);
            // Check Task Status, indirectly checks if missing
            String taskStatus = taskParts[1];
            ExceptionHandler.checkIfTaskStatusValid(taskStatus);
            boolean isDone = taskStatus.equals(StringStorage.IS_DONE_SYMBOL);
            // Check Desc and other Arguments, indirectly checks if missing
            String remainingParts = taskParts[2];
            switch (taskType) {
                case StringStorage.TODO_SYMBOL:
                    // no need to split
                    ExceptionHandler.checkIfTodoArgsMissing(
                            remainingParts.trim() );
                    return new Todo(remainingParts, isDone);
                case StringStorage.DEADLINE_SYMBOL:
                    String[] deadlineArgs = splitStringByDeadlineKeyword(remainingParts);
                    ExceptionHandler.checkIfDeadlineArgsMissing(
                            deadlineArgs[0], deadlineArgs[1] );
                    return new Deadline(deadlineArgs[0], isDone, deadlineArgs[1] );
                case StringStorage.EVENT_SYMBOL:
                    String[] eventArgs = splitStringByEventKeywords(remainingParts);
                    ExceptionHandler.checkIfEventArgsMissing(
                            eventArgs[0], eventArgs[1], eventArgs[2] );
                    return new Event(eventArgs[0], isDone, eventArgs[1], eventArgs[2]);
                default:
                    throw new YapperException("loadTask method reached default switch-case");
            }
        } catch (YapperException e) {
            throw new YapperException(taskData + ", because " + e.getMessage()); // ?
        }
    }

    // User-Input-related Methods to Split by delimiter into keywords
    public static String[] splitStringByDeadlineKeyword(String instructionArgs) {
        String[] deadlineArgs = instructionArgs.split(
                StringStorage.SPLIT_USING_DELIMITER, -2);
        String deadlineDesc = deadlineArgs[0].trim();
        String deadlineDate = deadlineArgs[1].trim();
        return new String[] {deadlineDesc, deadlineDate};
    }
    public static String[] splitStringByEventKeywords(String instructionArgs) {
        String[] eventArgs = instructionArgs.split(
                StringStorage.SPLIT_USING_DELIMITER, -2);
        String eventDesc = eventArgs[0].trim();
        String[] dates = eventArgs[1].split(
                StringStorage.SPLIT_USING_DELIMITER, -2);
        String startDate = dates[0].trim();
        String endDate = dates[1].trim();
        return new String[] {eventDesc, startDate, endDate};
    }

}
