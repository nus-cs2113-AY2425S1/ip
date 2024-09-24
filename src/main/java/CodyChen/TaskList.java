package CodyChen;

import CodyChen.Task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void delTask(int num) {
        tasks.remove(num);
    }

    public void markDone(int num) {
        tasks.get(num).markDone();
    }

    public void markUndone(int num) {
        tasks.get(num).markDel();
    }

    public int size(){
        return tasks.size();
    }

    public Task getTask(int num) {
        return tasks.get(num);
    }

    public ArrayList<Task> getTask() {
        return tasks;
    }
}
