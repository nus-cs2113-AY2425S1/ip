package taskpackage;

import java.util.ArrayList;

/**
 * Represents a list of tasks, allowing for operations such as adding, deleting, marking, and unmarking tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks; // List to store tasks

    /**
     * Constructor for initializing an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a task from the task list based on the task index.
     *
     * @param taskIndex The index of the task to be deleted.
     */
    public void deleteTask(int taskIndex) {
        String taskString = this.tasks.get(taskIndex).checkboxString();
        this.tasks.remove(taskIndex);
        System.out.println("Ay Caramba, Task deleted: " + taskString);
    }

    /**
     * Deletes the latest task in the task list in case of errors.
     */
    public void deleteLatestTask() {
        this.tasks.remove(tasks.size() - 1); // Remove the latest task from the list
    }

    /**
     * Marks the latest task in the task list as done.
     */
    public void markLatestTask() {
        this.tasks.get(tasks.size() - 1).isDone = true;
    }

    /**
     * Returns the size of the task list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Returns the string representation of a task for storing in a data file.
     *
     * @param index The index of the task.
     * @return The formatted string for storing the task data.
     */
    public String dataFileEntry(int index) {
        return (this.tasks.get(index).inputString + " /isdone " + this.tasks.get(index).isDone + "\n");
    }

    /**
     * Marks a task as done by its index.
     *
     * @param taskIndex The index of the task to be marked as done.
     */
    public void mark(int taskIndex) {
        tasks.get(taskIndex).isDone = true;
        System.out.println("Fantastica!!!! I marked it:");
        System.out.println(tasks.get(taskIndex).checkboxString());
    }

    /**
     * Unmarks a task as not done by its index.
     *
     * @param taskIndex The index of the task to be unmarked.
     */
    public void unmark(int taskIndex) {
        tasks.get(taskIndex).isDone = false;
        System.out.println("Ay Caramba, I unmarked it:");
        System.out.println(tasks.get(taskIndex).checkboxString());
    }

    /**
     * Prints the list of tasks to the console.
     */
    public void printTasksList() {
        if (tasks.isEmpty()) {
            System.out.println("Por Favor? Nothing Here");
        } else {
            System.out.println("Si compinche, your " + tasks.size() + " tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i).checkboxString());
            }
        }
    }

    /**
     * Prints the tasks that contain the specified search string.
     *
     * @param findString The string to search for in the tasks.
     */
    public void findTasksList(String findString) {
        if (tasks.isEmpty()) {
            System.out.println("Por Favor? Nothing Here");
        } else {
            System.out.println("Si compinche, your tasks with the phrase <" + findString + ">:");
            int foundCount = 0;
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).checkboxString().contains(findString)) {
                    foundCount++;
                    System.out.println((i + 1) + "." + tasks.get(i).checkboxString());
                }
            }

            if (foundCount == 0) {
                System.out.println("Nothing Here");
            }
        }
    }
}
