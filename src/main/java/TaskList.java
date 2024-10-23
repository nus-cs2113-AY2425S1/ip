import java.util.ArrayList;

/**
 * Manages a list of tasks. Provides methods to add, remove, and manipulate tasks in the list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    // Constructs an empty TaskList.
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    // Constructs a TaskList with an existing list of tasks.
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    // Adds a task to the TaskList.
    public void addTask(Task task) {
        tasks.add(task);
    }

    // Removes a task from the TaskList based on its index.
    public Task removeTask(int index) throws KaiException {
        if (index < 0 || index >= tasks.size()) {
            throw new KaiException("Invalid task number.");
        }
        return tasks.remove(index);
    }

    // Marks a task as done based on its index.
    public void markTaskAsDone(int index) throws KaiException {
        if (index < 0 || index >= tasks.size()) {
            throw new KaiException("Task number is out of range.");
        }
        tasks.get(index).markAsDone();
    }

    // Marks a task as not done based on its index.
    public void markTaskAsNotDone(int index) throws KaiException {
        if (index < 0 || index >= tasks.size()) {
            throw new KaiException("Task number is out of range.");
        }
        tasks.get(index).markAsNotDone();
    }

    // Returns the number of tasks in the TaskList.
    public int getSize() {
        return tasks.size();
    }

    // Retrieves a task from the TaskList based on its index.
    public Task getTask(int index) throws KaiException {
        if (index < 0 || index >= tasks.size()) {
            throw new KaiException("Invalid task number.");
        }
        return tasks.get(index);
    }

    // Returns the list of tasks.
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Prints the tasks in the TaskList to the console.
     * If the list is empty, prints a message indicating that there are no tasks.
     */
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
