package task;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages list of tasks, providing methods to perform add, delete, retrieval of tasks.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Construct empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Construct TaskList with defined initial tasks.
     * @param tasks initial set of tasks
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Add a task to TaskList.
     * @param task task to add
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Delete a task at given index.
     * @param index index of task to be deleted
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Retrieve task of a specified index.
     * @param index index of task to be retrieved
     * @return return task at the defined index
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Return number of tasks in list.
     * @return size of task list
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Check if task list is empty.
     * @return true if the task list is empty, else otherwise
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Return a list of all tasks.
     * @return list of tasks
     */
    public List<Task> getTasks() {
        return tasks;
    }
}
