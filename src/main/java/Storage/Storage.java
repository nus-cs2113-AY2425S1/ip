package Storage;
import Parser.Parser;
import commands.Deadline;
import commands.Event;
import commands.Task;
import commands.Todo;
import constants.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the methods that deals with loading and saving tasks in the txt file
 */
public class Storage{

    private Parser parser = new Parser();

    /**
     * Store existing data from txt file to the ArrayList.
     * Create a new text file if the file is not found
     * @return an ArrayList containing the preexisting tasks from txt file
     */
    public ArrayList<Task> loadExistingData() {
        ArrayList<Task> items = new ArrayList<>();
        try {
            storeExistingDataToList(items);
        } catch (FileNotFoundException e) {
            createFile();
        }
        return items;
    }

    /**
     * Print previously stored tasks from the txt file
     */
    public void printFileContents() throws FileNotFoundException {
        File f = new File(Utils.FILE_PATH);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    /**
     * Reads existing data from a txt file and stores it into the provided list of tasks.
     * This method parses each line from the file to determine the type of task (ToDo, Deadline, Event)
     * and calls the respective methods to load the task data into the list.
     * If the file is not found, it creates a new file.
     *
     * @param items the ArrayList that stores the pre-existing tasks
     * @throws FileNotFoundException when cy.txt is not found in the relevant file path
     */
    private void storeExistingDataToList(ArrayList<Task> items) throws FileNotFoundException {
        try {
            File file = new File(Utils.FILE_PATH);
            Scanner s = new Scanner(file);

            while (s.hasNextLine()) {
                String storedTask = s.nextLine();
                String[] storedTaskSubstrings = storedTask.split("\\| ");
                String command = storedTaskSubstrings[0].trim();

                switch (command) {
                    case "T":
                        loadExistingTodo(storedTaskSubstrings,items);
                        break;
                    case "D":
                        loadExistingDeadline(storedTaskSubstrings,items);
                        break;
                    case "E":
                        loadExistingEvent(storedTaskSubstrings,items);
                        break;
                }
            }

        } catch (FileNotFoundException e) {
            createFile();
        }
    }

    /**
     * Saves the current list of tasks to a file.
     *
     * This method first clears the existing file content and then writes each task in the list
     * to the file using a format specified in the command classes (todo,event and deadline).
     * It will print a message if there is an error in file operations
     *
     * @param items An ArrayList of Task objects that will be saved to the file.
     */
    public void saveNewData(ArrayList<Task> items) {
        try {
            clearFile();
            for (Task item : items) {
                writeToFile(item.createTaskTxt());
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving the file: " + e.getMessage());
        }
    }

    /**
     * Add the specified text to file without overriding the previous data.
     *
     * @param textToAdd the string containing the specified text to be written into the txt file
     * @throws IOException if there are any issues when file writing
     */
    private static void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(Utils.FILE_PATH, true);
        fw.write(System.lineSeparator() + textToAdd);
        fw.close();
    }

    /**
     * Clears the file before saving the current list of data into the file
     * This method ensures that the tasks in the txt file mirrors tasks stored in
     * the ArrayList
     *
     * @throws IOException if there are any issues when file writing
     */
    public static void clearFile() {
        try {
            FileWriter fw = new FileWriter(Utils.FILE_PATH, false);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong while clearing the file: " + e.getMessage());
        }
    }

    /**
     * Create txt file if file not found at the file path
     */
    public static void createFile() {
        try {
            File file = new File(Utils.FILE_PATH);

            // Check if the parent directory exists
            if (!file.getParentFile().exists()) {
                // Create the directory if it doesn't exist
                file.getParentFile().mkdirs();
            }

            if (!file.exists()) {
                file.createNewFile();
                System.out.println("File created: " + file.getAbsolutePath());
            }

        } catch (IOException e) {
            System.out.println("An error occurred while creating the file: " + e.getMessage());
        }
    }

    /**
     * Loads an existing deadline task from a stored string array and adds it to the task list.
     *
     * @param storedTaskSubstrings A String[] containing the stored task split by "|" delimiter.
     * @param items ArrayList that stores the tasks
     */
    public void loadExistingDeadline(String[] storedTaskSubstrings,ArrayList<Task> items) {
        String description = storedTaskSubstrings[2].trim();
        String by = storedTaskSubstrings[3].trim();
        Deadline deadline = new Deadline(description, by);
        parser.checkComplete(storedTaskSubstrings, deadline);

        items.add(deadline);
    }

    /**
     * Loads an existing event task from a stored string array and adds it to the task list.
     *
     * @param storedTaskSubstrings A String[] containing the stored task split by "|" delimiter.
     * @param items ArrayList that stores the tasks
     */
    public void loadExistingEvent(String[] storedTaskSubstrings, ArrayList<Task> items) {
        String description = storedTaskSubstrings[2].trim();
        String time = storedTaskSubstrings[3].trim();
        String start = time.split("-")[0];
        String end = time.split("-")[1];

        Event event = new Event(description, start, end);
        parser.checkComplete(storedTaskSubstrings, event);

        items.add(event);
    }

    /**
     * Loads an existing todo task from a stored string array and adds it to the task list.
     *
     * @param storedTaskSubstrings A String[] containing the stored task split by "|" delimiter.
     * @param items ArrayList that stores the tasks
     */
    public void loadExistingTodo(String[] storedTaskSubstrings, ArrayList<Task> items) {
        String description = storedTaskSubstrings[2].trim();
        Todo todo = new Todo(description);
        parser.checkComplete(storedTaskSubstrings, todo);

        items.add(todo);
    }

}
