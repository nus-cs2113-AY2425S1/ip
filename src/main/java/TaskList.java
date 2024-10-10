import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    /**
     * Represents a list of tasks. Provides methods to manage tasks, such as adding,
     * removing, retrieving, and searching tasks.
     *
     * @param tasks the initial list of tasks
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return the size of the task list
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Adds a new task to the task list.
     *
     * @param task the task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Searches for tasks that contain the specified keyword in their descriptions.
     * The search is case-insensitive and trims leading and trailing spaces.
     *
     * @param keyword the keyword to search for
     * @return a list of tasks that contain the keyword
     */
    public List<Task> find(String keyword) {
        List<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (task.task.toLowerCase().contains(keyword.trim().toLowerCase())) {
                result.add(task);
            }
        }
        return result;
    }

    /**
     * Returns the task at the specified index in the task list.
     *
     * @param index the index of the task to retrieve
     * @return the task at the specified index
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Removes and returns the task at the specified index in the task list.
     *
     * @param index the index of the task to remove
     * @return the removed task
     */
    public Task removeTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns the entire list of tasks.
     *
     * @return the list of tasks
     */
    public List<Task> getTaskList() {
        return tasks;
    }
}
