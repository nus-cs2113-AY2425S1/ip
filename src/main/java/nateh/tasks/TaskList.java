package nateh.tasks;

import java.util.ArrayList;

/**
 * TaskList represents the way tasks are stored in the application
 */
public class TaskList {
    private ArrayList<Task> tasks;
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    public void addTask(Task task) {
        tasks.add(task);
    }
    public void remove(int index) {
        tasks.remove(index);
    }
    public int size() {
        return tasks.size();
    }
    public Task get(int index) {
        return tasks.get(index);
    }
    public ArrayList<Task> getTasks() {
        return tasks;
    }
    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}
