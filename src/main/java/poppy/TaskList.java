package poppy;

import exceptions.CustomExceptions;
import tasks.Task;
import java.util.ArrayList;

/**
 * The {@code TaskList} class manages a collection of {@code Task} objects.
 * It provides methods to add, delete, and retrieve tasks from the list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Default constructor that initializes an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor that initializes the task list with a given list of tasks.
     *
     * @param tasks An {@code ArrayList} of tasks to initialize the task list.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the task list at the specified index.
     *
     * @param index The index of the task to be deleted.
     * @throws CustomExceptions.InvalidTaskIndexException if the index is invalid.
     */
    public void deleteTask(int index) throws CustomExceptions.InvalidTaskIndexException {
        if (index < 0 || index >= tasks.size()) {
            throw new CustomExceptions.InvalidTaskIndexException("Task do not exist :(");
        }
        tasks.remove(index);
    }

    /**
     * Retrieves a task from the task list at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The size of the task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the list of tasks in the task list.
     *
     * @return An {@code ArrayList} of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Displays all tasks in the task list, with their respective indices.
     */
    public void showTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
}


