package yapper.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import yapper.exceptions.YapperException;
import yapper.tasks.Task;

// File Data Saving Operations
public class InputFileHandler {

    public static void storeAddedTask(Task task) throws YapperException {
        try {
            FileWriter fileWriter = new FileWriter(StringStorage.SAVE_FILE_PATH, true);
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
            File file = new File(StringStorage.SAVE_FILE_PATH);
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

    public static void amendTaskStatus(Task task, int taskOrdinal) throws YapperException {
        try {
            File file = new File(StringStorage.SAVE_FILE_PATH);
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
            FileWriter fileWriter = new FileWriter(StringStorage.SAVE_FILE_PATH);
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
}
