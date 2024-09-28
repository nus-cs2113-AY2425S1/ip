package main.java;

import ran.task.Task;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private int taskCount;

    public TaskList() {
        taskCount = 0;
        tasks = new ArrayList<>();
    }

    public int getTaskCount() {
        return taskCount;
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }
    
    public Task getTask(int index) {
        return tasks.get(index);
    }

    public Task removeTask(int index) {
        Task deletedTask = tasks.get(index);
        tasks.remove(index);
        taskCount--;
        return deletedTask;
    }
        
    public void addTask(Task newTask) {
        tasks.add(newTask);
        taskCount++;
    }
}
