package yapper.tasks;

import java.util.ArrayList;
import java.util.List;

import yapper.io.OutputStringHandler;

// manages Tasks for Yapper
public class TaskHandler {
    private List<Task> tasks;

    // Constructor
    public TaskHandler() {
        tasks = new ArrayList<>();
    }
    // Getters
    public List<Task> getAllTasks() {
        return tasks;
    }
    public int getCurrTaskTotal() {
        return tasks.size();
    }
    public Task getTask(int taskOrdinal) {
        return tasks.get(taskOrdinal);
    }

    // Create, Update, Delete Operations
    public void addTask(Task task) {
        tasks.add(task);
        task.printAddedTask(tasks.size());
    }
    public void deleteTask(int taskOrdinal) {
        Task task = tasks.remove(taskOrdinal);
        task.printDeletedTask(tasks.size());
    }
    public void updateTaskStatus(Task task, boolean isDone) {
        task.setDoneStatus(isDone);
        OutputStringHandler.printTaskStatus(task, isDone);
    }

}
