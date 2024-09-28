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

    public Task removeTask(int index) throws KaiException {
        if (index < 0 || index >= tasks.size()) {
            throw new KaiException("Invalid task number.");
        }
        return tasks.remove(index);
    }

    public void markTaskAsDone(int index) throws KaiException {
        if (index < 0 || index >= tasks.size()) {
            throw new KaiException("Task number is out of range.");
        }
        tasks.get(index).markAsDone();
    }

    public void markTaskAsNotDone(int index) throws KaiException {
        if (index < 0 || index >= tasks.size()) {
            throw new KaiException("Task number is out of range.");
        }
        tasks.get(index).markAsNotDone();
    }

    public int getSize() {
        return tasks.size();
    }

    public Task getTask(int index) throws KaiException {
        if (index < 0 || index >= tasks.size()) {
            throw new KaiException("Invalid task number.");
        }
        return tasks.get(index);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void printTasks() {
        if (tasks.isEmpty()) {
            System.out.println(" No tasks to display!");
        } else {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }
}
