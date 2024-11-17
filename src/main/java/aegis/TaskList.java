package aegis;

import aegis.task.Task;

import java.util.ArrayList;

/**
 * The TaskList class manages a collection of tasks.
 * It provides methods to add, delete, find, and display tasks, allowing for flexible manipulation of the task list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param tasks An ArrayList of Task objects to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Finds tasks in the list that contain the specified keyword in their description.
     *
     * @param keyword The keyword to search for within task descriptions.
     * @return An ArrayList of tasks that match the keyword.
     */
    public ArrayList<Task> findTasksByKeyword(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The Task object to be added to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the task list at the specified index.
     *
     * @param index The index of the task to be removed.
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Returns the list of tasks.
     *
     * @return An ArrayList of Task objects in the task list.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Displays the tasks in the task list.
     * Each task is printed in a formatted list with its index number.
     */
    public void displayTasks() {
        System.out.println();
        System.out.println(" I could do much more than checking your list, but, if you wish:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("    %d. %s%n", i + 1, tasks.get(i));
        }
        System.out.println();
    }
}
