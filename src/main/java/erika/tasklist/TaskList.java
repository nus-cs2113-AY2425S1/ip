package erika.tasklist;

import erika.task.Task;
import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks;
    private static int taskArraySize;
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.taskArraySize = tasks.size();
    }

    public void deleteTask(int index) throws IndexOutOfBoundsException {
        if (index <= 0 || index > Task.getTaskArraySize()) {
            throw new IndexOutOfBoundsException();
        }
        tasks.remove(index - 1);
        decrementTaskArraySize();
    }

    public static void add(Task task) {
        tasks.add(task);
        incrementTaskArraySize();
    }
    public static void setTaskArraySize(int size) {
        taskArraySize = size;
    }

    public static void incrementTaskArraySize() {
        taskArraySize++;
    }

    public static void decrementTaskArraySize() {
        taskArraySize--;
    }

}
