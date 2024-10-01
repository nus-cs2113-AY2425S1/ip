package Yukee.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks that supports operations to add, delete, mark, and find tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the given tasks.
     *
     * @param tasks the initial list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task the task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the last task in the task list.
     *
     * @return the last task
     */
    public Task getLastTask() {
        return tasks.get(tasks.size() - 1);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return the number of tasks
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Prints all tasks in the task list.
     */
    public void printTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Your task list is empty! Try adding some tasks!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index the index of the task
     * @return the task at the given index
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param index the index of the task to be marked as done
     */
    public void markTaskAsDone(int index) {
        tasks.get(index).markAsDone();
    }

    /**
     * Marks the task at the specified index as not done.
     *
     * @param index the index of the task to be marked as not done
     */
    public void markTaskAsNotDone(int index) {
        tasks.get(index).isDone = false;
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param index the index of the task to be deleted
     * @return the deleted task
     */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns all tasks in the task list.
     *
     * @return the list of tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Finds tasks that contain the specified keyword.
     *
     * @param keyword the keyword to search for
     * @return the list of tasks that contain the keyword
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
