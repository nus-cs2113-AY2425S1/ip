package appal.task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public Task getTask(int listIndex) {
        return taskList.get(listIndex);
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void removeTask(int listIndex) {
        taskList.remove(listIndex);
    }

    public Task markTask(int listIndex, boolean isMark) {
        Task taskToMark = getTask(listIndex);
        taskToMark.setDone(isMark);
        return taskToMark;
    }
}
