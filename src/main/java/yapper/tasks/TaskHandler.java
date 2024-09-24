package yapper.tasks;

import java.util.ArrayList;
import java.util.List;

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
    public boolean isEmpty() {
        return tasks.isEmpty();
    }
    public Task getTaskAtOrdinal(int taskOrdinal) {
        return tasks.get(taskOrdinal);
    }
    public int getOrdinalOf(Task task) {
        return tasks.indexOf(task);
    }

    // Create, Update, Delete Operations
    public void addTask(Task task) {
        tasks.add(task);
    }
    public void deleteTask(int taskOrdinal) {
        tasks.remove(taskOrdinal);
    }
    public void updateTaskStatus(Task task, boolean isDone) {
        task.setDoneStatus(isDone);
    }

}
