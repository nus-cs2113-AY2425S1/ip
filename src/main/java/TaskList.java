import java.util.ArrayList;

/**
 * The {@code TaskList} class manages a list of tasks and provides methods
 * to manipulate the tasks, such as adding, deleting, and searching.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Creates an empty {@code TaskList}.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Creates a {@code TaskList} with an existing list of tasks.
     *
     * @param tasks The list of tasks to initialize the {@code TaskList} with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The {@code Task} to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes a task from the task list by its index.
     *
     * @param index The index of the task to delete (0-based).
     * @return The {@code Task} that was removed.
     * @throws AirBorderException If the index is out of bounds.
     */
    public Task deleteTask(int index) throws AirBorderException {
        if (index < 0 || index >= taskList.size()) {
            throw new AirBorderException("Invalid task number.");
        }
        return taskList.remove(index);
    }

    /**
     * Retrieves a task from the task list by its index.
     *
     * @param index The index of the task to retrieve (0-based).
     * @return The {@code Task} at the specified index.
     * @throws AirBorderException If the index is out of bounds.
     */
    public Task getTask(int index) throws AirBorderException {
        if (index < 0 || index >= taskList.size()) {
            throw new AirBorderException("Invalid task number.");
        }
        return taskList.get(index);
    }

    /**
     * Retrieves all tasks in the task list.
     *
     * @return An {@code ArrayList} containing all tasks.
     */
    public ArrayList<Task> getAllTasks() {
        return taskList;
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Checks if the task list is empty.
     *
     * @return {@code true} if the task list is empty; {@code false} otherwise.
     */
    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    /**
     * Searches for tasks that contain the specified keyword in their descriptions.
     *
     * @param keyword The keyword to search for.
     * @return An {@code ArrayList} of tasks that match the keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : taskList) {
            if (task.description.contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
