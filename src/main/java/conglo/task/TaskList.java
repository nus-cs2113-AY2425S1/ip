package conglo.task;

import conglo.exception.InvalidFormat;
import conglo.exception.InvalidTaskNumber;
import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> tasks) {
        taskList = tasks;
    }

    // Default constructor for an empty task list
    public TaskList() {
        taskList = new ArrayList<>(); // Initialize with an empty list
    }

    public static boolean isEmpty() {
        return taskList.isEmpty();
    }

    public int size() {
        return taskList.size();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void deleteTask(int index) throws InvalidTaskNumber {
        if(index<0 || index>=taskList.size()) {
            throw new InvalidTaskNumber();
        }
        taskList.remove(index);
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public int getSize() {
        return taskList.size();
    }

    public static ArrayList<Task> getTaskList() {
        return taskList;
    }

    public static void listTasks() {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i).toFileFormat());
        }
    }
}
