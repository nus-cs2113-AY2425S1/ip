package AlyBot;

import Task.Task;
import java.util.ArrayList;

/**
 * Represents a list of tasks in the AlyBot application.
 * This class provides methods for managing and manipulating the task list.
 */
public class TaskList {

    private ArrayList<Task> taskList;

    /**
     * Constructs a new TaskList with an empty task list.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with a specified list of tasks.
     *
     * @param taskList The initial list of tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns the current list of tasks.
     *
     * @return An ArrayList of Task objects.
     */
    public ArrayList<Task> getList() {
        return taskList;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Toggles the completion status of a task at the specified index.
     *
     * @param index The index of the task to toggle.
     * @param isDone The new completion status of the task.
     */
    public void toggleStatus(int index, boolean isDone) {
        taskList.get(index).setDone(isDone);
    }

    /**
     * Adds a new task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Finds and returns the task at the specified index.
     *
     * @param indexNumToToggle The index of the task to findTask.
     * @return The Task object at the specified index.
     */
    public Task findTask(int indexNumToToggle) {
        return taskList.get(indexNumToToggle);
    }

    /**
     * Removes the task at the specified index from the task list.
     *
     * @param index The index of the task to remove.
     */
    public void removeTask(int index) {
        taskList.remove(index);
    }
}