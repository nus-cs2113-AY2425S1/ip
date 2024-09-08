package Taylor.task;

import java.util.ArrayList;
import java.util.StringJoiner;

/**
 * Represents a list of tasks. The TaskList class provides methods to add, remove,
 * and manage tasks within the list.
 */
public class TaskList {

    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with a provided list of tasks.
     *
     * @param tasks An ArrayList of Task objects to initialize the TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return The size of the TaskList.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Retrieves a task by its index in the TaskList.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Removes a task from the TaskList by its index.
     *
     * @param index The index of the task to remove.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public void remove(int index) {
        if (index < tasks.size() && index >= 0) {
            tasks.remove(index);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Marks a task as completed in the TaskList.
     *
     * @param index The index of the task to mark as completed.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public void markTask(int index) {
        if (index < tasks.size() && index >= 0) {
            tasks.get(index).setCompleted(true);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Marks a task as not completed in the TaskList.
     *
     * @param index The index of the task to mark as not completed.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public void unmarkTask(int index) {
        if (index < tasks.size() && index >= 0) {
            tasks.get(index).setCompleted(false);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Returns a string representing the data of all tasks in the TaskList,
     * suitable for saving to a file.
     *
     * @return A string containing the serialized data of all tasks.
     */
    public String write() {
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            sb.append(task.write()).append(System.lineSeparator());
        }
        return sb.toString();
    }

    /**
     * Searches for tasks in the TaskList that contain the given keyword in their description.
     *
     * @param keyword The keyword to search for in the task descriptions.
     * @return A string containing the matching tasks with their index, or null if no match is found.
     */
    public String find(String keyword) {
        int counter = 0;
        StringJoiner sj = new StringJoiner(System.lineSeparator());
        for (Task task : this.tasks) {
            if (task.description.contains(keyword)) {
                counter++;
                sj.add(counter + "." + task);
            }
        }
        if (counter == 0) {
            return null;
        }
        return sj.toString();
    }

    /**
     * Clear all the tasks in the TaskList and reset
     */
    public void reset(){
        tasks.clear();
    }
}