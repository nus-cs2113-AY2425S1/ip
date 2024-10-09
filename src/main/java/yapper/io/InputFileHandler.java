package yapper.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import yapper.exceptions.ExceptionHandler;
import yapper.exceptions.YapperException;
import yapper.tasks.Deadline;
import yapper.tasks.Event;
import yapper.tasks.Task;
import yapper.tasks.TaskHandler;
import yapper.tasks.Todo;

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
        System.out.println("Searching for a save file ... ");
        File file = null;
        try {
            file = new File(StringStorage.SAVE_FILE_PATH);
            if (!file.exists()) {
                System.out.println("No save file has been found. Starting with an empty task list. ");
                file.getParentFile().mkdirs();
                file.createNewFile();
            } else {
                System.out.println("A save file has been found. Initialising the task list ... ");
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }

        int invalidTaskCount = 0;
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

            if (invalidTaskCount > 0) {
                System.out.println("There were " + invalidTaskCount + " invalid tasks detected in the save file. ");
            } else {
                System.out.println("No invalid tasks were detected in the save file. ");
            }
            System.out.println(StringStorage.LINE_DIVIDER);
        } catch (FileNotFoundException e) {
            System.out.println("There is a FileNotFoundException: " + e.getMessage());
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

            String taskType = taskParts[0].trim();
            ExceptionHandler.checkIfTaskTypeValid(taskType);

            String taskStatus = taskParts[1].trim();
            ExceptionHandler.checkIfTaskStatusValid(taskStatus);
            boolean isDone = taskStatus.equals(StringStorage.SYMBOL_IS_DONE);

            String remainingParts = taskParts[2].trim();
            switch (taskType) {
            case StringStorage.SYMBOL_TODO:
                ExceptionHandler.checkIfTodoArgsMissing(
                        remainingParts.trim());
                return new Todo(remainingParts, isDone);
            case StringStorage.SYMBOL_DEADLINE:
                String[] deadlineArgs = splitStringByDeadlineKeyword(remainingParts);
                ExceptionHandler.checkIfDeadlineArgsMissing(
                        deadlineArgs[0], deadlineArgs[1]);
                return new Deadline(deadlineArgs[0], isDone, deadlineArgs[1]);
            case StringStorage.SYMBOL_EVENT:
                String[] eventArgs = splitStringByEventKeywords(remainingParts);
                ExceptionHandler.checkIfEventArgsMissing(
                        eventArgs[0], eventArgs[1], eventArgs[2]);
                return new Event(eventArgs[0], isDone, eventArgs[1], eventArgs[2]);
            default:
                throw new YapperException("Yapper does not know how to load this task. ");
            }
        } catch (YapperException e) {
            throw new YapperException(taskData + ", because " + e.getMessage());
        }
    }


    /**
     * Splits the given instruction arguments by the deadline keyword delimiter.
     *
     * @param instructionArgs the instruction arguments to split
     * @return an array containing the description and deadline date
     */
    private static String[] splitStringByDeadlineKeyword(String instructionArgs) {
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
    private static String[] splitStringByEventKeywords(String instructionArgs) {
        String[] eventArgs = instructionArgs.split(
                StringStorage.SPLIT_USING_DELIMITER, -3);
        String eventDesc = eventArgs[0].trim();
        String startDate = eventArgs[1].trim();
        String endDate = eventArgs[2].trim();
        return new String[] {eventDesc, startDate, endDate};
    }

}
