package org.ajay.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.ajay.common.Messages;
import org.ajay.data.exceptions.EmptyArgumentException;
import org.ajay.data.exceptions.IllegalArgumentException;
import org.ajay.data.task.Deadline;
import org.ajay.data.task.Event;
import org.ajay.data.task.Task;
import org.ajay.data.task.Todo;
import org.ajay.ui.TextUi;

/**
 * Represents the storage of the task list.
 */
public class Storage {

    /** The file path of the storage file. */
    private static String filePath;

    /**
     * Gets the file path of the storage file.
     *
     * @return The file path of the storage file.
     */
    public static String getFilePath() {
        return filePath;
    }

    /**
     * Sets the file path of the storage file.
     *
     * @param filePath The file path of the storage file.
     */
    public static void setFilePath(String filePath) {
        Storage.filePath = filePath;
    }

    /** Default constructor for Storage. */
    public Storage() {
    }

    /**
     * Constructor for Storage.
     *
     * @param filePath The file path of the storage file.
     */
    public Storage(String filePath) {
        setFilePath(filePath);
    }

    /**
     * Loads the task list from the storage file.
     *
     * @return The task list loaded from the storage file.
     * @throws IllegalArgumentException If the file is not found or the data is
     *                                  corrupted.
     */
    public ArrayList<Task> load() throws IllegalArgumentException {
        ArrayList<Task> tasks = new ArrayList<>();

        /** Check if the file exists */
        File f = new File(filePath);
        if (!f.exists()) {
            try {
                /** Create the file if it does not exist */
                f.getParentFile().mkdirs();
                f.createNewFile();
            } catch (IOException e) {
                TextUi.printExceptions(Messages.MESSAGE_FILE_NOT_FOUND + " " + e.getMessage());
                TextUi.exit(0);
            }
        }

        /** Read the file */
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {

                /* Split the line into task type, isDone, and task description */
                String[] task = line.split(" \\| ");

                boolean isDone = false;
                if (task[Task.DONE_FLAG_LOAD_INDEX].equals("1")) {
                    isDone = true;
                }

                switch (task[Task.COMMAND_LOAD_INDEX]) {
                    case Todo.TASK_ID:
                        if (task.length < Todo.TASK_LENGTH) {
                            throw new IllegalArgumentException(Messages.MESSAGE_IMPORT_TODO_FAILED);
                        }
                        tasks.add(Todo.loadTaskString(isDone, task[Todo.DESCRIPTION_LOAD_INDEX]));

                        break;
                    case Event.TASK_ID:
                        if (task.length < Event.TASK_LENGTH) {
                            throw new IllegalArgumentException(Messages.MESSAGE_IMPORT_EVENT_FAILED);
                        }
                        tasks.add(Event.loadTaskString(isDone, task[Event.DESCRIPTION_LOAD_INDEX],
                                task[Event.FROM_LOAD_INDEX], task[Event.TO_LOAD_INDEX]));

                        break;
                    case Deadline.TASK_ID:
                        if (task.length < Deadline.TASK_LENGTH) {
                            throw new IllegalArgumentException(Messages.MESSAGE_IMPORT_DEADLINE_FAILED);
                        }
                        tasks.add(Deadline.loadTaskString(isDone, task[Deadline.DESCRIPTION_LOAD_INDEX],
                                task[Deadline.BY_LOAD_INDEX]));

                        break;
                    default:
                        throw new AssertionError();
                }
            }
        } catch (IOException e) {
            TextUi.printExceptions(Messages.MESSAGE_FILE_NOT_READ + " " + e.getMessage());
        } catch (EmptyArgumentException e) {
            TextUi.printExceptions(e.getMessage());
        }

        return tasks;
    }

    /**
     * Saves the task list to the storage file.
     *
     * @param tasks The task list to be saved.
     */
    public void saveTaskList(ArrayList<Task> tasks) {

        try (FileWriter fw = new FileWriter(filePath)) {
            for (Task task : tasks) {
                fw.write(task.saveTaskString() + "\n");
            }
        } catch (IOException e) {
            TextUi.printExceptions(Messages.MESSAGE_FILE_NOT_SAVED + " Unable to write to file: " + filePath);
        }
    }
}
