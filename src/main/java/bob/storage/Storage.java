package bob.storage;
import bob.task.Task;
import bob.task.ToDo;
import bob.task.Deadline;
import bob.task.Event;
import bob.ui.Ui;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a storage mechanism for managing tasks.
 * This class handles loading and saving tasks to a file,
 * as well as creating necessary directories and files.
 */
public class Storage {

    private static final String SEPARATOR = "\\|";
    private static final int MIN_TASK_LENGTH = 3;
    private static final int MIN_DEADLINE_LENGTH = 4;
    private static final int MIN_EVENT_LENGTH = 4;
    private static final int MIN_EVENT_TIME_LENGTH = 2;
    private static final String TASK_TODO = "T";
    private static final String TASK_DEADLINE = "D";
    private static final String TASK_EVENT = "E";
    private static final String FOLDER = "folder";
    private static final String FILE = "file";

    private final String filePath;
    private final Ui ui;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks will be stored.
     */
    public Storage(String filePath) {
        this.filePath = Paths.get(filePath).toString();
        this.ui = new Ui();
    }

    /**
     * Loads tasks from the storage file and returns them as an Arraylist of Task objects.
     * If the file or directory does not exist, they will be created and a corresponding message will be printed.
     *
     * @return An ArrayList of Task objects loaded from the file.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        createDirectoryIfNotExists(file);
        createFileIfNotExists(file);

        try (Scanner scanner = new Scanner(file)) {
            int lineNumber = 0;
            while (scanner.hasNextLine()) {
                lineNumber++;
                String line = scanner.nextLine();
                try {
                    Task task = parseTask(line);
                    if (task != null) {
                        tasks.add(task);
                    }
                } catch (IllegalArgumentException e) {
                    this.ui.printErrorParsingLineMessage(lineNumber, e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            this.ui.printFileNotFound(e.getMessage());
        }
        return tasks;
    }

    /**
     * Creates the parent directory of the specified file if it does not exist.
     *
     * @param file The file whose parent directory will be created if needed.
     */
    private void createDirectoryIfNotExists(File file) {
        File parentDirectory = file.getParentFile();
        if (!parentDirectory.exists()) {
            this.ui.printCreationMessage(FOLDER, true);
            if (!parentDirectory.mkdirs()) {
                this.ui.printCreationMessage(FOLDER,false);
            }
        }
    }

    /**
     * Creates the specified file if it does not exist.
     *
     * @param file The file to be created if it does not exist.
     */
    private void createFileIfNotExists(File file) {
        if (!file.exists()) {
            this.ui.printCreationMessage(FILE, true);
            try {
                file.createNewFile();
            } catch (IOException e) {
                this.ui.printCreationMessage(FILE, false);
            }
        }
    }

    /**
     * Parses a line from the storage file to create a Task object.
     *
     * @param line The line of text representing a task.
     * @return A Task object parsed from the line.
     * @throws IllegalArgumentException if the line format is invalid, a specific error message will be displayed.
     */
    private Task parseTask(String line) throws IllegalArgumentException {
        String[] parts = line.split(SEPARATOR);
        if (parts.length < MIN_TASK_LENGTH) {
            throw new IllegalArgumentException("Invalid line format: " + line);
        }

        String type = parts[0].trim();
        boolean isDone;
        try {
            isDone = Integer.parseInt(parts[1].trim()) == 1;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid done status: " + parts[1].trim());
        }
        String description = parts[2].trim();

        Task task;
        switch (type) {
        case TASK_TODO:
            task = new ToDo(description);
            break;
        case TASK_DEADLINE:
            if (parts.length < MIN_DEADLINE_LENGTH) {
                throw new IllegalArgumentException("Invalid deadline format: " + line);
            }
            task = new Deadline(description, parts[3].trim());
            break;
        case TASK_EVENT:
            if (parts.length < MIN_EVENT_LENGTH) {
                throw new IllegalArgumentException("Invalid event format: " + line);
            }
            String[] eventTimes = parts[3].trim().split("-");
            if (eventTimes.length != MIN_EVENT_TIME_LENGTH) {
                throw new IllegalArgumentException("Invalid event times: " + parts[3].trim());
            }
            task = new Event(description, eventTimes[0].trim(), eventTimes[1].trim());
            break;
        default:
            throw new IllegalArgumentException("Unknown task type: " + type);
        }

        if (isDone) {
            task.markAsDone();
        }

        return task;
    }

    /**
     * Saves the current list of tasks to the storage file.
     *
     * @param tasks An ArrayList of Task objects to save.
     */
    public void save(ArrayList<Task> tasks) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : tasks) {
                if (task == null) {
                    break;
                }
                writer.write(taskToString(task) + System.lineSeparator());
            }
        } catch (IOException e) {
            this.ui.printErrorSavingTasks(e.getMessage());
        }
    }

    /**
     * Appends a task to the storage file.
     *
     * @param task The Task object to be appended to the file.
     */
    public void appendTask(Task task) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(taskToString(task) + System.lineSeparator());
        } catch (IOException e) {
            this.ui.printErrorAppendingTask(e.getMessage());
        }
    }

    /**
     * Converts a Task object to its string representation for saving to a file.
     *
     * @param task The Task object to be converted to its string representation.
     * @return A string representation of the Task object.
     */
    private String taskToString(Task task) {
        StringBuilder sb = new StringBuilder();
        if (task instanceof ToDo) {
            sb.append(TASK_TODO);
        } else if (task instanceof Deadline) {
            sb.append(TASK_DEADLINE);
        } else if (task instanceof Event) {
            sb.append(TASK_EVENT);
        }
        sb.append(" | ").append(task.getStatusIcon().equals("X") ? "1" : "0");
        sb.append(" | ").append(task.getDescription());
        if (task instanceof Deadline) {
            sb.append(" | ").append(((Deadline) task).getDeadline());
        } else if (task instanceof Event) {
            sb.append(" | ").append(((Event) task).getEventStartTime());
            sb.append("-").append(((Event) task).getEventEndTime());
        }
        return sb.toString();
    }
}
