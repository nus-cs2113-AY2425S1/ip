import java.util.ArrayList;

/**
 * Manages a collection of tasks for the PlopBot application.
 * This class provides methods to add, remove, find, and manage tasks in a list.
 * It serves as the primary data structure for task management operations.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     * Initializes a new ArrayList to store Task objects.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with an existing collection of tasks.
     *
     * @param tasks - The ArrayList of Task objects to initialize the TaskList with
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new task to the list.
     *
     * @param task - The Task object to be added to the list
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes and returns a task at the specified index.
     *
     * @param index             - The index of the task to remove (0-based)
     * @return                  - The removed Task object
     * @throws PlopBotException - If the index is out of bounds or invalid
     */
    public Task removeTask(int index) throws PlopBotException {
        if (index < 0 || index >= tasks.size()) {
            throw new PlopBotException("Invalid task index");
        }
        return tasks.remove(index);
    }

    /**
     * Searches for tasks whose names contain the specified keyword.
     * The search is case-insensitive.
     *
     * @param keyword - The search term to match against task names
     * @return        - An ArrayList containing all tasks whose names contain the keyword
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getName().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Returns the complete list of tasks.
     *
     * @return - An ArrayList containing all tasks in the list
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Retrieves a task at the specified index.
     *
     * @param index             - The index of the task to retrieve (0-based)
     * @return                  - The Task object at the specified index
     * @throws PlopBotException - If the index is out of bounds or invalid
     */
    public Task getTask(int index) throws PlopBotException {
        if (index < 0 || index >= tasks.size()) {
            throw new PlopBotException("Task index out of range.");
        }
        return tasks.get(index);
    }

    /**
     * Returns the total number of tasks in the list.
     *
     * @return - The number of tasks currently in the list
     */
    public int size() {
        return tasks.size();
    }
}
