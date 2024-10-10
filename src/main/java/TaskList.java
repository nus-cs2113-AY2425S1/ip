/**
 * Represents a list of tasks.
 * Provides methods to manage and manipulate tasks.
 */
import java.util.ArrayList;
import java.util.List;

public class TaskList {
  private List<Task> tasks;

  public TaskList() {
    this.tasks = new ArrayList<>();
  }

  public void addTask(Task task) {
    tasks.add(task);
  }

  public Task getTask(int index) {
    return tasks.get(index);
  }

  public int getSize() {
    return tasks.size();
  }

  public List<Task> findTasks(String keyword) {
    List<Task> matchingTasks = new ArrayList<>();
    for (Task task : tasks) {
      if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
        matchingTasks.add(task);
      }
    }
    return matchingTasks;
  }

  public List<Task> getTasks() {
    return tasks;
  }

  public void deleteTask(int index) {
    tasks.remove(index);
  }
}
