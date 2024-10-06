package aether.tasklist;

import aether.task.Task;
import aether.ui.Ui;

import java.util.ArrayList;

/**
 * Manages the task list and provides operations to manipulate tasks.
 * <p>
 * This class allows for adding, deleting, listing, and updating the status of tasks.
 * It maintains an internal list of tasks and interacts with the user interface
 * to provide feedback on operations performed.
 * </p>
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a {@code TaskList} with the provided list of tasks.
     *
     * @param tasks An {@code ArrayList} of {@code Task} objects to initialize the task list.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Displays all tasks in the task list to the user.
     * <p>
     * If the task list is empty, a message indicating that the task list is empty is shown.
     * Otherwise, each task is listed with its corresponding index.
     * </p>
     */
    public void listTasks() {
        if (tasks.isEmpty()) {
            Ui.response("Task list is empty.");
        } else {
            Ui.response("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                Ui.newLineResponse((i + 1) + "." + tasks.get(i));
            }
        }
    }

    /**
     * Adds a new task to the task list and notifies the user.
     *
     * @param task The {@code Task} object to be added to the task list.
     */
    public void addTask(Task task) {
        tasks.add(task);
        Ui.response("Added task: " + task);
        Ui.newLineResponse("You now have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Deletes a task from the task list based on its index and notifies the user.
     *
     * @param index The zero-based index of the task to be deleted.
     * @throws IndexOutOfBoundsException If the provided index is out of range (index < 0 || index >= tasks.size()).
     */
    public void deleteTask(int index) {
        Task task = tasks.remove(index);
        Ui.response("Removed task: " + task);
        Ui.newLineResponse("You now have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Marks or unmarks a task as done based on the provided index and status.
     *
     * @param index  The zero-based index of the task to be updated.
     * @param isMark {@code true} to mark the task as done, {@code false} to mark it as not done.
     * @throws IndexOutOfBoundsException If the provided index is out of range (index < 0 || index >= tasks.size()).
     */
    public void markTaskStatus(int index, boolean isMark) {
        Task task = tasks.get(index);
        task.setDone(isMark);
        String status = isMark ? "Done" : "Not done";
        Ui.response("Task " + (index + 1) + " marked as " + status + ": " + task);
    }

    /**
     * Retrieves the list of all tasks.
     *
     * @return An {@code ArrayList} containing all {@code Task} objects in the task list.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
