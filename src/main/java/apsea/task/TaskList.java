package apsea.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public int getTotal() {
        return taskList.size();
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public int getTaskIndex(Task task) {
        return taskList.indexOf(task);
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void removeTask(int taskIndex) {
        taskList.remove(taskList.get(taskIndex));
    }

}
