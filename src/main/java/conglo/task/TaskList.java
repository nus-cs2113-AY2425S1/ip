package conglo.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private static ArrayList<Task> taskList;

    /**
     * Constructs a TaskList with the specified list of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        taskList = tasks;
    }

    /**
     * Default constructor for an empty task list.
     * Initializes the task list with an empty list.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Checks if the task list is empty.
     */
    public static boolean isEmpty() {
        return taskList.isEmpty();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Removes a task from the task list at the specified index.
     *
     * @param index The index of the task to be removed.
     */
    public void removeTask(int index) {
        taskList.remove(index);
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Returns the list of tasks.
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Lists all tasks in the task list, printing their details.
     */
    public static void listTasks() {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i).toFileFormat());
        }
    }
}
