package jeremy.util;

import jeremy.exception.InvalidTaskNumberException;
import jeremy.exception.TaskNotFoundException;
import jeremy.task.Task;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * The {@code TaskList} class manages a list of tasks and provides various
 * methods to manipulate and interact with the list such as adding, deleting,
 * marking tasks as done or not done, and printing the task list.
 */
public class TaskList implements Iterable<Task> {
    private final ArrayList<Task> tasks;
    private final Ui ui;

    /**
     * Default constructor. Initializes an empty task list and a UI object.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
        this.ui = new Ui();
    }

    /**
     * Copy constructor. Creates a new {@code TaskList} that references the tasks
     * from the provided {@code TaskList}.
     *
     * @param taskList The {@code TaskList} to copy tasks from.
     */
    public TaskList(TaskList taskList) {
        this.tasks = taskList.tasks;
        this.ui = new Ui();
    }

    /**
     * Returns an iterator over the tasks in the TaskList.
     * This allows for iteration over the tasks using an enhanced for-loop.
     *
     * @return an Iterator of Task objects.
     */
    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Retrieves the task at the specified index in the list.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Prints the current list of tasks using the UI.
     */
    public void printList() {
        ui.lineBreak();
        for (Task task : tasks) {
            ui.println((tasks.indexOf(task) + 1) + "." + task);
        }
        ui.lineBreak();
    }

    /**
     * Adds a task to the list and prints a confirmation message unless the task
     * is already marked as done (used for loading from storage).
     *
     * @param task The task to be added to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);

        // Doesn't print if task is being loaded
        if (!task.isDone()) {
            ui.lineBreak();
            ui.println("Got it. I've added this task:");
            ui.println(task.toString());
            ui.println("Now you have " + tasks.size() + " tasks in the list.");
            ui.lineBreak();
        }
    }

    /**
     * Deletes the task at the specified index, and prints a confirmation message.
     *
     * @param argument The task number (as a String) to delete.
     * @throws InvalidTaskNumberException If the provided task number is not a valid integer.
     * @throws TaskNotFoundException      If the task number is out of bounds or doesn't exist.
     */
    public void deleteTask(String argument) throws InvalidTaskNumberException, TaskNotFoundException {
        int index;
        Task task;
        try {
            index = Integer.parseInt(argument);
            task = tasks.get(index - 1);
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException(argument);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException(argument, tasks.size());
        }

        ui.lineBreak();
        tasks.remove(task);
        ui.println("Ok, deleted this task:");
        ui.println(task.toString());
        ui.println("Now you have " + tasks.size() + " tasks in the list.");
        ui.lineBreak();
    }

    /**
     * Marks the task at the specified index as done and prints a confirmation message.
     *
     * @param argument The task number (as a String) to mark as done.
     * @throws InvalidTaskNumberException If the provided task number is not a valid integer.
     * @throws TaskNotFoundException      If the task number is out of bounds or doesn't exist.
     */
    public void markTaskAsDone(String argument) throws InvalidTaskNumberException, TaskNotFoundException {
        try {
            setTaskStatus(argument, true);
        } catch (InvalidTaskNumberException e) {
            throw new InvalidTaskNumberException(e.getMessage());
        } catch (TaskNotFoundException e) {
            throw new TaskNotFoundException(e.getMessage());
        }
    }

    /**
     * Marks the task at the specified index as not done and prints a confirmation message.
     *
     * @param argument The task number (as a String) to unmark as done.
     * @throws InvalidTaskNumberException If the provided task number is not a valid integer.
     * @throws TaskNotFoundException      If the task number is out of bounds or doesn't exist.
     */
    public void markTaskAsNotDone(String argument) throws InvalidTaskNumberException, TaskNotFoundException {
        try {
            setTaskStatus(argument, false);
        } catch (InvalidTaskNumberException e) {
            throw new InvalidTaskNumberException(e.getMessage());
        } catch (TaskNotFoundException e) {
            throw new TaskNotFoundException(e.getMessage());
        }
    }

    /**
     * Changes the status of the task at the specified index to either done or not done.
     *
     * @param argument The task number (as a String) to update.
     * @param isDone   The new status of the task (true for done, false for not done).
     * @throws InvalidTaskNumberException If the provided task number is not a valid integer.
     * @throws TaskNotFoundException      If the task number is out of bounds or doesn't exist.
     */
    private void setTaskStatus(String argument, boolean isDone) throws InvalidTaskNumberException, TaskNotFoundException {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(argument);
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException(argument);
        }

        try {
            if (isDone) {
                tasks.get(taskNumber - 1).markDone();
                ui.lineBreak();
                ui.println("Nice! I've marked this task as done:");
            } else {
                tasks.get(taskNumber - 1).markNotDone();
                ui.lineBreak();
                ui.println("I've unmarked this task:");
            }
            ui.println(tasks.get(taskNumber - 1).toString());
            ui.lineBreak();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            throw new TaskNotFoundException(argument, tasks.size());
        }
    }
}
