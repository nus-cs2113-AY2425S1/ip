package jeremy.util;

import jeremy.command.CommandType;
import jeremy.exception.InvalidStorageException;
import jeremy.task.Todo;
import jeremy.task.Deadline;
import jeremy.task.Event;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * The {@code Storage} class is responsible for handling the loading and saving of tasks
 * to a file. It ensures that task data is persisted across program sessions.
 */
public class Storage {
    private static final String PATH = "./data/";
    private static final String FILE_NAME = "Jeremy.txt";
    private static final String SEPARATOR = " \\| ";

    /**
     * Creates a task from the given parts of a line read from the storage file.
     * This method adds the task to the provided {@link TaskList}.
     *
     * @param parts    The string array representing different parts of the task (icon, status, description, etc.).
     * @param taskList The {@link TaskList} to which the created task is added.
     */
    public void createTask(String[] parts, TaskList taskList) {
        try {
            String commandStr = parts[0];
            boolean isNotDone = parts[1].equals("0");
            boolean isDone = parts[1].equals("1");

            if (!isNotDone && !isDone) {
                throw new InvalidStorageException("Corrupted storage :(");
            }

            CommandType command = Parser.fromIcon(commandStr);

            switch (command) {
            case TODO:
                taskList.addTask(new Todo(parts[2]), isDone);
                break;
            case DEADLINE:
                taskList.addTask(new Deadline(parts[2] + " /by " + parts[3]), isDone);
                break;
            case EVENT:
                taskList.addTask(new Event(parts[2] + " /from " + parts[3]
                                            + " /to " + parts[4]), isDone);
                break;
            default:
                // should already be thrown by fromIcon method, but just in case
                throw new InvalidStorageException("Corrupted storage :(");
            }
        } catch (Exception ignored) {
            // ignore lines with incorrect format, but continue execution of program
            System.out.println("error " + ignored);
        }
    }

    /**
     * Loads tasks from the storage file, creating a new {@link TaskList} that contains
     * the tasks from the file. If the file does not exist, a new file and directory are created.
     *
     * @return The {@link TaskList} loaded from the storage file.
     * @throws FileNotFoundException If an error occurs while creating or accessing the file.
     */
    public TaskList load() throws FileNotFoundException {
        File dir = new File(PATH);
        if (!dir.exists()) {
            dir.mkdir();
        }

        File file = new File(PATH + FILE_NAME);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new FileNotFoundException(e.getMessage());
            }
        }

        Scanner scanner = new Scanner(file);
        TaskList taskList = new TaskList();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(SEPARATOR);

            if (parts.length >= 3) {
                createTask(parts, taskList);
            }
        }

        return taskList;
    }

    /**
     * Saves the current state of the task list to the storage file. Each task is saved in a format
     * suitable for reloading by the {@link #load()} method.
     *
     * @param tasks The {@link TaskList} to be saved.
     */
    public void save(TaskList tasks) {
        File file = new File(PATH + FILE_NAME);

        try (FileWriter fw = new FileWriter(file)) {
            for (int i = 0; i < tasks.getSize(); i++) {
                fw.write(tasks.getTask(i).toStorageString() + System.lineSeparator());
            }
        } catch (IOException e) {
            Ui ui = new Ui();
            ui.println("Unable to save data: " + e.getMessage());
        }
    }
}
