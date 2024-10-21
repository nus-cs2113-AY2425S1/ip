package appal.task;

import java.util.ArrayList;

/**
 * TaskList class has an ArrayList taskList attribute that stores users' tasks,
 * and contains methods to add, delete, mark and get tasks.
 */
public class TaskList {
    /** Stores users' tasks */
    private final ArrayList<Task> taskList;

    /**
     * Class constructor.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Returns the entire list of user tasks.
     *
     * @return An ArrayList taskList that contains all user tasks.
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Returns the Task stored at the specified index in the task list.
     *
     * @param listIndex Index of the Task to get from the task list.
     * @return The task stored at listIndex.
     */
    public Task getTask(int listIndex) {
        return taskList.get(listIndex);
    }

    /**
     * Adds a new Task to the task list.
     *
     * @param task The task that is added to the task list.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Removes the task at the specified index in the task list.
     *
     * @param listIndex The index of the Task to be removed from the task list.
     */
    public void removeTask(int listIndex) {
        taskList.remove(listIndex);
    }

    /**
     * Marks (if done) or unmarks (if undone) the task at the specified index in the task list.
     * @param listIndex The index of the Task to mark in the task list.
     * @param isMark Indicates if task is to be marked or unmarked.
     * @return The Task that is marked or unmarked.
     */
    public Task markTask(int listIndex, boolean isMark) {
        Task taskToMark = getTask(listIndex);
        taskToMark.setDone(isMark);
        return taskToMark;
    }
}
