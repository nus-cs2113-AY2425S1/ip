import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    // Constructor for initializing an empty task list
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    // Constructor to initialize with an existing list of tasks
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    // Method to add a task to the list
    public void addTask(Task task) {
        tasks.add(task);
    }

    // Method to remove a task from the list
    public void deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        } else {
            throw new IndexOutOfBoundsException("Task index out of range");
        }
    }

    // Method to get a task by index
    public Task getTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        } else {
            throw new IndexOutOfBoundsException("Task index out of range");
        }
    }

    // Method to get the number of tasks
    public int getSize() {
        return tasks.size();
    }

    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks);  // Returns a copy of the tasks list
    }

    // Returns a ArrayList base on tasks with matching keyword
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }



}
