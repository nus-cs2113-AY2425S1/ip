package yapper.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import yapper.exceptions.YapperException;
import yapper.tasks.Task;
import yapper.tasks.TaskHandler;

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
     * Converts a list of tasks to strings and writes them to the file.
     *
     * @throws YapperException if an error occurs during file operations
     */
    public static void storeAllTasks(TaskHandler taskHandler) throws YapperException {
        try {
            convertArrayListToFile(taskHandler.tasksToString());
        } catch (IOException e) {
            throw new YapperException(
                    StringStorage.SAVING_ERROR_MESSAGE
                            + "error occurred when saving tasks to file: \n"
                            + e.getMessage());
        }
    }

    /**
     * Reads the content of a file line by line and
     * stores each line in an {@code ArrayList<String>}.
     *
     * @param file the {@code File} object representing the file to be read
     * @return an {@code ArrayList<String>} containing the lines of the file
     * @throws FileNotFoundException if the specified file does not exist or cannot be opened
     */
    private static ArrayList<String> convertFileToArrayList(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        ArrayList<String> taskLines = new ArrayList<>();
        while (scanner.hasNextLine()) {
            taskLines.add(scanner.nextLine());
        }
        scanner.close();
        return taskLines;
    }
    /**
     * Writes the contents of an {@code ArrayList<String>} to a file,
     * with each element written as a new line in the file.
     *
     * @param taskLines the {@code ArrayList<String>} containing the lines to be written to the file
     * @throws IOException if an I/O error occurs while writing to the file
     */
    private static void convertArrayListToFile(ArrayList<String> taskLines) throws IOException {
        FileWriter fileWriter = new FileWriter(StringStorage.SAVE_FILE_PATH);
        for (String taskLine : taskLines) {
            fileWriter.write(taskLine + "\n");
        }
        fileWriter.close();
    }


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
        } catch (FileNotFoundException e) {
            throw new YapperException(
                    StringStorage.FILE_NOT_FOUND_ERROR_MESSAGE
                    + ", when adding task to file: \n"
                    + e.getMessage());
        } catch (IOException e) {
            throw new YapperException(
                    StringStorage.SAVING_ERROR_MESSAGE
                    + ", when adding task to file: \n"
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
            ArrayList<String> taskLines = convertFileToArrayList(file);
            taskLines.remove(taskOrdinal);
            convertArrayListToFile(taskLines);
        } catch (FileNotFoundException e) {
            throw new YapperException(
                    StringStorage.FILE_NOT_FOUND_ERROR_MESSAGE
                    + ", when deleting task from file: \n"
                    + e.getMessage());
        } catch (IOException e) {
            throw new YapperException(
                    StringStorage.SAVING_ERROR_MESSAGE
                    + ", when deleting task from file: \n"
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
            ArrayList<String> taskLines = convertFileToArrayList(file);
            taskLines.set(taskOrdinal, task.taskToString());
            convertArrayListToFile(taskLines);
        } catch (FileNotFoundException e) {
            throw new YapperException(
                    StringStorage.FILE_NOT_FOUND_ERROR_MESSAGE
                    + ", when amending task status in file: \n"
                    + e.getMessage());
        } catch (IOException e) {
            throw new YapperException(
                    StringStorage.SAVING_ERROR_MESSAGE
                    + ", when amending task status in file: \n"
                    + e.getMessage());
        }
    }
}
