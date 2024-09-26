package bosco.task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasksList;

    // Creates an empty list of tasks
    public TaskList() {
        tasksList = new ArrayList<>();
    }

    // Constructs a list of tasks from the given data
    public TaskList(ArrayList<Task> tasks) {
        this.tasksList = tasks;
    }

    public void addTask(Task toAdd) {
        tasksList.add(toAdd);
    }

    public void removeTask(Task toRemove) {
        tasksList.remove(toRemove);
    }

    public Task getTaskAtIndex(int index) {
        return tasksList.get(index);
    }

    public ArrayList<Task> getAllTasks() {
        return new ArrayList<Task>(tasksList);
    }

    public int getSize() {
        return tasksList.size();
    }
}
