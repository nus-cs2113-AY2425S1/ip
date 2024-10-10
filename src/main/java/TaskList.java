import java.util.ArrayList;

public class TaskList {
  private ArrayList<Task> tasks;

  public TaskList() {
    this.tasks = new ArrayList<>();
  }

  public TaskList(ArrayList<Task> tasks) {
    this.tasks = tasks;
  }

  public void addTask(Task task) {
    tasks.add(task);
  }

  public void deleteTask(int index) {
    tasks.remove(index);
  }

  public ArrayList<Task> getTasks() {
    return tasks;
  }

  public int getSize() {
    return tasks.size();
  }

  public Task getTask(int index) {
    return tasks.get(index);
  }
}