package erika.tasklist;

import erika.task.Task;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class TaskList {
    private static ArrayList<Task> tasks;
    private static int taskArraySize;
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        taskArraySize = tasks.size();
    }

    public static void setTaskArraySize(int size) {
        taskArraySize = size;
    }

    public static int getTaskArraySize() {
        return taskArraySize;
    }

    public static void incrementTaskArraySize() {
        taskArraySize++;
    }

    public static void decrementTaskArraySize() {
        taskArraySize--;
    }

    public String printFileLine() {
        return (String) tasks.stream()
                        .map(Task::generateFileLine)
                        .collect(Collectors.joining(""));
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }
}
