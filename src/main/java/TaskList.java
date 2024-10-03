import java.util.ArrayList;
/**
 * Represents a list of tasks.
 */

public class TaskList {
    private Task[] tasks = new Task[100];
    private int lastIndex = 0;

    /**
     * Adds a task to the task list.
     *
     * @param task The task to add.
     */
    public void add(Task task) {
        tasks[lastIndex] = task;
        lastIndex++;
    }

    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (int i = 0; i < lastIndex; i++) {
            if (tasks[i].getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                foundTasks.add(tasks[i]);
            }
        }
        return foundTasks;
    }
    /**
     * Deletes a task from the task list at the specified index.
     *
     * @param index The index of the task to delete.
     */
    public void delete(int index) {
        for (int i = index; i < lastIndex - 1; i++) {
            tasks[i] = tasks[i + 1];
        }
        tasks[lastIndex - 1] = null;
        lastIndex--;
    }
    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public Task[] getTasks() {
        return tasks;
    }
    /**
     * Returns the number of tasks in the task list.
     *
     * @return The count of tasks.
     */
    public int getTaskCount() {
        return lastIndex;
    }
    /**
     * Returns the task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return tasks[index];
    }
    /**
     * Marks the specified task as done.
     *
     * @param index The index of the task to mark.
     * @param isDone true to mark as done, false otherwise.
     */
    public void markTask(int index, boolean isDone) {
        tasks[index].setDone(isDone);
    }
}
