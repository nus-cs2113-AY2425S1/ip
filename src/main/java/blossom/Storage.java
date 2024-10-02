package blossom;

import blossom.task.Deadline;
import blossom.task.Event;
import blossom.task.Task;
import blossom.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * The <code>Storage</code> class handles loading and saving tasks to a text file.
 * It is responsible for reading and writing task data to the hard disk.
 */
public class Storage {
    private final String horizontalLine = "____________________________________________________________";
    private final String filePath;
    private final ArrayList<Task> tasks;

    /**
     * Constructs a Storage object.
     *
     * @param filePath The path of the file where tasks are stored.
     * @param tasks The list of tasks to be loaded or saved.
     */
    Storage(String filePath, ArrayList<Task> tasks) {
        this.filePath = filePath;
        this.tasks = tasks;
    }

    /**
     * Loads tasks from the file specified by filePath. If the file does not exist,
     * it tries to create a new file.
     *
     * @throws BlossomException if there is no file to be found.
     */
    public void loadTasks() throws BlossomException {
        File f = new File(this.filePath); // create a File for the given file path
        try (Scanner s = new Scanner(f)) {
            while (s.hasNextLine()) {
                String line = s.nextLine();
                addTaskFromFile(line);
            }
        } catch (BlossomException | FileNotFoundException e) {
            System.out.println("Data file not found! Creating a new one....");
            new File("./data").mkdirs();
            System.out.println("Data file created!");
            System.out.println(horizontalLine);
        }
    }

    /**
     * Parses a single line from the text file and adds the corresponding task
     * to the task list.
     *
     * @param fileInput A line from the task file that stands for a single task.
     * @throws BlossomException if the task type is unknown.
     */
    public void addTaskFromFile(String fileInput) throws BlossomException {
        String[] parts = fileInput.split("\\|");
        String type = parts[0].trim();
        boolean isDone = Boolean.parseBoolean(parts[1].trim());
        String description = parts[2].trim();
        switch (type) {
        case "T":
            Todo todo = new Todo(description);
            if (isDone) {
                todo.markAsDone();
            }
            this.tasks.add(todo);
            break;
        case "D":
            String by = parts[3].trim();
            Deadline deadline = new Deadline(description, by);
            if (isDone) {
                deadline.markAsDone();
            }
            this.tasks.add(deadline);
            break;
        case "E":
            String from = parts[3].trim();
            String to = parts[4].trim();
            Event event = new Event(description, from, to);
            if (isDone) {
                event.markAsDone();
            }
            this.tasks.add(event);
            break;
        default:
            throw new BlossomException("Invalid task type: " + type);
        }
    }

    /**
     * Saves all tasks in the task list to the text file specified by filePath.
     */
    public void saveTasks() {
        try (FileWriter fw = new FileWriter(this.filePath, false)) {
            for (Task item : this.tasks) {
                fw.write( item.toFileFormat()+ "\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while trying to save tasks to file.");
        }
    }

}
