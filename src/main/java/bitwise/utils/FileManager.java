package bitwise.utils;

import bitwise.constants.Constants;
import bitwise.constants.Icons;
import bitwise.exceptions.InvalidFormatException;
import bitwise.tasks.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The {@code FileManager} class handles file operations for saving and retrieving tasks.
 * It ensures the task file is created if it doesn't exist and handles saving/loading tasks from a file.
 */
public class FileManager {
    /**
     * Creates the file and directory for storing tasks if they do not exist.
     *
     * @throws IOException if an I/O error occurs while creating the file or directory
     */
    public static void createFileIfNotExists() throws IOException{
        File dir = new File(Constants.FILE_DIR_PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File tasks = new File(dir, Constants.FILE_NAME);
        tasks.createNewFile();
    }

    public static void removeCorruptFile() {
        File dir = new File(Constants.FILE_DIR_PATH);
        File tasks = new File(dir, Constants.FILE_NAME);
        tasks.delete();
        try {
            createFileIfNotExists();
        } catch (IOException e) {}
    }

    /**
     * Saves the entire list of tasks to the file.
     *
     * @param tasksList the list of tasks to save
     * @param numberOfTasks the number of tasks in the list
     * @throws IOException if an I/O error occurs while writing to the file
     */
    public static void saveTasksList(ArrayList<Task> tasksList, int numberOfTasks) throws IOException{
        createFileIfNotExists();
        FileWriter fw = new FileWriter(Constants.FILE_PATH);
        for (int i = 0; i < numberOfTasks; i++) {
            fw.write(tasksList.get(i).toString() + System.lineSeparator());
        }
        fw.close();
    }
    /**
     * Appends a new task to the task file.
     *
     * @param task the task to append
     * @throws IOException if an I/O error occurs while writing to the file
     */
    public static void saveTask(Task task) throws IOException{
        createFileIfNotExists();
        FileWriter fw = new FileWriter(Constants.FILE_PATH, true);
        fw.write(task.toString() + System.lineSeparator());
        fw.close();
    }
    /**
     * Loads tasks from the file into the provided task list.
     *
     * @param tasksList the list to store loaded tasks
     * @return the number of tasks loaded from the file
     * @throws IOException if an I/O error occurs while reading the file
     * @throws InvalidFormatException if the file format is corrupted
     */
    public static int getTasks(ArrayList<Task> tasksList) throws IOException{
        File f = new File(Constants.FILE_PATH);
        int numberOfTasks = 0;
        if (!f.exists()) {
            return 0;
        }
        Scanner s = new Scanner(f);
        while (s.hasNextLine()) {
            String line = s.nextLine();
            String toProcess = line.substring(line.indexOf(" ") + 1);
            Task newTask;
            switch(line.substring(Constants.PARSE_ICON_INDEX, line.indexOf(" "))) {
            case Icons.ICON_TODO:
                newTask = TextParser.handleTodo(toProcess);
                tasksList.add(newTask);
                numberOfTasks++;
                break;
            case Icons.ICON_EVENT:
                newTask = TextParser.handleEvent(toProcess);
                tasksList.add(newTask);
                numberOfTasks++;
                break;
            case Icons.ICON_DEADLINE:
                newTask = TextParser.handleDeadline(toProcess);
                tasksList.add(newTask);
                numberOfTasks++;
                break;
            default:
                s.close();
                throw new InvalidFormatException("File corrupt: " + line);
            }
        }
        return numberOfTasks;
    }
}
