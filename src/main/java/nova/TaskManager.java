package nova;

import nova.exception.InsufficientSpaceException;
import nova.task.Task;

import java.io.IOException;
import java.util.ArrayList;

public class TaskManager {

    private static final int MAX_TASKS = 100;
    private static ArrayList<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public void checkSpace() throws InsufficientSpaceException {
        if (Task.getNumberOfTasks() >= MAX_TASKS) {
            throw new InsufficientSpaceException("Maximum number of " + MAX_TASKS + " tasks reached.");
        }
    }

    public static void addTask(Task task) {
        tasks.add(task);
        try {
            Storage.appendToStorage(task.getTaskStorageInfo());
        } catch (IOException e) {
            MessageDisplay.displayStorageError();
        }
    }

    public static void loadTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(int index) {
        tasks.remove(index);
        Task.removeTask();
    }

    public void markTask(int index) {
        tasks.get(index).markDone();
    }

    public void unmarkTask(int index) {
        tasks.get(index).unmarkDone();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void listTasks() {
        MessageDisplay.displaySeparator();
        for (int i = 0; i < Task.getNumberOfTasks(); i++) {
            System.out.println("     " + (i + 1) + "." + tasks.get(i).getTaskInfo());
        }
        MessageDisplay.displaySeparator();
    }

    public void updateStorage() {
        String updatedInfo = "";
        for (int i = 0; i < Task.getNumberOfTasks(); i++) {
            updatedInfo += (tasks.get(i).getTaskStorageInfo() + System.lineSeparator());
        }
        try {
            Storage.updateStorage(updatedInfo);
        } catch (IOException e) {
            MessageDisplay.displayStorageError();
        }
    }
}
