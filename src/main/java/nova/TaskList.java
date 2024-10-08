package nova;

import nova.exception.InsufficientSpaceException;
import nova.task.Task;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents the entire TaskList. Contains the data of the TaskList.
 */
public class TaskList {

    /** Maximum number of tasks that can be stored. */
    private static final int MAX_TASKS = 100;

    /** The list of tasks stored in the system. */
    private static ArrayList<Task> tasks;

    /**
     * Constructs a new {@code TaskList} with an empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Checks if there is sufficient space to add more tasks.
     *
     * @throws InsufficientSpaceException If the number of tasks reaches {@code MAX_TASKS}.
     */
    public void checkSpace() throws InsufficientSpaceException {
        if (Task.getNumberOfTasks() >= MAX_TASKS) {
            throw new InsufficientSpaceException("Maximum number of " + MAX_TASKS + " tasks reached.");
        }
    }

    /**
     * Adds a new task to the task list and appends it to the storage.
     *
     * @param task The task to be added.
     */
    public static void addTask(Task task) {
        tasks.add(task);
        try {
            Storage.appendToStorage(task.getTaskStorageInfo());
        } catch (IOException e) {
            Ui.displayStorageError();
        }
    }

    /**
     * Loads a task into the task list without updating storage.
     *
     * @param task The task to be loaded.
     */
    public static void loadTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the task list at the specified index and updates the number of tasks.
     *
     * @param index The index of the task to be removed.
     */
    public void removeTask(int index) {
        tasks.remove(index);
        Task.removeTask();
    }

    /**
     * Marks a task as done at the specified index in the task list.
     *
     * @param index The index of the task to be marked as done.
     */
    public void markTask(int index) {
        tasks.get(index).markDone();
    }

    /**
     * Unmarks a task as not done at the specified index in the task list.
     *
     * @param index The index of the task to be unmarked as done.
     */
    public void unmarkTask(int index) {
        tasks.get(index).unmarkDone();
    }

    /**
     * Retrieves a task from the task list at the specified index.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Updates the storage with the current task information.
     */
    public void updateStorage() {
        String updatedInfo = "";
        for (int i = 0; i < Task.getNumberOfTasks(); i++) {
            updatedInfo += (tasks.get(i).getTaskStorageInfo() + System.lineSeparator());
        }
        try {
            Storage.updateStorage(updatedInfo);
        } catch (IOException e) {
            Ui.displayStorageError();
        }
    }

    /**
     * Retrieves the storage information of a task at the specified index.
     *
     * @param i The index of the task.
     * @return The storage information of the task.
     */
    public String getTaskInfo(int i) {
        return tasks.get(i).getTaskInfo();
    }

    /**
     * Checks if the task at the specified index is associated with the given date.
     *
     * @param i The index of the task in the task list.
     * @param localDate The date to check against.
     * @return true if the task at the specified index matches the given date, false otherwise.
     */
    public boolean isDate(int i, LocalDate localDate) {
        return tasks.get(i).isDate(localDate);
    }

    /**
     * Checks if the task at the specified index contains the given phrase in its description.
     *
     * @param i The index of the task in the task list.
     * @param phrase The phrase to search for in the task's description.
     * @return true if the task at the specified index contains the given phrase, false otherwise.
     */
    public boolean doesContain(int i, String phrase) {
        return tasks.get(i).doesContain(phrase);
    }
}
