import task.Deadline;
import task.Event;
import task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * The Storage class handles loading and saving task data to and from a file.
 */
public class Storage {

    /**
     * Writes the given text to a specified file.
     * @param filePath The path of the file to write to.
     * @param textToAdd The text to add to the file.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Sets up the file by creating a new file if it does not exist.
     * Also creates the parent directory if it doesn't exist.
     * @param filePath The path of the file to set up.
     * @throws IOException If an I/O error occurs while setting up the file.
     */
    public static void writerSetUp(String filePath) throws IOException {
        File listFile = new File(filePath);
        if (!listFile.exists()) {
            File directory = listFile.getParentFile();
            if (!directory.exists()) {
                directory.mkdirs(); // Create directory if it doesn't exist
            }
            listFile.createNewFile(); // Create file if it doesn't exist
        }
    }

    /**
     * Loads task data from a file and adds it to the provided user list.
     * @param filePath The path of the file to load data from.
     * @param userList The list where the tasks will be added.
     */
    public static void loadDataFromFile(String filePath, List userList) {
        File file = new File(filePath);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                parseAndAddItem(line, userList);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        }
    }

    /**
     * Parses a line from the file and adds the corresponding task to the user list.
     * @param line The line to parse.
     * @param userList The list where the task will be added.
     */
    private static void parseAndAddItem(String line, List userList) {
        String[] parts = line.split(" \\| ");
        String taskType = parts[0]; // T, D, E
        boolean isDone = parts[1].equals("X");
        String taskDescription = parts[2];

        switch (taskType) {
        case "T":
            Todo todoTask = new Todo(taskDescription);
            if (isDone) {
                todoTask.markAsDone();
            }
            userList.itemArrayList.add(todoTask);
            break;

        case "D":
            String deadlineDate = parts[3]; // Ignore remaining parts
            Deadline deadlineTask = new Deadline(taskDescription, deadlineDate);
            if (isDone) {
                deadlineTask.markAsDone();
            }
            userList.itemArrayList.add(deadlineTask);
            break;

        case "E":
            String eventStart = parts[3]; // Start date/time
            String eventEnd = parts[4];   // End date/time
            Event eventTask = new Event(taskDescription, eventStart, eventEnd);
            if (isDone) {
                eventTask.markAsDone();
            }
            userList.itemArrayList.add(eventTask);
            break;

        default:
            System.out.println("Invalid task type in file: " + taskType);
            break;
        }
    }

    /**
     * Saves the current task list to the specified file.
     * @param listFilePath The path of the file to save the list to.
     * @param userList The list containing tasks to save.
     */
    public static void saveListToFile(String listFilePath, List userList) {
        try {
            writeToFile(listFilePath, userList.getFormattedTasks()); // getFormattedTasks returns a formatted String
        } catch (IOException e) {
            System.out.println("An error occurred while saving the list.");
        }
    }
}

