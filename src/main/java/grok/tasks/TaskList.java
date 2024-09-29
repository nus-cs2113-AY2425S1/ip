package grok.tasks;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    // Default constructor
    public TaskList() {
        tasks = new ArrayList<>();
    }

    // Constructor that accepts a list of tasks
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
