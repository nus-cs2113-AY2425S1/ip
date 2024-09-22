package erika.filesystem;
import erika.console.Console;
import erika.exception.FileFormatErrorException;
import erika.settings.Settings;
import erika.task.Deadline;
import erika.task.Event;
import erika.task.Task;
import erika.task.Todo;
import erika.tasklist.TaskList;

import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
/**
 * Represents the interface between the application and the host's filesystem
 */
public class FileSystem {
    private String filePath;
    private String separator;
    /**
     * @param filePath path to the file used for storage
     * @param separator character used to delimit the regions of data
     */
    public FileSystem(String filePath, String separator) {
        this.filePath = filePath;
        this.separator = separator;
    }
    /**
     * Deletes and recreates the file used for storage
     * Typically called when a <code>FileFormatException</code> is thrown
     */
    private void reinitFileSystem() {
        try{
            Console.printMessage(Settings.FILENAME + " is corrupted, re-creating the file");
            writeToFile("",false);
        } catch (IOException _e) {
            Console.printMessage("IO Error encountered when re-creating " + Settings.FILENAME);
        }
    }
    /**
     * Creates the file used for storage when the application is run for the very first time
     * Typically called when a <code>FileNotFoundException</code> is thrown
     */
    public ArrayList<Task> initializeFileSystem() {
        ArrayList<Task> tasks = new ArrayList<>();
        try{
            readFromFile(tasks);
        } catch (FileNotFoundException e){
            printFileNotFoundMessage();
        } catch (FileFormatErrorException e) {
            reinitFileSystem();
        } finally {
            return tasks;
        }
    }
    /**
     * Creates the file used for storage when the application is run for the very first time
     * Reads and parses data from the filePath line by line
     * @param tasks An <code>ArrayList</code> of the list of tasks
     */
    public void readFromFile(ArrayList<Task> tasks) throws FileNotFoundException, FileFormatErrorException {
        File f = new File(filePath);
        if(!f.exists()) {
            createDirectory(Settings.DIRECTORY);
            try{
                writeToFile("",false);
            } catch (IOException _e) {
                Console.printMessage("IO Error encountered when creating " + Settings.DIRECTORY);
            }
            throw new FileNotFoundException(filePath);
        }
        Scanner s = new Scanner(f);
        int lineNumber = 1;
        while (s.hasNext()) {
            parseLine(tasks, s.nextLine(), lineNumber++);
        }
    }
    /**
     * Writes data to the storage file
     * @param textToAdd Text data to be added.
     * @param isAppend whether to append or overwrite existing data
     */
    public void writeToFile(String textToAdd, boolean isAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, isAppend);
        fw.write(textToAdd);
        fw.close();
    }
    /**
     * Appends a <code>Task</code> object to the storage file
     * @param task <code>Task</code> object to be appended to the file
     */
    public void appendTaskToFile(Task task) throws IOException {
        writeToFile(task.generateFileLine(),true);
    }
    /**
     * Overwrites task file with data generated from current <code>TaskList</code> list of tasks
     * @param tasks <code>TaskList</code> lists of tasks
     */
    public void updateFileSystemWithLocalTasks(TaskList tasks) throws IOException{
        String res = tasks.printFileLine();
        writeToFile(res,false);
    }
    /**
     * Prints an error message detailing to the user that the file was not found and that a new one has been created
     */
    public void printFileNotFoundMessage() {
        Console.printMessage("\t" + filePath + " not found. Creating new file.");
    }
    /**
     * Creates a directory to store the storage file
     * @param directoryName <code>String</code> storing the location of the directory
     */
    private void createDirectory(String directoryName) {
        File directory = new File(directoryName);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }
    /**
     * Parses a <code>String</code> line and appends the <code>Task</code> object to the <code>TaskList</code> list of
     * tasks
     * @param tasks <code>TaskList</code> lists of tasks
     * @param line <code>String</code> representing the line from the text file
     * @param lineNumber <code>int</code> representing the current line number. Used for error handling
     */
    private void parseLine(ArrayList<Task> tasks, String line, int lineNumber) throws FileFormatErrorException {
        String[] tokens = line.split(separator);
        if (tokens.length < 3) {
            throw new FileFormatErrorException("Error on line " + lineNumber + ": invalid line, too few entries");
        }
        String description = tokens[2];
        switch (tokens[0]) {
        case "T":
            if (tokens.length != 3) {
                throw new FileFormatErrorException("Error on line " + lineNumber + ": invalid Todo line!");
            }
            Todo todo = new Todo(description);
            todo.setMark(tokens[1].equals("1"));
            tasks.add(todo);
            break;

        case "D":
            if (tokens.length != 4) {
                throw new FileFormatErrorException("Error on line " + lineNumber + ": invalid Deadline line!");
            }
            Deadline deadline = new Deadline(description, tokens[3]);
            deadline.setMark(Boolean.parseBoolean(tokens[1]));
            tasks.add(deadline);
            break;
        case "E":
            if (tokens.length != 5) {
                throw new FileFormatErrorException("Error on line " + lineNumber + ": invalid Event line!");
            }
            Event event = new Event(description, tokens[3], tokens[4]);
            event.setMark(Boolean.parseBoolean(tokens[1]));
            tasks.add(event);
            break;
        }
    }
}
