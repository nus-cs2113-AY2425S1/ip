package Tars;

import Tars.Task.Task;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task getTask(int index) throws TarsException {
        if (index < 0 || index >= tasks.size()) {
            throw new TarsException("Task number out of range.");
        }
        return tasks.get(index);
    }

    public Task deleteTask(int index) throws TarsException {
        if (index < 0 || index >= tasks.size()) {
            throw new TarsException("Task number out of range.");
        }
        return tasks.remove(index);
    }

    public int getTaskCount() {
        return tasks.size();
    }
}
