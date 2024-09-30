import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) throws BebeException {
        try {
            tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new BebeException("Task number is out of range. Cannot delete task.");
        }
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void markTask(int index) throws BebeException {
        try {
            Task task = tasks.get(index);
            task.markAsDone();
        } catch (IndexOutOfBoundsException e) {
            throw new BebeException("Task number is out of range. Cannot mark task.");
        }
    }

    public void unmarkTask(int index) throws BebeException {
        try {
            Task task = tasks.get(index);
            task.markAsNotDone();
        } catch (IndexOutOfBoundsException e) {
            throw new BebeException("Task number is out of range. Cannot unmark task.");
        }
    }

    public int getTaskCount() {
        return tasks.size();
    }
}