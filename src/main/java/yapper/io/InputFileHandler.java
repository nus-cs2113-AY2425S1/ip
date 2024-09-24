package yapper.io;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

import yapper.exceptions.ExceptionHandler;
import yapper.exceptions.YapperException;
import yapper.tasks.*; // need all classes in tasks folder

/**
 * File Data Retrieval Manager for Yapper.
 *
 * <p>
 * This class is responsible for loading tasks from a file and creating
 * corresponding Task objects. It provides methods for reading tasks
 * from a specified file, handling invalid tasks, and returning a
 * TaskHandler containing all valid tasks.
 * </p>
 *
 */
public class InputFileHandler {

    /**
     * Retrieves tasks from file
     *
     * Loads tasks from the file and returns a TaskHandler containing
     * the tasks. If the file does not exist, an empty task list is
     * initialized.
     *
     * @return a TaskHandler with loaded tasks
     */
    public static TaskHandler loadTasks() {
        TaskHandler taskHandler = new TaskHandler();
        File file = null;
        try {
            file = new File(StringStorage.SAVE_FILE_PATH);
            if (!file.exists()) {
                System.out.println("File not found. Starting with an empty task list.");
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
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
            System.out.println("FileNotFoundException: " + e.getMessage());
        }
        return taskHandler;
    }

    /**
     * Loads a task from the provided task data string.
     *
     * @param taskData the string representing the task data
     * @return a Task object created from the task data
     * @throws YapperException if the task data is invalid
     */
    private static Task loadTask(String taskData) throws YapperException {
        try {
            String[] taskParts = StringStorage.splitByDelimiter(taskData);
            if (taskParts.length < 3) {
                throw new YapperException("invalid task format, missing fields");
            }
            // Check Task Type, indirectly checks if missing
            String taskType = taskParts[0].trim();
            ExceptionHandler.checkIfTaskTypeValid(taskType);
            // Check Task Status, indirectly checks if missing
            String taskStatus = taskParts[1].trim();
            ExceptionHandler.checkIfTaskStatusValid(taskStatus);
            boolean isDone = taskStatus.equals(StringStorage.IS_DONE_SYMBOL);
            // Check Desc and other Arguments, indirectly checks if missing
            String remainingParts = taskParts[2].trim();
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
    /**
     * Splits the given instruction arguments by the deadline keyword delimiter.
     *
     * @param instructionArgs the instruction arguments to split
     * @return an array containing the description and deadline date
     */
    public static String[] splitStringByDeadlineKeyword(String instructionArgs) {
        String[] deadlineArgs = instructionArgs.split(
                StringStorage.SPLIT_USING_DELIMITER, -2);
        String deadlineDesc = deadlineArgs[0].trim();
        String deadlineDate = deadlineArgs[1].trim();
        return new String[] {deadlineDesc, deadlineDate};
    }
    /**
     * Splits the given instruction arguments by the event keywords delimiter.
     *
     * @param instructionArgs the instruction arguments to split
     * @return an array containing the event description, start date, and end date
     */
    public static String[] splitStringByEventKeywords(String instructionArgs) {
        String[] eventArgs = instructionArgs.split(
                StringStorage.SPLIT_USING_DELIMITER, -3);
        String eventDesc = eventArgs[0].trim();
        String startDate = eventArgs[1].trim();
        String endDate = eventArgs[2].trim();
        return new String[] {eventDesc, startDate, endDate};
    }

}
