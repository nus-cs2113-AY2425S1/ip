import java.util.ArrayList;

/**
 * The TaskList class manages a list of tasks.
 * It provides methods to add, delete, retrieve, and find tasks within the list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    // Constructor for initializing an empty task list
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs an empty TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Adds a task to the list.
     *
     * @param task the Task object to be added to the list
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the list by its index.
     *
     * @param index the index of the task to remove
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public void deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        } else {
            throw new IndexOutOfBoundsException("Task index out of range");
        }
    }

    /**
     * Retrieves a task by its index.
     *
     * @param index the index of the task to retrieve
     * @return the Task object at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public Task getTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        } else {
            throw new IndexOutOfBoundsException("Task index out of range");
        }
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the size of the task list
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns a copy of all tasks in the list.
     *
     * @return an ArrayList containing all Task objects in the list
     */
    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks);  // Returns a copy of the tasks list
    }

    /**
     * Finds and returns tasks that contain the specified keyword in their description.
     *
     * @param keyword the keyword to search for in task descriptions
     * @return an ArrayList of Task objects that match the keyword
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

}
