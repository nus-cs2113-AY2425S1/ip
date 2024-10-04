import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public int getSize() {
        return tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public List<Task> find(String keyword) {
        List<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (task.task.toLowerCase().contains(keyword.trim().toLowerCase())) {
                result.add(task);
            }
        }
        return result;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public Task removeTask(int index) {
        return tasks.remove(index);
    }

    public List<Task> getTaskList() {
        return tasks;
    }
}
