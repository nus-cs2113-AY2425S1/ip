package tasklist;

import model.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task remove(int index) {
        return tasks.remove(index);
    }

    public void markAsDone(int index) {
        tasks.get(index).markAsDone();
    }

    public void markAsNotDone(int index) {
        tasks.get(index).markAsNotDone();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public boolean isValidTaskNumber(int number) {
        return number >= 0 && number < tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
