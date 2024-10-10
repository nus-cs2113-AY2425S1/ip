package tommi;

import tommi.Task.Task;

import java.util.ArrayList;

public class TaskList {

    private static final ArrayList<Task> tasks = new ArrayList<>();  // Array to store tasks

    /**
     * Return the current ArrayList of Task for saving in save file
     *
     * @return current ArrayList of Task
     */
    public static ArrayList<Task> getTaskList() {
        return tasks;
    }

    // print the task list using Ui method
    public static void listTasks() {
        Ui.printTaskList(tasks);
    }

    /**
     * Add a Task to the current TaskList. Print the task added
     * with its index in the ArrayList of Task
     *
     * @param task Task to be added
     */
    public static void addTask(Task task) {
        tasks.add(task);
        Ui.printAddTask(task, tasks);
    }

    /**
     * Add a Task to the current TaskList. To be used when adding save file content
     * back into empty TaskList when starting program
     *
     * @param task Task to be added
     */
    public static void addTaskWithoutMessage(Task task) {
        tasks.add(task);
    }

    /**
     * Delete a Task from the current TaskList. Print the task deleted
     * with its index in the ArrayList of Task.
     *
     * @param index index of ArrayList of Task to be deleted
     * @throws IllegalArgumentException If index given is not in ArrayList of Task
     */
    public static void deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            Ui.printDeleteTask(tasks, index);
            tasks.remove(index);
        } else {
            throw new IllegalArgumentException(
                    """
                            ____________________________________________________________
                            ERROR: Cannot delete task as it is out of scope!
                            ____________________________________________________________""");
        }
    }

    /**
     * Mark a Task from the current TaskList as done. Print the task marked
     * with its index in the ArrayList of Task.
     *
     * @param index index of ArrayList of Task to be marked
     * @throws IllegalArgumentException If index given is not in ArrayList of Task
     */
    public static void markTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task updatedTask = tasks.get(index).updateIsDone(true);
            tasks.set(index, updatedTask);
            Ui.printMarkTask(tasks, index);
        } else {
            throw new IllegalArgumentException(
                    """
                            ____________________________________________________________
                            ERROR: Cannot mark task as it is out of scope!
                            ____________________________________________________________""");
        }
    }

    /**
     * Unmark a Task from the current TaskList as done. Print the task unmarked
     * with its index in the ArrayList of Task.
     *
     * @param index index of ArrayList of Task to be unmarked
     * @throws IllegalArgumentException If index given is not in ArrayList of Task
     */
    public static void unmarkTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task updatedTask = tasks.get(index).updateIsDone(false);
            tasks.set(index, updatedTask);
            Ui.printUnmarkTask(tasks, index);
        } else {
            throw new IllegalArgumentException(
                    """
                            ____________________________________________________________
                            ERROR: Cannot unmark task as it is out of scope!
                            ____________________________________________________________""");
        }
    }
}
