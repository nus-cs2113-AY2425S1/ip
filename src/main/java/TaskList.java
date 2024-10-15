package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the task list at the specific index.
     *
     * @param index The index of the task to be deleted.
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Retrieves the task at the specific index in the task list.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specific index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The size of the task list.
     */
    public int getSize() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return (ArrayList<Task>) tasks;
    }

    /**
     * Finds and returns a map of tasks that match the specific keyword and their corresponding indices.
     *
     * @param keyword The keyword to search for in the tasks.
     * @return A map where the keys are the 1-based indices of the matching tasks and the values are the matching tasks.
     */
    public Map<Integer, Task> findTasksByKeywordWithIndex(String keyword) {
        Map<Integer, Task> matchingTasks = new HashMap<>();  // Store index-task pairs
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getName().contains(keyword)) {
                matchingTasks.put(i + 1, task);  // Store the 1-based index
            }
        }
        return matchingTasks;
    }
}
