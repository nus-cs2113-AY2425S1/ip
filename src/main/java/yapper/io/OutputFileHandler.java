package yapper.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import yapper.exceptions.YapperException;
import yapper.tasks.Task;

/**
 * File Data Saving Manager for Yapper.
 *
 * <p>
 * This class handles the storage, deletion, and modification of tasks
 * in a file. It provides methods for adding tasks, removing tasks,
 * and updating task statuses.
 * </p>
 *
 */
public class OutputFileHandler {

    /**
     * Stores an added task to the file.
     *
     * @param task             the task to be saved
     * @throws YapperException if an error occurs during file operations
     */
    public static void storeAddedTask(Task task) throws YapperException {
        try {
            FileWriter fileWriter = new FileWriter(StringStorage.SAVE_FILE_PATH, true);
            fileWriter.write(task.taskToString() + "\n");
            fileWriter.close();
        } catch (IOException e) {
            throw new YapperException(
                    StringStorage.SAVING_ERROR_MESSAGE
                    + "error occurred when saving task to file: "
                    + e.getMessage());
        }
    }

    /**
     * Removes a deleted task from the file.
     *
     * @param taskOrdinal      the ordinal of the task to be deleted
     * @throws YapperException if an error occurs during file operations
     */
    public static void unstoreDeletedTask(int taskOrdinal) throws YapperException {
        try {
            File file = new File(StringStorage.SAVE_FILE_PATH);
            // Convert Entire File to Array for easier search of taskLine
            Scanner scanner = new Scanner(file);
            ArrayList<String> taskLines = new ArrayList<>();
            while (scanner.hasNextLine()) {
                taskLines.add(scanner.nextLine());
            }
            scanner.close();

            taskLines.remove(taskOrdinal);

            FileWriter fileWriter = new FileWriter(StringStorage.SAVE_FILE_PATH);
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

    /**
     * Amends the status of a task in the file.
     *
     * @param task             the task with the updated status
     * @param taskOrdinal      the ordinal of the task to be amended
     * @throws YapperException if an error occurs during file operations
     */
    public static void amendTaskStatus(Task task, int taskOrdinal) throws YapperException {
        try {
            File file = new File(StringStorage.SAVE_FILE_PATH);

            Scanner scanner = new Scanner(file);
            ArrayList<String> taskLines = new ArrayList<>();
            while (scanner.hasNextLine()) {
                taskLines.add(scanner.nextLine());
            }
            scanner.close();

            taskLines.set(taskOrdinal, task.taskToString());

            FileWriter fileWriter = new FileWriter(StringStorage.SAVE_FILE_PATH);
            for (String taskLine : taskLines) {
                fileWriter.write(taskLine + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new YapperException(
                    StringStorage.SAVING_ERROR_MESSAGE
                    + "error occurred when amending task status in file: "
                    + e.getMessage());
        }
    }
}
