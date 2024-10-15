package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

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

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return (ArrayList<Task>) tasks;
    }

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