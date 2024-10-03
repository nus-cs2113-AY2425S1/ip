package CassHelpers.util;

import CassHelpers.exceptions.CassandraException;
import CassHelpers.exceptions.InvalidDateFormatException;
import CassHelpers.types.Deadline;
import CassHelpers.types.Event;
import CassHelpers.types.Task;
import CassHelpers.types.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static CassHelpers.util.Parser.parseStringToLocalDateTime;

/**
 * Storage class that handles reading and writing tasks to and from a file.
 * Manages the creation of directories and files, and supports storing tasks as a list.
 */
public class Storage {
    public static final String TODO = "T";
    public static final String  DEADLINE = "D";
    public static final String EVENT = "E";
    private final File dir;
    private final File file;

    /**
     * Constructor for Storage class that initializes the directory and file for task storage.
     *
     * @param directoryPath The path to the directory where the file will be stored.
     * @param fileName The name of the file to store tasks.
     */
    public Storage(String directoryPath, String fileName) {
        this.dir = new File(directoryPath);
        createDir();
        this.file = new File(dir, fileName);
        createFile();
    }

    /**
     * Creates the directory if it does not exist.
     * If the directory creation fails, an error message is displayed.
     */
    public void createDir() {
        try {
            if(!dir.exists()){
                if(!dir.mkdirs()){
                    throw new IOException("Could not create directory");
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Creates the file if it does not exist.
     * If the file creation fails, an error message is displayed.
     */
    public void createFile() {
        try {
            if(!file.exists()){
                if(!file.createNewFile()){
                    throw new IOException("Could not create file");
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Appends a single task to the file.
     *
     * @param task The task to be appended to the file.
     */
    public void appendTaskToFile(Task task) {
        try {
            FileWriter fileWriter = new FileWriter(file.getPath(), true);
            fileWriter.write(task.toWritableString()+"\n");
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file. ");
        }
    }

    /**
     * Writes all tasks from the task list to the file, overwriting any existing data.
     *
     * @param taskList The list of tasks to be written to the file.
     */
    public void writeTasksToFile(ArrayList<Task> taskList) {
        try {
            FileWriter fileWriter = new FileWriter(file.getPath());
            for (Task task : taskList) {
                fileWriter.write(task.toWritableString()+"\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file. ");
        }
    }

    /**
     * Reads tasks from the file and loads them into an ArrayList of tasks.
     *
     * @return An ArrayList of tasks read from the file.
     * @throws CassandraException If an error occurs while reading from the file.
     */
    public ArrayList<Task> readTaskFromFile() throws CassandraException {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(this.file);
            while (scanner.hasNext()) {
                Task task = taskFormatter(scanner.nextLine());
                taskList.add(task);
            }
        } catch (IOException e) {
            throw new CassandraException("An error occurred while reading from the file.");
        }
        return taskList;
    }

    /**
     * Formats a task string from the file into a corresponding Task object (Todo, Deadline, or Event).
     *
     * @param line The string representing the task in the file.
     * @return A Task object parsed from the input string.
     * @throws InvalidDateFormatException If the date format in the task string is invalid.
     */
    private Task taskFormatter(String line) throws InvalidDateFormatException {
        String[] lineParser = line.split(",");
        Task task = new Task("dummy");
        switch (lineParser[0]){
            case TODO:
                task = new Todo(lineParser[2]);
                task.setCompleted(lineParser[1].equals("1"));
                break;
            case DEADLINE:
                task = new Deadline(lineParser[2], parseStringToLocalDateTime(lineParser[3]));
                task.setCompleted(lineParser[1].equals("1"));
                break;
            case EVENT:
                task = new Event(lineParser[2], parseStringToLocalDateTime(lineParser[3]), parseStringToLocalDateTime(lineParser[4]));
                task.setCompleted(lineParser[1].equals("1"));
                break;
            default:
                System.out.println("An error occurred while parsing a task.");
        }
        return task;
    }
}
