package november.storage;

import november.exceptions.NovemberExceptions;
import november.tasks.Deadline;
import november.tasks.Event;
import november.tasks.Task;
import november.tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static november.tasklist.TaskList.markTask;

import static november.messages.Messages.DEADLINE_COMMAND;
import static november.messages.Messages.EVENT_COMMAND;
import static november.messages.Messages.TODO_COMMAND;

public class Storage {

    public static final String SAVE_FILE = "data/saveFile.txt"; // Path to the save file
    public static final File saveFile = new File(SAVE_FILE); // File object for the save file

    /**
     * Initializes the save file by creating the necessary directories and file if they do not exist.
     */
    public static void initialiseSaveFile() {
        Path dirPath = Paths.get("data"); // Path to the data directory
        Path filePath = dirPath.resolve("saveFile.txt"); // Path to the save file
        File file = new File(filePath.toString()); // File object for the save file

        // Create directories if they do not exist
        if (!file.exists()) {
            try {
                Files.createDirectories(dirPath);
            } catch (IOException e) {
                System.out.println("Directory could not be created");
                throw new RuntimeException(e);
            }
        }

        // Create a save file if it does not already exist
        try {
            if (file.createNewFile()) {
                System.out.println("No existing save file found. New save file created: " + file.getName());
            } else {
                System.out.println("Accessing existing save file...");
            }
        } catch (IOException e) {
            System.out.println("Save file could not be created.");
            throw new RuntimeException(e);
        }
    }

    /**
     * Loads the tasks from the save file into the given task list.
     *
     * @param taskList The list to populate with tasks from the save file.
     * @throws FileNotFoundException If the save file is not found.
     */
    public static void loadSaveFile(ArrayList<Task> taskList) throws FileNotFoundException {
        Scanner s = new Scanner(saveFile); // Create a Scanner to read the save file
        while (s.hasNext()) {
            String line = s.nextLine(); // Read each line from the file
            String command = line.split(" ", 2)[0]; // Extract command from the line
            switch (command) {
                case TODO_COMMAND:
                    String[] todoData = line.split(" \\| ", 3); // Split the line for TODO task data
                    String todoDescription = todoData[2]; // Get the description of the TODO task
                    Todo todoTask = NovemberExceptions.validTodo(todoDescription); // Create a TODO task
                    taskList.add(todoTask); // Add the TODO task to the list
                    if (Boolean.parseBoolean(todoData[1])) {
                        markTask(String.valueOf(taskList.size()), taskList, false); // Mark task if needed
                    }
                    break;
                case DEADLINE_COMMAND:
                    String[] deadlineData = line.split(" \\| ", 4); // Split the line for DEADLINE task data
                    String deadlineDescription = deadlineData[2] + " /by " + deadlineData[3]; // Format the description
                    Deadline deadlineTask = NovemberExceptions.validDeadline(deadlineDescription); // Create a DEADLINE task
                    taskList.add(deadlineTask); // Add the DEADLINE task to the list
                    if (Boolean.parseBoolean(deadlineData[1])) {
                        markTask(String.valueOf(taskList.size()), taskList, false); // Mark task if needed
                    }
                    break;
                case EVENT_COMMAND:
                    String[] eventData = line.split(" \\| ", 5); // Split the line for EVENT task data
                    String eventDescription = eventData[2] + " /from " + eventData[3] + " /to " + eventData[4]; // Format the description
                    Event eventTask = NovemberExceptions.validEvent(eventDescription); // Create an EVENT task
                    taskList.add(eventTask); // Add the EVENT task to the list
                    if (Boolean.parseBoolean(eventData[1])) {
                        markTask(String.valueOf(taskList.size()), taskList, false); // Mark task if needed
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Invalid entry in save file."); // Handle invalid entries
            }
        }
        System.out.println("Save file successfully loaded.");
    }

    /**
     * Updates the save file with the current list of tasks.
     *
     * @param taskList The list of tasks to be saved.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public static void updateSaveFile(ArrayList<Task> taskList) throws IOException {
        try (FileWriter fw = new FileWriter(saveFile)) {
            for (Task task : taskList) {
                fw.write(task.toString()); // Write the task to the file
                fw.write(System.lineSeparator()); // Add a new line after each task
            }
        }
    }
}
