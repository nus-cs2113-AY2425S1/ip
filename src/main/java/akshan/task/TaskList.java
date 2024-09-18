package akshan.task;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class TaskList {

    private final ArrayList<Task> list = new ArrayList<>();

    /**
     * Returns index of item in TaskList
     */
    public Task getTask(int index) {
        return list.get(index);
    }

    /**
     * Returns size of TaskList
     */
    public int size() {
        return list.size();
    }

    /**
     * Prints list of tasks with their completion status.
     */
    public void printList() {
        if (list.isEmpty()) {
            System.out.println("Uh oh, the list is empty!");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        IntStream.range(0, list.size())
                .forEach(x -> System.out.println( (x + 1) + "." + list.get(x)));
    }

    /**
     * Adds a new task.
     *
     * @param task The task object to be added.
     * @throws IllegalArgumentException If the task is null.
     */
    public void addItem(Task task) throws IllegalArgumentException {
        if (task == null) {
            throw new IllegalArgumentException("Cannot add a null task to the list.");
        }
        this.list.add(task);
    }

    /**
     * Deletes a task from the list.
     *
     * @param index The index of the task to be deleted (0-based).
     * @return The deleted Task object.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public Task deleteItem(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= list.size()) {
            throw new IndexOutOfBoundsException("Task index out of range: " + (index + 1));
        }
        return list.remove(index);
    }

    /**
     * Sets the status of a task in the list.
     * Includes error catching for task index.
     *
     * @param index Index of the task in the list.
     * @param status The task's new status.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     */
    public void setItemStatus(int index, boolean status) throws IndexOutOfBoundsException {
        if (index < 1 || index > list.size()) {
            throw new IndexOutOfBoundsException("Invalid task index: " + index);
        }
        this.list.get(index - 1).setStatus(status);
    }
}