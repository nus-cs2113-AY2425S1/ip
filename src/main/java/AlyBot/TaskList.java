package AlyBot;

import Task.Task;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList (ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public ArrayList<Task> getList() {
        return taskList;
    }

    public int getSize() {
        return taskList.size();
    }

    public void toggleStatus(int index, boolean isDone) {
        taskList.get(index).setDone(isDone);
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public Task find(int indexNumToToggle) {
        return taskList.get(indexNumToToggle);
    }

    public void removeTask(int index) {
        taskList.remove(index);
    }
}
